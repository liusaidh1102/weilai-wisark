package com.weilai.gateway.filters;

import cn.hutool.core.collection.CollectionUtil;
import com.weilai.gateway.config.AuthProperties;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.weilai.common.constants.CacheConstant.LOGIN_TOKEN_EXPIRE;
import static com.weilai.common.constants.CacheConstant.LOGIN_TOKEN_PREFIX;

@Component
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private final AuthProperties authProperties;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private String headerName = "authorization";


    /**
     * @param exchange 当前请求的上下文
     * @param chain    放行
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 网关拦截，校验token
        ServerHttpRequest request = exchange.getRequest();
        if (isExclude(request.getPath().toString())) {
            return chain.filter(exchange);
        }
        List<String> headers = request.getHeaders().get(headerName);
        String token = null;
        if (!CollectionUtil.isEmpty(headers)) {
            token = headers.get(0);
        }
        if (token == null) {
            return getMono(exchange);
        }
        String key = LOGIN_TOKEN_PREFIX + token;
        String id = stringRedisTemplate.opsForValue().get(key);
        if (id == null) {
            return getMono(exchange);
        }
        // 传递用户信息 网关传到微服务，保存到请求头, 传到下一个微服务
//        ServerWebExchange swe = exchange.mutate()
//                .request(builder -> builder.header("userId", id))
//                .build();
//        return chain.filter(ex);
        // 放行
        return chain.filter(exchange);
    }

    /**
     * 返回结果
     *
     * @param exchange
     * @return
     */
    private static Mono<Void> getMono(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        String body = "{\"code\": 401, \"message\": \"未授权\"}";
        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }

    private boolean isExclude(String string) {
        for (String excludePath : authProperties.getExcludePaths()) {
            if (antPathMatcher.match(excludePath, string)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

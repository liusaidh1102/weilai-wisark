package com.weilai.gateway.filters;

import cn.dev33.satoken.reactor.context.SaReactorSyncHolder;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.weilai.gateway.config.AuthProperties;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

/**
 * SpringCloud 网关过滤器
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private final AuthProperties authProperties;

    @Value("${sa-token.timeout}")
    private Long timeout;

    /*
     * @param exchange 当前请求的上下文
     * @param chain    放行
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 放行路径
        if (isExclude(request.getPath().toString())) {
            return chain.filter(exchange);
        }
        try {
            // 先 set 上下文，再调用 Sa-Token 同步 API，并在 finally 里清除上下文
            SaReactorSyncHolder.setContext(exchange);
            if(!StpUtil.isLogin()) {
                return getMono(exchange);
            }
            // 检查前端提交的token是否已被顶替
            String currentToken = StpUtil.getTokenValue();
            Object loginId = StpUtil.getLoginId();

            // 获取当前登录账号的最新token
            String latestToken = StpUtil.getTokenValueByLoginId(loginId);

            // 如果当前token与最新token不一致，说明账号在别处登录
            if (!currentToken.equals(latestToken)) {
                return getKickedOutMono(exchange);
            }

            // 续签token
            StpUtil.renewTimeout(timeout);
        } finally {
            SaReactorSyncHolder.clearContext();
        }
        return chain.filter(exchange);
    }


//    /**
//     * @param exchange 当前请求的上下文
//     * @param chain    放行
//     * @return
//     */
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        // 网关拦截，校验token
//        ServerHttpRequest request = exchange.getRequest();
//        if (isExclude(request.getPath().toString())) {
//            return chain.filter(exchange);
//        }
//        List<String> headers = request.getHeaders().get(headerName);
//        String token = null;
//        if (!CollectionUtil.isEmpty(headers)) {
//            token = headers.get(0);
//        }
//        if (token == null) {
//            return getMono(exchange);
//        }
//        String key = LOGIN_TOKEN_PREFIX + token;
//        String id = stringRedisTemplate.opsForValue().get(key);
//        if (id == null) {
//            return getMono(exchange);
//        }
//        // 传递用户信息 网关传到微服务，保存到请求头, 传到下一个微服务
////        ServerWebExchange swe = exchange.mutate()
////                .request(builder -> builder.header("userId", id))
////                .build();
////        return chain.filter(ex);
//        // 放行
//        return chain.filter(exchange);
//    }

    /**
     * 返回结果
     *
     * @param exchange
     * @return
     */
    private static Mono<Void> getKickedOutMono(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        String body = "{\"code\": -1, \"message\": \"账号在别处登录\", \"date\": null}";
        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }

    private static Mono<Void> getMono(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        String body = "{\"code\": 401, \"message\": \"未授权\", \"date\": null}";
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
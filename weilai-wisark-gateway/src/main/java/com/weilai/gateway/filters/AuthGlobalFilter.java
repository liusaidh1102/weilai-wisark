package com.weilai.gateway.filters;
import com.weilai.gateway.config.AuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.List;
@Component
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private final AuthProperties authProperties;

    /**
     *
     * @param exchange  当前请求的上下文
     * @param chain 放行
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // to do 登录校验逻辑
        ServerHttpRequest request = exchange.getRequest();
        if(isExclude(request.getPath().toString())){
            return chain.filter(exchange);
        }
        List<String> headers = request.getHeaders().get("authorization");
        String token = null;
        if (headers != null && !headers.isEmpty()) {
            token = headers.get(0);
        }
        if(token == null){
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        // 根据token获取用户id
        Long userId = null;
//        try(){
//              解析过程出现的异常
//        }catch (Exception e){
//            ServerHttpResponse response = exchange.getResponse();
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            return response.setComplete();
//        }
        // 传递用户信息 网关传到微服务，保存到请求头, 传到下一个微服务
        ServerWebExchange swe = exchange.mutate()
                .request(builder -> builder.header("userId", userId.toString()))
                .build();

        return chain.filter(swe);
    }

    private boolean isExclude(String string) {
        for (String excludePath : authProperties.getExcludePaths()){
            if(antPathMatcher.match(excludePath, string)){
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

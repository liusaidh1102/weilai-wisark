package com.wisark.api.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 配置日志 ,调用处加上注解
 */
public class DefaultConfig implements RequestInterceptor {

    @Bean
    public Logger.Level feignLoggerLevel()  {
        return Logger.Level.FULL;
    }

    /**
     * 配置微服务，传递对应的token信息。远程调用基于token获取用户信息
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        获取请求体
        HttpServletRequest request = attributes.getRequest();
//        获取token
        String token = request.getHeader("Authorization");
//        注入feign的请求头
        template.header("Authorization",token);
    }

//    @Bean
//    public RequestInterceptor userInfoInterceptor() {
//        return new RequestInterceptor() {
//            @Override
//            public void apply(RequestTemplate requestTemplate) {
//                // 可以从上一个服务中获取到信息，传到下一个服务，解决跨服务传递信息问题
//                Long userId = UserHolder.getUser();
//                if (userId != null){
//                    requestTemplate.header("userId",userId.toString());
//                }
//            }
//        };
//    }

}

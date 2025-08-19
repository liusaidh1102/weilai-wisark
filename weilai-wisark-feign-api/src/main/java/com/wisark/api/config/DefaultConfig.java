package com.wisark.api.config;

import com.weilai.common.utils.UserHolder;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;

/**
 * 配置日志 ,调用处加上注解
 */
public class DefaultConfig {

    @Bean
    public Logger.Level feignLoggerLevel()  {
        return Logger.Level.FULL;
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

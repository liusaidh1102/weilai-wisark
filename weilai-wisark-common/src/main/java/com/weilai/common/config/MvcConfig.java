package com.weilai.common.config;
import com.weilai.common.interceptors.UserInfoInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
// 加上路径排除，在gateway模块中引入了common，可以排除掉web依赖
// webConfigure是mvc包下的
// 但是springcloud网关没有mvc那一套东西，底层是非阻塞式的响应编程（WenFlux）
// 所以mvc相关的内容不应该加载到网关中
// 加上@ConditionalOnClass检查是不是存在springmvc的核心类
@ConditionalOnClass(DispatcherServlet.class)
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInfoInterceptor());
    }
}
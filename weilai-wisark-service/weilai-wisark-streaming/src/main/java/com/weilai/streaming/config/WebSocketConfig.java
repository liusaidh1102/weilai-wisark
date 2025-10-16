package com.weilai.streaming.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {
    /**
     * 注册 JSR-356 标准 WebSocket 端点扫描器
     * 嵌入式容器（如 Tomcat）必须显式注册，外置容器（如独立 Tomcat）可省略
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}

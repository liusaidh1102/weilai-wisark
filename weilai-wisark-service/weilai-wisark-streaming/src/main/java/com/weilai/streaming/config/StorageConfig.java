package com.weilai.streaming.config;


import com.weilai.model.streaming.dtos.LiveUserDto;
import jakarta.websocket.Session;
import org.springframework.context.annotation.Bean;

import org.springframework.stereotype.Component;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class StorageConfig {
    // 存储房间信息
    @Bean
    public Map<String, Map<String, LiveUserDto>> rooms() {
        return new ConcurrentHashMap<>();
    }

    // 存储用户信息映射
    @Bean
    public Map<String, LiveUserDto> userInfoMap() {
        return new ConcurrentHashMap<>();
    }

    // 存储客户端连接
    @Bean
    public Map<String, Session> clients() {
        return new ConcurrentHashMap<>();
    }
}


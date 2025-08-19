package com.weilai.topic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 话题服务启动类
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.weilai")
public class TopicApplication {
    public static void main(String[] args) {
        SpringApplication.run(TopicApplication.class, args);
    }
}
package com.weilai.ai.config;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class AiConfig {

    @Value("${ai.system-role}")
    private String systemRole;

    /**
     * 定义系统角色
     * @param builder
     * @return
     */
    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        // 使用默认值
        return  builder.defaultSystem(systemRole).build();
    }

}
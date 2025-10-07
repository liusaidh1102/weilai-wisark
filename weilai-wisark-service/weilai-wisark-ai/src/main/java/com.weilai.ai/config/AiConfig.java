package com.weilai.ai.config;

import com.weilai.ai.memory.MysqlChatMemory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    /**
     * 系统角色
     */
    private static final String SYSTEM_ROLE = "你是舟小云，学习助手.";



    /**
     * 定义系统角色
     *
     * @param model
     * @return
     */
    @Bean
    public ChatClient chatClient(ChatModel model, ChatMemory chatMemory) {
        // 使用默认值
        return ChatClient
                .builder(model)
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory, "default", 10))
                .defaultSystem(SYSTEM_ROLE)
                .build();
    }

    /**
     * 使用自定义的Memory创建会话记忆的bean
     *
     * @return
     */
    @Bean
    public ChatMemory chatMemory() {
        return new MysqlChatMemory();
    }

}
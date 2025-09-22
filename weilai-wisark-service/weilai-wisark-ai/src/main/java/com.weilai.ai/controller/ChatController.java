package com.weilai.ai.controller;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * ai聊天相关功能
 */
@RestController
@RequestMapping("/ai")
public class ChatController {

    @Resource
    public ChatClient chatClient;

    @Resource
    private ChatModel chatModel;

    @GetMapping(value = "/chat/text",produces="text/html;charset=UTF-8")
    public Flux<String> chatStream(@RequestParam(value = "msg") String msg) {
        // prompt 提示词
        return chatClient.prompt("你是关于智慧云舟订单方面的小助手，基于订单方面回单对应的问题").user(msg).stream().content();
    }


}
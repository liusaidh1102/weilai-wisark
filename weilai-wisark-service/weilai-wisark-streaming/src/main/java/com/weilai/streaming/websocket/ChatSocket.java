package com.weilai.streaming.websocket;

import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@ServerEndpoint("/ws/chat/{userId}")
@Slf4j
public class ChatSocket {

}

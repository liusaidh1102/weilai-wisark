package com.weilai.streaming.webSocket;


import com.weilai.streaming.webSocket.netty.NettyWebSocketStarter;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class initRun implements ApplicationRunner {
    @Resource
    private NettyWebSocketStarter nettyWebSocketStarter;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        new Thread(nettyWebSocketStarter).start();

    }
}

package com.weilai.streaming.webSocket.netty;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HandlerHeartBeat extends ChannelDuplexHandler {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            switch (event.state()){
                case READER_IDLE:
                    log.info("未发送心跳，断开连接");
                    ctx.channel().close();
                    break;
                 case WRITER_IDLE:
//                    log.info("写超时");
                    ctx.writeAndFlush("ping");
                    break;
            }
        }
    }
}

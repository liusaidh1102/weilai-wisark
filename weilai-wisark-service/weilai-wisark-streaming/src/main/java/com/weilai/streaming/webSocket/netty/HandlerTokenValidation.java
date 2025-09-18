package com.weilai.streaming.webSocket.netty;

import com.weilai.streaming.webSocket.ChannelContextUtils;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@ChannelHandler.Sharable
@Slf4j
public class HandlerTokenValidation extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Resource
    private ChannelContextUtils channelContextUtils;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        String uri=msg.uri();
        QueryStringDecoder decoder=new QueryStringDecoder(uri);
        List<String> tokens = decoder.parameters().get("token");
        if(tokens==null){
            sendErrorResponse(ctx);
            return;
        }
        String token=tokens.get(0);
        if(token==null||token.isEmpty()){
            log.error("token为空");
            sendErrorResponse(ctx);
            return;
        }
        if(token.equals("123456")){
            //验证成功 将消息传递给下一个处理器
            ctx.fireChannelRead(msg.retain());
            //建立用户和channel的关联
            channelContextUtils.addContext(token,ctx.channel());
        }else{
            log.error("token无效");
            sendErrorResponse(ctx);
        }
    }
    private void sendErrorResponse(ChannelHandlerContext ctx) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.FORBIDDEN, Unpooled.copiedBuffer("token无效", StandardCharsets.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}

package com.weilai.streaming.webSocket.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class NettyWebSocketStarter implements Runnable{
    /**
     * boss 线程组,用于接受客户端的连接
     */
    private EventLoopGroup bossGroup=new NioEventLoopGroup();
    /**
     * worker 线程组,用于处理业务逻辑
     */
    private EventLoopGroup workerGroup=new NioEventLoopGroup();
    @Resource
    private HandlerTokenValidation handlerTokenValidation;
    @Resource
    private HandlerWebSocket handlerWebSocket;
    @Override
    public void run() {
        try {
            ServerBootstrap serverBootstrap=new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup);
            serverBootstrap.channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO)) //日志处理器
                    .childHandler(new ChannelInitializer<Channel>() { //初始化处理器
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            ChannelPipeline pipeline = channel.pipeline();
                            /**
                             * http编解码器
                             */
                            pipeline.addLast(new HttpServerCodec());
                            /**
                             * http 聚合器 64K 主要是将分片的http请求聚合成完整的请求 FullHttpRequest
                             */
                            pipeline.addLast(new HttpObjectAggregator(64*1024));
                            /**
                             * 用于检测连接的空闲状态，常用于心跳机制中。参数依次表示读空闲、写空闲和全部空闲的时间（单位秒）
                             * 6秒内如果没有收到客户端的请求就发送一个心跳包过去，
                             */
                            pipeline.addLast(new IdleStateHandler(6,0,0));
                            /**
                             * 自定义心跳处理类
                             */
                            pipeline.addLast(new HandlerHeartBeat());
                            /**
                             *
                             * 进行token校验
                             * 拦截channelRead时间
                             */
                            pipeline.addLast(handlerTokenValidation);
                            /**
                             * 添加websocket处理器
                             *
                             */
                            pipeline.addLast(new WebSocketServerProtocolHandler("/ws", null, true, 65536, true, true,10000));
                            /**
                             * websocket处理器
                             */
                            pipeline.addLast(handlerWebSocket);
                        }
                    });
            serverBootstrap.bind(8081).sync().channel().closeFuture().sync();
        }catch (Exception e){
            log.error("启动失败",e);
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    @PreDestroy
    private void close(){
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}

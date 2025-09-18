package com.weilai.streaming.webSocket;


import com.weilai.model.streaming.dtos.MessageSendDto;
import com.weilai.model.streaming.enums.MessageSend2TypeEnum;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.swagger.v3.core.util.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class ChannelContextUtils {
    public static ConcurrentHashMap<String, Channel> USER_CONTEXT_MAP = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, ChannelGroup> ROOM_CONTEXT_MAP = new ConcurrentHashMap<>();
    public void addContext(String userId, Channel channel){
        try {
            String channelId = channel.id().toString();
            AttributeKey<String> key = null;
            if(!AttributeKey.exists(channelId)){
                // 如果attributeKey不存在 则创建
                key = AttributeKey.newInstance(channelId);
            }else{
                // 如果attributeKey存在 则获取
                key = AttributeKey.valueOf(channelId);
            }
            // 将用户id和channel进行关联
            channel.attr(key).set(userId);
            USER_CONTEXT_MAP.put(userId,channel);
            //更新最后登录时间
            //断连接处理，自动加入会议
            //创建的假数据
            String meetingId = "meetingId";
            addRoom(meetingId,userId);
        }catch (Exception e){
            log.error("添加用户失败");
        }
    }
    public void addRoom(String roomId, String userId){
        Channel context = USER_CONTEXT_MAP.get(userId);
        if(context==null) return;
        ChannelGroup group = ROOM_CONTEXT_MAP.get(roomId);
        if(group==null){
            // 创建一个ChannelGroup
            group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
            ROOM_CONTEXT_MAP.put(roomId,group);
        }
        Channel channel = group.find(context.id());
        if(channel==null){
            group.add(context);
        }
    }
    // 发送消息
    public void sendMessage(MessageSendDto messageSendDto){
        if(MessageSend2TypeEnum.USER.getType().equals(messageSendDto.getMessageSend2Type())){
            sendMsg2User(messageSendDto);
        }else{
            sendMsg2Room(messageSendDto);
        }
    }
    // 发送给房间
    private void sendMsg2Room(MessageSendDto messageSendDto){
        if(messageSendDto.getRoomId()==null) return;
        ChannelGroup group = ROOM_CONTEXT_MAP.get(messageSendDto.getRoomId());
        if(group==null) return;
        group.writeAndFlush(new TextWebSocketFrame(Json.pretty(messageSendDto)));
    }
    // 发送给用户
    private void sendMsg2User(MessageSendDto messageSendDto){
        if(messageSendDto.getReceiveUserId()==null) return;
        Channel channel = USER_CONTEXT_MAP.get(messageSendDto.getReceiveUserId());
        if(channel==null) return;
        channel.writeAndFlush(new TextWebSocketFrame(Json.pretty(messageSendDto)));
    }
    // 关闭连接
    public void closeContext(String userId){
        if(userId.isEmpty()){
            return;
        }
        Channel channel = USER_CONTEXT_MAP.get(userId);
        USER_CONTEXT_MAP.remove(userId);
        if(channel==null) return;
        channel.close();
    }
}

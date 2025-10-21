package com.weilai.streaming.websocket;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.weilai.model.streaming.dos.LiveRoom;
import com.weilai.model.streaming.dtos.LiveUserDto;

import com.weilai.model.streaming.dtos.Message;
import com.weilai.model.streaming.enums.MessageTypeEnum;
import com.weilai.model.streaming.enums.StreamTypeEnum;
import com.weilai.streaming.service.LiveRoomService;
import com.weilai.streaming.service.impl.LiveRoomServiceImpl;
import jakarta.annotation.Resource;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * WebSocket端点，处理直播相关的实时通信
 */
@Component
@ServerEndpoint("/ws/live/{userId}")
@Slf4j
public class LiveWebSocket implements ApplicationContextAware {
    private static Map<String,Map<String,LiveUserDto>> rooms; //房间 roomId -> {userId,LiveUserDto}
    private static Map<String,LiveUserDto> userInfoMap; //用户ID -> LiveUserDto
    private static Map<String, Session> clients; //sessionId -> Session
    private static ApplicationContext applicationContexts;
    private static LiveRoomServiceImpl liveRoomService;
    // 当前连接的用户ID
    private String userId;

    @Resource
    public void setApplicationContext(ApplicationContext applicationContext) {
        applicationContexts = applicationContext;
        // 初始化静态变量
        rooms = applicationContexts.getBean("rooms", Map.class);
        userInfoMap = applicationContexts.getBean("userInfoMap", Map.class);
        clients = applicationContexts.getBean("clients", Map.class);
        liveRoomService = applicationContexts.getBean(LiveRoomServiceImpl.class);
    }

    /**
     * 连接建立时调用
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.userId = userId;
        System.out.println(clients);
        clients.put(session.getId(), session);
        log.info("用户连接: userId={}", userId);
    }

    /**
     * 连接关闭时调用
     */
    @OnClose
    public void onClose() {

        log.info("用户断开连接: userId={}", userId);
    }


    /**
     * 收到客户端消息时调用
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到用户消息: userId={}, message={}", userId, message);
        try {
            Message msg = JSONObject.parseObject(message, Message.class);
            switch(MessageTypeEnum.valueOf(msg.getType().toUpperCase())){
                case JOIN:
                    handleJoin(session, msg);
                    break;
                case OFFER:
                    handleOffer(session, msg);
                    break;
                case ANSWER:
                    handleAnswer(session, msg);
                    break;
                case ICE_CANDIDATE:
                    handleIceCandidate(session, msg);
                    break;
                case LEAVE:
                    handleLeave(session, msg);
                    break;
                default:
                    log.warn("Unknown message type: {}", msg.getType());
            }
        } catch (Exception e) {
            log.error("处理消息异常", e);
            sendMessage(userId, createMessage("error", "处理消息失败", null).toJSONString());
        }
    }

    private void handleLeave(Session session, Message msg) {
        log.info("用户离开: userId={}", msg.getUserId());
        LiveUserDto user = userInfoMap.get(msg.getUserId());
        if(user != null){
            if("anchor".equals(user.getRole())){
                LiveRoom liveRoom = liveRoomService.getById(msg.getRoomId());
                liveRoom.setViewCount(liveRoom.getViewCount()-1);
                liveRoomService.updateLiveRoom(liveRoom);
                 rooms.get(msg.getRoomId()).values().forEach(liveUserDto -> {
                     //清空当前观众的客户端连接
                     userInfoMap.remove(liveUserDto.getUserId());
                     sendMessage(liveUserDto.getSocketId(), createMessage("leave", msg.getNickName()+"主播已经关闭直播了", msg).toJSONString());
                 });
              rooms.remove(msg.getRoomId());
            }else{
                userInfoMap.remove(msg.getUserId());
                clients.remove(session.getId());
                rooms.get(msg.getRoomId()).remove(msg.getUserId());
                Map<String, LiveUserDto> room = rooms.get(msg.getRoomId());
                LiveUserDto anchorUser=new LiveUserDto();
                room.values().forEach(liveUserDto -> {
                    if(liveUserDto.getRole().equals("anchor")){
                        BeanUtils.copyProperties(liveUserDto,anchorUser);
                    }
                });
                sendMessage(anchorUser.getSocketId(), createMessage("leave", msg.getNickName()+"已经离开了房间", msg).toJSONString());
            }

        }

    }

    private void handleIceCandidate(Session session, Message msg) {
        log.info("收到用户发送的iceCandidate: userId={}, iceCandidate={}", msg.getUserId(), msg.getCandidate());
        String memberId = msg.getMemberId();
        Session targetSession = clients.get(memberId);
        if(isSessionValid(targetSession)){
            sendMessage(memberId, createMessage("icecandidate", null, msg).toJSONString());
        }
    }

    private void handleAnswer(Session session, Message msg) {
        log.info("收到用户发送的answer{},answer={}",msg.getUserId(),msg.getAnswer());
        String memberId = msg.getMemberId();
        Session targetSession = clients.get(memberId);
        if(isSessionValid(targetSession)){
            LiveUserDto senderUser = getUserBySocketId(session.getId());
            if(senderUser!=null){
                StreamTypeEnum streamType = StreamTypeEnum.valueOf(msg.getStreamType().toUpperCase());
                senderUser.getStreamConnections().computeIfAbsent(streamType,k->new ConcurrentHashMap<>())
                        .put(msg.getConnectorId(),memberId);
            }
            sendMessage(memberId,createMessage("answer",null,msg).toJSONString());
        }
    }

    private void handleOffer(Session session, Message msg) {
        log.info("收到用户offer: userId={}, offer={}", msg.getUserId(), msg.getOffer());
        String targetSocketId = msg.getMemberId();
        Session targetSession = clients.get(targetSocketId);
        if(isSessionValid(targetSession)){
            //获取发送者的用户信息
            LiveUserDto senderUser = getUserBySocketId(session.getId());
            if(senderUser != null){
                StreamTypeEnum streamType = StreamTypeEnum.valueOf(msg.getStreamType().toUpperCase());
                senderUser.getStreamConnections().computeIfAbsent(streamType, k -> new ConcurrentHashMap<>()).put(msg.getConnectorId(), targetSocketId);
            }
            sendMessage(targetSocketId, createMessage("offer", null, msg).toJSONString());
        }
    }

    private void handleJoin(Session session, Message msg) {
        /**
         * 用户加入房间,携带房间ID和角色，判断是主包还是观众
         */
        log.info("用户加入房间: userId={}, roomId={}", msg.getUserId(), msg.getRoomId());
        String roomId = msg.getRoomId();
        String userId = msg.getUserId();
        String nickName = msg.getNickName();
        if("anchor".equals(msg.getRoomId())){
            //初始化房间信息
            rooms.putIfAbsent(roomId, new ConcurrentHashMap<>());
            Map<String, LiveUserDto> room = rooms.get(roomId);
            //检查用户名是否重复
            boolean isRepeat = room.values().stream().anyMatch(liveUserDto -> liveUserDto.getNickName().equals(nickName));
            if(isRepeat){
                sendMessage(userId, createMessage("error", "用户名重复", msg).toJSONString());
                return;
            }
            //创建用户信息并且添加进房间
            LiveUserDto user= new LiveUserDto();
            user.setUserId(userId);
            user.setNickName(nickName);
            user.setRoomId(roomId);
            user.setSocketId(session.getId());
            room.put(userId, user);
            userInfoMap.put(userId, user);
        }else if("audience".equals(msg.getRole())){
            Map<String, LiveUserDto> room = rooms.get(roomId);
            LiveUserDto anchorUser=new LiveUserDto();
            room.values().forEach(liveUserDto -> {
                if(liveUserDto.getRole().equals("anchor")){
                    BeanUtils.copyProperties(liveUserDto,anchorUser);
                }
            });
            //检查用户名是否重复
            boolean isRepeat = room.values().stream().anyMatch(liveUserDto -> liveUserDto.getNickName().equals(nickName));
            if(isRepeat){
                sendMessage(userId, createMessage("error", "用户名重复", msg).toJSONString());
                return;
            }
            //创建用户信息并且添加进房间
            LiveUserDto user= new LiveUserDto();
            user.setUserId(userId);
            user.setNickName(nickName);
            user.setRoomId(roomId);
            user.setSocketId(session.getId());
            room.put(userId, user);
            userInfoMap.put(userId, user);
            msg.setFromUserId(anchorUser.getUserId());
            sendMessage(session.getId(),createMessage("join", null, msg).toJSONString());
        }
    }

    /**
     * 向指定用户发送消息
     */
    private void sendMessage(String socketId, String message) {
        Session session = clients.get(socketId);
        if (session != null && session.isOpen()) {
            try {
                log.info("发送消息: socketId={}, message={}", socketId, message);
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("发送消息失败: socketId={}", socketId, e);
            }
        }
    }


    /**
     * 创建标准化消息
     */
    private JSONObject createMessage(String type, String content , Message msg) {
        JSONObject message = new JSONObject();
        message.put("type", type);
        message.put("content", content);
        message.put("msg", JSON.toJSONString(msg));
        message.put("timestamp", System.currentTimeMillis());
        return message;
    }
    // 工具方法：检查会话是否有效
    private boolean isSessionValid(Session session) {
        return session != null && session.isOpen();
    }
    // 工具方法：通过socketId获取用户
    private LiveUserDto getUserBySocketId(String socketId) {
        return userInfoMap.values().stream()
                .filter(u -> socketId.equals(u.getSocketId()))
                .findFirst()
                .orElse(null);
    }
}
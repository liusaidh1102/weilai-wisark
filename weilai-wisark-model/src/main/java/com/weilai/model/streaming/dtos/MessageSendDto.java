package com.weilai.model.streaming.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageSendDto {
    private String id; // 用户ID
    private String username; // 用户名
    private String roomId; // 房间ID
    private String messageType; // 消息类型(对应原枚举)
    private String messageSend2Type; // 发送类型(用户/房间)
    private String content; // 普通消息内容

    // WebRTC信令相关字段
    private String connectorId; // 连接器ID
    private String remoteConnectorId; // 远程连接器ID
    private String offer; // SDP offer
    private String answer; // SDP answer
    private String candidate; // ICE候选者
    private String streamType; // 流类型(USER/DISPLAY/REMOTE_DISPLAY)
    private String memberId; // 目标成员ID
    private String type; // 重连等操作类型
}

package com.weilai.model.streaming.dtos;

import lombok.Data;

@Data
public class Message {
    private String type;
    private String connectorId;
    private String remoteConnectorId;
    private String offer;
    private String answer;
    private Object candidate;
    private String memberId;
    private String streamType;
    private String nickName;
    private String fromUserId;
    private String roomId;
    private String role;
    private String message;
    private String userId;
}

package com.weilai.model.streaming.enums;

import lombok.Getter;

@Getter
public enum MessageTypeEnum {
    JOIN_ROOM("join"),
    LEAVE_ROOM("leave"),
    CHAT_MESSAGE("chatMessage"),
    HEARTBEAT("heartbeat"),
    // 新增WebRTC信令类型
    OFFER("offer"),
    ANSWER("answer"),
    GET_OFFER("getOffer"),
    ICE_CANDIDATE("icecandidate"),
    RECONNECT("reconnect"),
    RECONNECT_WORK("reconnectWork"),
    ERROR("error");

    private final String type;
    MessageTypeEnum(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public static MessageTypeEnum getByType(String type) {
        for (MessageTypeEnum e : values()) {
            if (e.type.equals(type)) return e;
        }
        return null;
    }
}

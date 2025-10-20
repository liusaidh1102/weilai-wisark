package com.weilai.model.streaming.enums;

import lombok.Getter;

@Getter
public enum MessageTypeEnum {
    OFFER("offer"),
    ANSWER("answer"),
    GET_OFFER("getOffer"),
    ICE_CANDIDATE("icecandidate"),
    LEAVE("leave"),
    JOIN("join"),
    RECONNECT("reconnect"),
    RECONNECT_WORK("reconnectWork"),
    ERROR("error");

    private final String value;

    MessageTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

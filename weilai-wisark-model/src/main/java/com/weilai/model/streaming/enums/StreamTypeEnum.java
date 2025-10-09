package com.weilai.model.streaming.enums;

public enum StreamTypeEnum {
    USER("user"),
    DISPLAY("display"),
    REMOTE_DISPLAY("remoteDisplay");

    private final String type;
    StreamTypeEnum(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}

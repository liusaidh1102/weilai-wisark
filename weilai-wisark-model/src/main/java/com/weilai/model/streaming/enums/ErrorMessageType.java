package com.weilai.model.streaming.enums;

public enum ErrorMessageType {
    REPEAT("repeat");

    private final String type;
    ErrorMessageType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}

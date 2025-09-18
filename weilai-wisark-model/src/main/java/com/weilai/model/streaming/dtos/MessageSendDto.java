package com.weilai.model.streaming.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageSendDto<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer messageSend2Type;
    private String roomId;
    private Integer messageType;
    private String sendUserId;
    private String sendUserName;
    private T messageContent;
    private String receiveUserId;
    private Long sendTime;
    private Long messageId;
    private Integer status;
    private String fileName;
    private Integer fileType;
    private Long fileSize;
}

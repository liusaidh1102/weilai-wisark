package com.weilai.common.response;

import lombok.Getter;

/**
 * 状态码
 */
@Getter
public enum CodeEnum {


    SUCCESS(200,"操作成功"),

    INTERNAL_SERVER_ERROR(500,"服务器异常");

    private final Integer code;

    private final String msg;


    CodeEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
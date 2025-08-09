package com.weilai.common.response;

import lombok.Getter;

/**
 * 状态码
 */
@Getter
public enum CodeEnum {


    SUCCESS(200,"操作成功"),
    BAD_REQUEST(400,"操作失败"),
    UNAUTHORIZED(401,"未授权"),
    NO_RESOURCE_FOUND(404,"未找到资源"),
    INTERNAL_SERVER_ERROR(500,"服务器异常"),

    // 添加其他状态码  用户服务 600 - 700
    CODE_EXISTS(600,"请勿重复发送验证码"),
    ERROR_CODE(601,"手机号或验证码错误"),
    ERROR_PWD(602,"密码错误"),











    ;

    private final Integer code;

    private final String msg;


    CodeEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
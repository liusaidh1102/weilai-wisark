package com.weilai.common.response;

import lombok.Getter;

/**
 * 状态码
 */
@Getter
public enum CodeEnum {

    KICKED_OUT(-1,"账号在别处登录"),

    SUCCESS(200,"操作成功"),
    BAD_REQUEST(400,"操作失败"),
    UNAUTHORIZED(401,"未授权"),
    NO_RESOURCE_FOUND(404,"未找到资源"),
    INTERNAL_SERVER_ERROR(500,"服务器异常"),

    // 添加其他状态码  用户服务 600 - 700,
    CODE_EXISTS(600,"请勿重复发送验证码"),
    ERROR_CODE(601,"手机号或验证码错误"),
    ERROR_PWD(602,"密码错误"),
    USER_NOT_EXISTS(603,"用户不存在"),
    DATE_NOT_EXISTS(604,"数据不存在"),
    PERMISSION_DENIED(605,"权限不足"),


    // 文件服务
    FILE_NOT_EXISTS(700,"文件不存在"),
    FILE_UPLOAD_ING(701,"文件断点续传"),
    FILE_ERROR(703,"文件上传失败"),
    FILE_OK(704,"文件上传成功"),
    FILE_INIT_OK(705,"初始化成功"),










    ;

    private final Integer code;

    private final String msg;


    CodeEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
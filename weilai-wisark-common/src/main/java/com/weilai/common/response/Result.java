package com.weilai.common.response;
import lombok.Data;
import java.io.Serializable;

import static com.weilai.common.response.CodeEnum.SUCCESS;

/**
 * 统一返回结果
 */
@Data
public class Result<T> implements Serializable {


    private static final long serialVersionUID = 1L;


    private final Integer code; //状态码

    private final String message;  //信息

    private final T data; //数据


    public static <T> Result<T> ok() {
        return Result.ok(SUCCESS);
    }

    public static <T> Result<T> ok(T data) {
        return Result.ok(SUCCESS,data);
    }

    public static <T> Result<T> ok(CodeEnum codeEnum) {
        return new Result<>(codeEnum.getCode(),codeEnum.getMsg(),null);
    }

    public static <T> Result<T> ok(CodeEnum codeEnum,T data) {
        return new Result<>(codeEnum.getCode(),codeEnum.getMsg(),data);
    }

    public static <T> Result<T> ok(CodeEnum codeEnum,String msg,T data) {
        return new Result<>(codeEnum.getCode(),msg,data);
    }


    public static <T> Result<T> fail() {
        return Result.ok(CodeEnum.INTERNAL_SERVER_ERROR);
    }

    public static <T> Result<T> fail(CodeEnum codeEnum,String msg) {
        return new Result<>(codeEnum.getCode(),msg,null);
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<>(CodeEnum.INTERNAL_SERVER_ERROR.getCode(),msg,null);
    }

    public static <T> Result<T> fail(CodeEnum codeEnum) {
        return new Result<>(codeEnum.getCode(),codeEnum.getMsg(),null);
    }


}
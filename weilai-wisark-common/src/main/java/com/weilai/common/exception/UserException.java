package com.weilai.common.exception;

import com.weilai.common.response.CodeEnum;
import lombok.Getter;

@Getter
public class UserException extends RuntimeException{

    private final CodeEnum codeEnum;

    public UserException(CodeEnum codeEnum){
        this.codeEnum = codeEnum;
    }

}

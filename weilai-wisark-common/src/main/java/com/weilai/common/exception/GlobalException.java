package com.weilai.common.exception;
import com.weilai.common.response.CodeEnum;
import com.weilai.common.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import static com.weilai.common.response.CodeEnum.BAD_REQUEST;
import static com.weilai.common.response.CodeEnum.NO_RESOURCE_FOUND;

@RestControllerAdvice
@Slf4j
public class GlobalException{

    /**
     * 处理其他未捕获异常
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(UserException.class)
    public Result<?> exception(UserException e){
        e.printStackTrace();
        return Result.fail(e.getCodeEnum());
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public Result<?> HandlerMethodValidationException(HandlerMethodValidationException e){
        e.printStackTrace();
        return Result.fail(BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public Result<?> HandlerMethodValidationException(NoResourceFoundException e){
        e.printStackTrace();
        return Result.fail(NO_RESOURCE_FOUND);
    }




}

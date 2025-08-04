package com.weilai.common.exception;
import com.weilai.common.response.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalException{

    /**
     * 处理其他未捕获异常
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        return Result.fail();
    }

    @ExceptionHandler(UserException.class)
    public Result<?> exception(UserException e){
        return Result.fail(e.getCodeEnum());
    }

}

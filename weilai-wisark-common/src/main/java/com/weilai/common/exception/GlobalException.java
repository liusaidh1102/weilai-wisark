package com.weilai.common.exception;
import com.weilai.common.response.CodeEnum;
import com.weilai.common.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import static com.weilai.common.response.CodeEnum.BAD_REQUEST;
import static com.weilai.common.response.CodeEnum.NO_RESOURCE_FOUND;

@RestControllerAdvice
@Slf4j
public class GlobalException{


    @ExceptionHandler(NoResourceFoundException.class)
    public Result<?> handleNoResourceFoundException(NoResourceFoundException e){
        e.printStackTrace();
        return Result.fail(NO_RESOURCE_FOUND);
    }


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

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        e.printStackTrace();
        return Result.fail(CodeEnum.NO_RESOURCE_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        e.printStackTrace();
        return Result.fail(CodeEnum.BAD_REQUEST,"请求方式不合法");
    }

    /**
     * 系统异常
     * @param e
     * @return
     */
    @ExceptionHandler(SystemException.class)
    public Result<?> handleSystemException(SystemException e) {
        e.printStackTrace();
        return Result.fail(CodeEnum.INTERNAL_SERVER_ERROR);
    }

}

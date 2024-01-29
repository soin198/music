package org.soin.website.common.exception;

import org.soin.core.biz.common.GenericResponse;
import org.soin.core.util.RunTimeTool;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 用戶端异常拦截器
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-29 14:18
 **/
@RestControllerAdvice
public class GlobalExceptionHandler<T> {

    @ExceptionHandler(value = BizErrorException.class)
    public GenericResponse<?> exception(BizErrorException bizErrorException) {
        return new GenericResponse<T>(HttpStatus.EXPECTATION_FAILED.value(), bizErrorException.getMessage());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public GenericResponse<?> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        RunTimeTool.printError(illegalArgumentException);
        return new GenericResponse<T>(HttpStatus.BAD_REQUEST.value(), illegalArgumentException.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    public GenericResponse<?> handleBindException(Exception exception) {
        RunTimeTool.printError(exception);
        return new GenericResponse<T>(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }

}

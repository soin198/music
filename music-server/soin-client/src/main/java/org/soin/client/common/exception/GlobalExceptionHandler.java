package org.soin.client.common.exception;

import org.soin.core.infrastructure.base.constant.CodeConstant;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.soin.core.infrastructure.utils.RunTimeTool;
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
        return new GenericResponse<T>(CodeConstant.ERROR_CODE, bizErrorException.getMessage());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public GenericResponse<?> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        RunTimeTool.printError(illegalArgumentException);
        return new GenericResponse<T>(CodeConstant.ERROR_CODE, illegalArgumentException.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    public GenericResponse<?> handleBindException(Exception exception) {
        RunTimeTool.printError(exception);
        exception.printStackTrace();
        return new GenericResponse<T>(CodeConstant.ERROR_CODE, "系统繁忙");
    }

}

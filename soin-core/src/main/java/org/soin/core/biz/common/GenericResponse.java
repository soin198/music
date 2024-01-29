package org.soin.core.biz.common;

import lombok.Data;

/**
 * 响应体
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 15:27
 **/
@Data
public class GenericResponse<T> {

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T items;

    public GenericResponse() {
    }

    public GenericResponse(Integer code, String message, T items) {
        this.code = code;
        this.message = message;
        this.items = items;
    }

    public GenericResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.items = null;
    }

    public static Builder builder() {
        return new Builder();
    }

    @SuppressWarnings("all")
    public static class Builder {

        public GenericResponse build() {
            return new GenericResponse();
        }

        public GenericResponse success() {
            return new GenericResponse<>(CommonCode.SUCCESS_CODE, "响应成功");
        }

        public <T> GenericResponse<T> success(T item) {
            return new GenericResponse<>(CommonCode.SUCCESS_CODE, "响应成功", item);
        }

    }

}

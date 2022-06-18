package com.cm.util;

import lombok.Getter;

@Getter
public class ResultJson<T> {
    private Integer code;
    private String message;
    private T obj;

    private ResultJson(ResultCode resultCode, T obj) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.obj = obj;
    }

    private ResultJson(ResultCode resultCode, T obj, String message) {
        this.code = resultCode.getCode();
        this.message = message;
        this.obj = obj;
    }

    public static <T> ResultJson<T> getInstance(ResultCode resultCode, T obj) {
        return new ResultJson<>(resultCode, obj);
    }

    public static <T> ResultJson<T> getInstance(ResultCode resultCode, T obj, String message) {
        return new ResultJson<>(resultCode, obj, message);
    }

    public static <T> ResultJson<T> success(T obj) {
        return getInstance(ResultCode.SUCCESS, obj);
    }

    public static <T> ResultJson<T> success(T obj, String message) {
        return getInstance(ResultCode.SUCCESS, obj, message);
    }

    public static <T> ResultJson<T> error() {
        return getInstance(ResultCode.ERROR, null);
    }

    public static <T> ResultJson<T> error(String message) {
        return getInstance(ResultCode.ERROR, null, message);
    }
}

package com.cm.config;

import com.cm.util.ResultJson;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultException {
    // AOP 统一的错误处理
    @ExceptionHandler
    public ResultJson defaultExceptionHandler(Exception e) {
        e.printStackTrace();
        return ResultJson.error(e.getMessage());
    }
}

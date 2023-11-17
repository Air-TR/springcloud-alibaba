package com.tr.auth.config.exception;

import com.tr.auth.common.exception.BusinessException;
import com.tr.auth.common.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @Author: TR
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handle(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity handle(BusinessException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(InvalidGrantException.class)
    public ResponseEntity handle(InvalidGrantException e) {
        if ("Bad credentials".equals(e.getMessage())) {
            return ResponseEntity.badRequest().body(ResultEnum.WRONG_PASSWORD.getMsg());
        }
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handle(BadCredentialsException e) {
        return ResponseEntity.badRequest().body(ResultEnum.WRONG_PASSWORD.getMsg());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handle(AccessDeniedException e) {
        return ResponseEntity.status(403).body(ResultEnum.NO_ACCESS.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handle(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}

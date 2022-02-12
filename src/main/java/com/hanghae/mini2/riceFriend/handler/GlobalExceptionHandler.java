package com.hanghae.mini2.riceFriend.handler;

import com.hanghae.mini2.riceFriend.handler.ex.ErrorResponse;
import com.hanghae.mini2.riceFriend.handler.ex.InvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<ErrorResponse> handleInvalidException(InvalidException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getErrorCode(), e.getMessage())
                , HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }
}

package com.hanghae.mini2.riceFriend.handler;

import com.hanghae.mini2.riceFriend.handler.ex.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<ErrorResponse> handleInvalidException(InvalidException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getErrorCode(), e.getMessage())
                , HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmailNotFoundException(EmailNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getErrorCode(), e.getMessage())
                , HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.BAD_REQUEST, Objects.requireNonNull(e.getFieldError()).getDefaultMessage())
                , HttpStatus.valueOf(ErrorCode.BAD_REQUEST.getStatus()));
    }

    @ExceptionHandler(MeetingRequestException.class)
    public ResponseEntity<ErrorResponse> handleMeetingRequestException(MeetingRequestException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getErrorCode(), e.getMessage())
                , HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

}

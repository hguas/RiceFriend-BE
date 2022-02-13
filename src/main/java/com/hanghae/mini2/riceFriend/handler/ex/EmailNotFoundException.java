package com.hanghae.mini2.riceFriend.handler.ex;

import lombok.Getter;

@Getter
public class EmailNotFoundException extends RuntimeException{

    private ErrorCode errorCode;

    public EmailNotFoundException(String message) {
        super(message);
        this.errorCode = ErrorCode.BAD_REQUEST;
    }
}

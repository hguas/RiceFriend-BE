package com.hanghae.mini2.riceFriend.handler.ex;

import lombok.Getter;

@Getter
public class MeetingRequestException extends RuntimeException {

    private ErrorCode errorCode;

    public MeetingRequestException(String message) {
        super(message);
        this.errorCode = ErrorCode.BAD_REQUEST;
    }
}

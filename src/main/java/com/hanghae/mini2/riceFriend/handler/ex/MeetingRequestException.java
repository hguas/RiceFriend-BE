package com.hanghae.mini2.riceFriend.handler.ex;

import lombok.Getter;

@Getter
public class MeetingRequestException extends RuntimeException {

    public MeetingRequestException(String message) {
        super(message);
    }
}

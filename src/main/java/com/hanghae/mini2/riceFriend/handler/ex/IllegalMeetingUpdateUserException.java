package com.hanghae.mini2.riceFriend.handler.ex;

import lombok.Getter;

@Getter
public class IllegalMeetingUpdateUserException extends RuntimeException {
    public IllegalMeetingUpdateUserException(String message) {
        super(message);
    }
}

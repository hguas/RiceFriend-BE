package com.hanghae.mini2.riceFriend.handler.ex;

import lombok.Getter;

@Getter
public class IllegalMeetingDeleteUserException extends RuntimeException {
    public IllegalMeetingDeleteUserException(String message) {
        super(message);
    }
}

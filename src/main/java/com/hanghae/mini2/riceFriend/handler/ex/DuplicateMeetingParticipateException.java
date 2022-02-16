package com.hanghae.mini2.riceFriend.handler.ex;

import lombok.Getter;

@Getter
public class DuplicateMeetingParticipateException extends RuntimeException {
    public DuplicateMeetingParticipateException(String message) {
        super(message);
    }
}

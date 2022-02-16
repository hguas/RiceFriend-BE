package com.hanghae.mini2.riceFriend.handler.ex;

import lombok.Getter;

@Getter
public class MeetingParticipationNotFoundException extends RuntimeException {
    public MeetingParticipationNotFoundException(String message) {
        super(message);
    }
}

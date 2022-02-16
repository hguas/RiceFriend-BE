package com.hanghae.mini2.riceFriend.handler.ex;

import lombok.Getter;

@Getter
public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(String message) {
        super(message);
    }
}

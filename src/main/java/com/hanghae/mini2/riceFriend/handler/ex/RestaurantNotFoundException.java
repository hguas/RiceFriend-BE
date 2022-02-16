package com.hanghae.mini2.riceFriend.handler.ex;

import lombok.Getter;

@Getter
public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(String message) {
        super(message);
    }
}

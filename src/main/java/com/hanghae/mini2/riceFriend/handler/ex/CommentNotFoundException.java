package com.hanghae.mini2.riceFriend.handler.ex;

import lombok.Getter;

@Getter
public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(String message) {
        super(message);
    }
}

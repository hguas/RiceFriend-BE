package com.hanghae.mini2.riceFriend.handler.ex;

import lombok.Getter;

@Getter
public class IllegalCommentDeleteUserException extends RuntimeException {
    public IllegalCommentDeleteUserException(String message) {
        super(message);
    }
}

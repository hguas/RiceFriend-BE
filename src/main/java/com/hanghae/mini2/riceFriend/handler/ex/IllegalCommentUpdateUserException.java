package com.hanghae.mini2.riceFriend.handler.ex;

import lombok.Getter;

@Getter
public class IllegalCommentUpdateUserException extends RuntimeException {
    public IllegalCommentUpdateUserException(String message) {
        super(message);
    }
}

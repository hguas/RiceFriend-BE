package com.hanghae.mini2.riceFriend.handler.ex;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {

    private String code;
    private String message;
    private String result;

    public ErrorResponse(ErrorCode errorCode, String message) {
        this.code = errorCode.getErrorcode();
        this.message = message;
        result = "false";
    }
}

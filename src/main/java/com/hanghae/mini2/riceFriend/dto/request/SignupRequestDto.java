package com.hanghae.mini2.riceFriend.dto.request;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {

    public String nickname;

    public String email;

    public String password;

    public String passwordCheck;

    public String gender;
}

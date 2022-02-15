package com.hanghae.mini2.riceFriend.dto.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class SignupRequestDto {

    @NotBlank(message = "닉네임을 입력해주세요.")
    public String nickname;

    @NotBlank(message = "이메일을 입력해주세요.")
    public String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    public String password;

    @NotBlank(message = "비밀번호를 한번 더 입력해주세요.")
    public String passwordCheck;

    @NotBlank(message = "성별을 선택해주세요.")
    public String gender;
}

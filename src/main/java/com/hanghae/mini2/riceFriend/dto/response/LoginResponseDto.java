package com.hanghae.mini2.riceFriend.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {

    @ApiModelProperty(value = "응답 여부")
    public String result;
    @ApiModelProperty(value = "토큰 정보")
    public String token;
}

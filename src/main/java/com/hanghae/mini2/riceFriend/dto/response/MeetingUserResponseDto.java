package com.hanghae.mini2.riceFriend.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@ApiModel(value = "모임 참여자 정보", description = "모임 참여자 정보 ResponseDto Class")
public class MeetingUserResponseDto {
    //USER 테이블
    @ApiModelProperty(value = "참여자 이메일")
    private String email;
    @ApiModelProperty(value = "참여자 닉네임")
    private String nickname;
    @ApiModelProperty(value = "참여자 성별")
    private String gender;
}

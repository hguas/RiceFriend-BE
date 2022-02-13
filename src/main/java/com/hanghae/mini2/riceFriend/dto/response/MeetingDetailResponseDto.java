package com.hanghae.mini2.riceFriend.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApiModel(value = "모임 상세 정보", description = "모임 상세 정보 ResponseDto Class")
public class MeetingDetailResponseDto {
    @ApiModelProperty(value = "모임정보")
    private MeetingResonseDto meetingResonseDto;

    //COMMENT 테이블
    @ApiModelProperty(value = "댓글목록")
    private List<CommentResponseDto> commentResponseDtos = new ArrayList<>();

    @ApiModelProperty(value = "모임 참여자 목록")
    List<MeetingUserResponseDto> users = new ArrayList<>();
}

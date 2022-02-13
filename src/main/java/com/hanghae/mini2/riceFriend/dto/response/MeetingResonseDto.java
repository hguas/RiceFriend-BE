package com.hanghae.mini2.riceFriend.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel(value = "모임 정보", description = "모임 정보 ResponseDto Class")
public class MeetingResonseDto {
    //USER 테이블
    @ApiModelProperty(value = "등록자 닉네임")
    private Long nickname;

    //RESTAURANT 테이블
    @ApiModelProperty(value = "등록자_ID")
    private Long userId;
    @ApiModelProperty(value = "이미지_url")
    private String imgUrl;
    @ApiModelProperty(value = "지역_ID")
    private Long locationId;
    @ApiModelProperty(value = "모집인원")
    private int limitMember;

    //MEETING 테이블
    @ApiModelProperty(value = "모임제목")
    private String meetingTitle;
    @ApiModelProperty(value = "모임내용")
    private String content;
    @ApiModelProperty(value = "모임날짜")
    private LocalDateTime meetingDate;

    // ETC
    @ApiModelProperty(value = "참여인원")
    private int memberCount;
    @ApiModelProperty(value = "댓글개수")
    private int commentCount;
}

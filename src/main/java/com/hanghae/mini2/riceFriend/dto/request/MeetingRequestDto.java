package com.hanghae.mini2.riceFriend.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel(value = "모임 정보", description = "모임 정보 ResponseDto Class")
public class MeetingRequestDto {
    @ApiModelProperty(value = "모임제목")
    private String meetingTitle;
    @ApiModelProperty(value = "맛집이름")
    private String restaurantName;
    @ApiModelProperty(value = "이미지_url")
    private String restaurantUrl;
    @ApiModelProperty(value = "모임내용")
    private String content;
    @ApiModelProperty(value = "모임날짜")
    private LocalDateTime meetingDate;
    @ApiModelProperty(value = "지역_ID")
    private Long locationId;
    @ApiModelProperty(value = "모집인원")
    private int limitMember;
}

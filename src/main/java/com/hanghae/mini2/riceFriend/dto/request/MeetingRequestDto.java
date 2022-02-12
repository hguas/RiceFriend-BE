package com.hanghae.mini2.riceFriend.dto.request;

import com.hanghae.mini2.riceFriend.model.Location;
import com.hanghae.mini2.riceFriend.model.Meeting;
import com.hanghae.mini2.riceFriend.model.Restaurant;
import com.hanghae.mini2.riceFriend.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel(value = "모임 정보", description = "모임 정보 ResponseDto Class")
public class MeetingRequestDto {
    // Restaurant 테이블
    @ApiModelProperty(value = "맛집이름")
    private String restaurantName;
    @ApiModelProperty(value = "이미지_url")
    private String restaurantUrl;
    @ApiModelProperty(value = "지역_ID")
    private Long locationId;

    // Meeting 테이블
    @ApiModelProperty(value = "모임제목")
    private String meetingTitle;
    @ApiModelProperty(value = "모임내용")
    private String content;
    @ApiModelProperty(value = "모임날짜")
    private LocalDateTime meetingDate;
    @ApiModelProperty(value = "모집인원")
    private int limitMember;

    public Restaurant toRestaurantEntity(Location location, User user) {
        return Restaurant.builder()
                .name(this.restaurantName)
                .imageUrl(this.restaurantUrl)
                .location(location)
                .user(user)
                .build();
    }

    public Meeting toMeetingEntity(User user, Restaurant restaurant) {
        return Meeting.builder()
                .title(this.meetingTitle)
                .content(this.content)
                .date(this.meetingDate)
                .limitMember(this.limitMember)
                .user(user)
                .restaurant(restaurant)
                .build();
    }
}

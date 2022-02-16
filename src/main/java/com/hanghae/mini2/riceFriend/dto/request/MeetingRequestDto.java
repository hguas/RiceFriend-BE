package com.hanghae.mini2.riceFriend.dto.request;

import com.hanghae.mini2.riceFriend.model.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ApiModel(value = "모임 정보", description = "모임 정보 ResponseDto Class")
public class MeetingRequestDto {
    // Restaurant 테이블
    @NotBlank(message = "음식점 이름을 작성해주세요.")
    @ApiModelProperty(value = "맛집이름")
    private String restaurantName;
    @NotNull(message = "지역을 선택해주세요.")
    @ApiModelProperty(value = "지역_ID")
    private Long locationId;
    // Meeting 테이블
    @NotBlank(message = "모임 제목을 작성해주세요.")
    @ApiModelProperty(value = "모임제목")
    private String meetingTitle;
    @NotBlank(message = "모임 내용을 작성해주세요.")
    @ApiModelProperty(value = "모임내용")
    private String content;
    @NotNull(message = "모임 날짜를 입력해주세요.")
    @ApiModelProperty(value = "모임날짜")
    private LocalDateTime meetingDate;
    @NotNull(message = "모집 인원을 선택해주세요.")
    @ApiModelProperty(value = "모집인원")
    private int limitMember;

    public Restaurant toRestaurantEntity(Location location, String imgUrl, User user) {
        return Restaurant.builder()
                .name(this.restaurantName)
                .imageUrl(imgUrl)
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

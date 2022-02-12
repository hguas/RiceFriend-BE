package com.hanghae.mini2.riceFriend.model;

import com.hanghae.mini2.riceFriend.dto.response.MeetingUserResponseDto;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "meeting_user")
@ApiModel(value = "MEETING_USER_TABLE", description = "모임 참가자 정보 TABLE")
public class MeetingUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public MeetingUserResponseDto toMeetingUserResponseDto() {
        return MeetingUserResponseDto.builder()
                .nickname(this.user.getNickname())
                .gender(this.user.getGender())
                .build();
    }
}

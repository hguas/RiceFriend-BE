package com.hanghae.mini2.riceFriend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hanghae.mini2.riceFriend.dto.response.MeetingResonseDto;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "meeting")
@ApiModel(value = "MEETING_TABLE", description = "모임 정보 TABLE")
public class Meeting extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private LocalDateTime date;

    @Column(name = "limit_member")
    private int limitMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restuarant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"meeting"})
    @OrderBy("id desc")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "meeting")
    @JsonIgnoreProperties({"meeting"})
    private List<MeetingUser> meetingUsers = new ArrayList<>();

    public MeetingResonseDto toMeetingDetailResponseDto() {
        int commentCount = this.comments.size();// 댓글개수
        int memberCount = this.meetingUsers.size();// 참여인원

        return MeetingResonseDto.builder()
                .nickname(this.user.getNickname())
                .userId(this.user.getId())
                .imgUrl(this.restaurant.getImageUrl())
                .locationId(this.restaurant.getLocation().getId())
                .meetingId(this.getId())
                .meetingTitle(this.getTitle())
                .content(this.getContent())
                .meetingDate(this.getDate())
                .limitMember(this.getLimitMember())
                .commentCount(commentCount)
                .memberCount(memberCount)
                .build();
    }
}

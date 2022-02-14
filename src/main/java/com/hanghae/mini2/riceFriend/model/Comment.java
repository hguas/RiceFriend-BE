package com.hanghae.mini2.riceFriend.model;

import com.hanghae.mini2.riceFriend.dto.request.CommentRequestDto;
import com.hanghae.mini2.riceFriend.dto.response.CommentResponseDto;
import com.hanghae.mini2.riceFriend.dto.response.MeetingUserResponseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "comment")
@ApiModel(value = "COMMENT_TABLE", description = "댓글 TABLE")
public class Comment extends Timestamp {   // 생성, 수정시간을 자동으로 만들어줌

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


//    public Comment(String content, Meeting meeting, User user) {
//        this.content = content;
//        this.meeting = meeting;
//        this.user = user;
//    }

    public CommentResponseDto toCommentResponseDto() {
        return CommentResponseDto.builder()
                .nickname(this.user.getNickname())
                .content(this.content)
                .createdAt(this.getCreatedAt())
                .build();
    }

    public void updateComment (CommentRequestDto requestDto) {
        this.content = requestDto.getContent();

    }


}
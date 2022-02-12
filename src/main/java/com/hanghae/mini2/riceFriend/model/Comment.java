package com.hanghae.mini2.riceFriend.model;

import com.hanghae.mini2.riceFriend.dto.response.CommentResponseDto;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "comment")
@ApiModel(value = "COMMENT_TABLE", description = "댓글 TABLE")
public class Comment extends Timestamp {

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

    public CommentResponseDto toCommentResponseDto() {
        return CommentResponseDto.builder()
                .nickname(this.user.getNickname())
                .content(this.content)
                .createdAt(this.getCreatedAt())
                .build();
    }
}

package com.hanghae.mini2.riceFriend.dto.request;

import com.hanghae.mini2.riceFriend.model.Comment;
import com.hanghae.mini2.riceFriend.model.Meeting;
import com.hanghae.mini2.riceFriend.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel(value = "댓글 정보", description = "댓글 정보 ResponseDto Class")
public class CommentRequestDto {

    @ApiModelProperty(value = "댓글내용")
    @NotBlank(message = "댓글 내용을 입력해주세요.")
    private String content;

    public Comment toCommentEntity(Meeting meeting, User user, String content) {
        return Comment.builder()
                .content(content)
                .meeting(meeting)
                .user(user)
                .build();
    }

}
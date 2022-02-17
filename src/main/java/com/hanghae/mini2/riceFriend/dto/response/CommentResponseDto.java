package com.hanghae.mini2.riceFriend.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Getter
@Setter
@ApiModel(value = "댓글 정보", description = "댓글 정보 ResponseDto Class")
public class CommentResponseDto {
    //COMMENT 테이블
    @ApiModelProperty(value = "댓글 ID")
    private Long commentId;
    @ApiModelProperty(value = "댓글 작성자 닉네임")
    private String nickname;
    @ApiModelProperty(value = "댓글 내용")
    private String content;
    @ApiModelProperty(value = "댓글 작성일자")
    private LocalDateTime createdAt;

    // 생성자 생략

}

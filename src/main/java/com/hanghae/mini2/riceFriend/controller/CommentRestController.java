package com.hanghae.mini2.riceFriend.controller;

import com.hanghae.mini2.riceFriend.config.auth.PrincipalDetails;
import com.hanghae.mini2.riceFriend.dto.request.CommentRequestDto;
import com.hanghae.mini2.riceFriend.handler.ex.LoginUserNotFoundException;
import com.hanghae.mini2.riceFriend.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@Api("Comment Controller API")
@RequiredArgsConstructor
@RestController
public class CommentRestController {

    private final CommentService commentService;

    //댓글 작성
    @PostMapping("/api/meeting/{meeting_id}/comments")
    @ApiOperation(value = "댓글 작성", notes = "댓글을 작성")
    public HashMap<String, Object> createComment(@PathVariable Long meeting_id, @RequestBody @Valid CommentRequestDto requestDto,
                                                 @AuthenticationPrincipal PrincipalDetails userDetails) {

        if (userDetails == null)
            throw new LoginUserNotFoundException("로그인한 유저 정보가 없습니다.");

        HashMap<String, Object> result = new HashMap<>();

        commentService.createComment(meeting_id, requestDto, userDetails.getUser().getId());
        result.put("result", "true");

        return result;
    }

    //댓글 수정
    @PutMapping("/api/meeting/{meeting_id}/comments/{comment_id}")
    @ApiOperation(value = "댓글 수정", notes = "댓글을 수정")
    public HashMap<String, Object> updateComment(@PathVariable Long comment_id, @RequestBody @Valid CommentRequestDto requestDto, @AuthenticationPrincipal PrincipalDetails userDetails) {
        HashMap<String, Object> result = new HashMap<>();

        if (userDetails == null)
            throw new LoginUserNotFoundException("로그인한 유저 정보가 없습니다.");

        commentService.updateComment(comment_id, requestDto, userDetails.getUser().getId());
        result.put("result", "true");

        return result;
    }

    //댓글 삭제
    @DeleteMapping("/api/meeting/{meeting_id}/comments/{comment_id}")
    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제")
    public HashMap<String, Object> deleteComment(@PathVariable Long meeting_id, @PathVariable Long comment_id, @AuthenticationPrincipal PrincipalDetails userDetails) {
        HashMap<String, Object> result = new HashMap<>();

        if (userDetails == null)
            throw new LoginUserNotFoundException("로그인한 유저 정보가 없습니다.");

        commentService.deleteComment(meeting_id, comment_id, userDetails.getUser().getId());
        result.put("result", "true");

        return result;
    }
}
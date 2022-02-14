package com.hanghae.mini2.riceFriend.controller;

import com.hanghae.mini2.riceFriend.dto.request.CommentRequestDto;
import com.hanghae.mini2.riceFriend.repository.CommentRepository;
import com.hanghae.mini2.riceFriend.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@Api("Comment Controller API")
@RequiredArgsConstructor
@RestController
public class CommentRestController {
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    //댓글 조회
//    @GetMapping("/api/meeting/{meeting_id}")
//    @ApiOperation(value = "댓글 조회", notes = "댓글을 조회한다")
//    public  HashMap<String, Object> findCommentList() {
//        HashMap<String, Object> result = new HashMap<>();
//
//        List<CommentResponseDto> commentResponseDtos = commentService.findCommentList();
//        result.put("commentInfo", commentResponseDtos);
//        result.put("result", "true");
//
//        return result;
//    }

    //댓글 작성
    @PostMapping("/api/meeting/{meeting_id}/comments")
    @ApiOperation(value = "댓글 작성", notes = "댓글을 작성")
    public HashMap<String, Object> createComment(@PathVariable Long meeting_id, @RequestBody CommentRequestDto requestDto, Long userId) {
        HashMap<String, Object> result = new HashMap<>();

        commentService.createComment(meeting_id, requestDto, userId);
        result.put("result", "true");

        return result;
    }

    //댓글 수정
    @PutMapping("/api/meeting/{meeting_id}/comments/{commet_id}")
    @ApiOperation(value = "댓글 수정", notes = "댓글을 수정")
    public HashMap<String, Object> updateComment(@PathVariable Long comment_id, @RequestBody CommentRequestDto requestDto, Long userId) {
        HashMap<String, Object> result = new HashMap<>();

        ///////////////////////////테스트 데이터영역////////////////////////////////
//        comment_id = 1L;
//        CommentRequestDto commentRequestDto = CommentRequestDto.builder().restaurantName("음식점이름_test2").restaurantUrl("음식점url_test2").locationId(30L).commentTitle("모임날짜_test2").content("모임내용_test2").commentDate(LocalDateTime.now()).limitMember(5).build();
        /////////////////////////////////////////////////////////////////////////

        commentService.updateComment(comment_id, requestDto, userId);
        result.put("result", "true");

        return result;
    }

    //댓글 삭제
    @DeleteMapping("/api/meeting/{meeting_id}/comments/{commet_id}")
    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제")
    public HashMap<String, Object> deleteComment(@PathVariable Long meeting_id, @PathVariable Long comment_id, Long userId) {
        HashMap<String, Object> result = new HashMap<>();

        commentService.deleteComment(meeting_id, comment_id, userId);
        result.put("result", "true");

        return result;
    }
}



//    @GetMapping("/api/meeting/{meeting_id}")
//    @ApiOperation(value = "댓글 조회", notes = "댓글을 조회한다")
//    public CommentDetailResponseDto findComment(@PathVariable Long comment_id) {
//        HashMap<String, Object> result = new HashMap<>();
//
//        ///////////////////////////테스트 데이터영역////////////////////////////////
//        comment_id = 1L;
//        /////////////////////////////////////////////////////////////////////////
//
//        CommentDetailResponseDto commentDetailResponseDto = commentService.findComment(comment_id);
//        result.put("commentInfo", commentDetailResponseDto);
//        result.put("result", "true");
//
//        return commentDetailResponseDto;
//    }


// 테스트용(성공!!!)
//    @GetMapping("/api/meeting/test")
//    @ApiOperation(value = "맛집모임 정보 등록.", notes = "맛집모임 정보를 입력받아 등록한다.")
//    public HashMap<String, Object> createMeeting(Long userId) {
//        HashMap<String, Object> result = new HashMap<>();
//
//        ///////////////////////////테스트 데이터영역////////////////////////////////
//        userId = 1L;
//        MeetingRequestDto meetingRequestDto = MeetingRequestDto.builder()
//                .restaurantName("음식점이름_test")
//                .restaurantUrl("음식점url_test")
//                .locationId(11L)
//                .meetingTitle("모임날짜_test")
//                .content("모임내용_test")
//                .meetingDate(LocalDateTime.now())
//                .limitMember(3)
//                .build();
//
//        meetingService.createMeeting(meetingRequestDto, userId);
//        /////////////////////////////////////////////////////////////////////////
//
//        result.put("result", "true");
//
//        return result;
//    }
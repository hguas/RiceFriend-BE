package com.hanghae.mini2.riceFriend.service;


import com.hanghae.mini2.riceFriend.dto.request.CommentRequestDto;
import com.hanghae.mini2.riceFriend.dto.response.CommentResponseDto;
import com.hanghae.mini2.riceFriend.dto.response.MeetingDetailResponseDto;
import com.hanghae.mini2.riceFriend.dto.response.MeetingResonseDto;
import com.hanghae.mini2.riceFriend.dto.response.MeetingUserResponseDto;
import com.hanghae.mini2.riceFriend.model.*;
import com.hanghae.mini2.riceFriend.repository.CommentRepository;
import com.hanghae.mini2.riceFriend.repository.MeetingRepository;
import com.hanghae.mini2.riceFriend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;


    //  전체 댓글 조회
    public CommentResponseDto findComment(Long comment_id) {

        // 댓글 조회
        Comment comment = commentRepository.findCommentInfo(comment_id).orElseThrow(
                () -> new NullPointerException("해당 댓글이 존재하지 않습니다"));

        // 댓글 정보 셋팅
        CommentResponseDto commentResponseDto = comment.toCommentResponseDto();

        // 댓글 정보 반환(CommentResponseDto)
        return CommentResponseDto.builder()
//                .commentResponseDtos(commentResponseDtos)
//                .build();
    }



    // 댓글 등록.
    @Transactional
    public void createComment(CommentRequestDto requestDto, Long userId) {
//        User user = userRepository.findById(userId).orElseThrow(
//                () -> new NullPointerException("로그인을 해주세요!"));
//
//        Location location = locationRepository.findById(requestDto.getLocationId()).orElseThrow(
//                () -> new NullPointerException("해당 지역은 존재하지 않습니다!"));

        // Comment 테이블 저장
        Comment comment = requestDto.toCommentEntity(user, restaurant);
        commentRepository.save(comment);

        // 테이블 저장
        // 모임 등록자는 해당 모임의 참여자로 자동등록 되어야 함
        CommentUser commentUser = CommentUser.builder()
                .comment(comment)
                .user(user)
                .build();
        commentRepository.save(commentUser);
    }


    // 댓글 수정 (하나의 댓글 ) UPDATE
    @Transactional
    public void updateComment(Long comment_id, CommentRequestDto requestDto) {
        // 댓글테이블 조회
        Comment comment = commentRepository.findById(comment_id).orElseThrow(
                () -> new NullPointerException("해당 댓글이 존재하지 않습니다!"));

        comment.updateComment(requestDto);
    }


    // 댓글 삭제
    @Transactional
    public void deleteComment(Long comment_id, Long userId) {
        // 댓글테이블 조회
        Comment comment = commentRepository.findById(comment_id).orElseThrow(
                () -> new NullPointerException("해당 댓글이 존재하지 않습니다!"));

        // 서버에서 삭제권한 체크
        if (comment.getUser().getId().equals(userId)) { // 댓글 등록자 == 로그인 사용자
            // 해당 댓글정보 삭제
            commentRepository.deleteById(comment_id);
        } else {
            throw new IllegalArgumentException("댓글 작성자만 삭제하실 수 있습니다!");
        }
    }


}

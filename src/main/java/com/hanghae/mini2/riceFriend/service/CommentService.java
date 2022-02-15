package com.hanghae.mini2.riceFriend.service;


import com.hanghae.mini2.riceFriend.dto.request.CommentRequestDto;

import com.hanghae.mini2.riceFriend.model.*;
import com.hanghae.mini2.riceFriend.repository.CommentRepository;
import com.hanghae.mini2.riceFriend.repository.MeetingRepository;
import com.hanghae.mini2.riceFriend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;

    // 댓글 등록
    @Transactional
    public void createComment(Long meeting_id, CommentRequestDto requestDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NullPointerException("로그인을 해주세요"));

        Meeting meeting = meetingRepository.findById(meeting_id).orElseThrow(
                () -> new NullPointerException("해당 모임정보가 없습니다."));

        // Comment 테이블 저장
//        Comment commentTemp = new Comment(requestDto.getContent(), meeting, user);

        Comment comment = Comment.builder()
                .content(requestDto.getContent())
                .meeting(meeting)
                .user(user)
                .build();

        commentRepository.save(comment);

        // 모임 등록자는 해당 모임의 참여자로 자동등록 되어야 함
//        commentRepository.save(commentUser);
    }


    // 댓글 수정 (하나의 댓글 ) UPDATE
    @Transactional
    public void updateComment(@PathVariable Long comment_id, @RequestBody CommentRequestDto requestDto, Long userId) {
        // 댓글테이블 조회
        Comment comment = commentRepository.findById(comment_id).orElseThrow(
                () -> new NullPointerException("해당 댓글이 존재하지 않습니다!"));

        // 서버에서 수정권한 체크
        if (comment.getUser().getId().equals(userId)) { // 댓글 등록자 == 로그인 사용자
            // 해당 댓글정보 수정
            commentRepository.findById(comment_id);
        } else {
            throw new IllegalArgumentException("댓글 작성자만 수정하실 수 있습니다!");
        }

        comment.updateComment(requestDto);
    }


    // 댓글 삭제
    @Transactional
    public void deleteComment(@PathVariable Long meeting_id, Long comment_id,  Long userId) {
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

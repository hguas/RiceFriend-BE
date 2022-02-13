package com.hanghae.mini2.riceFriend.repository;

import com.hanghae.mini2.riceFriend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByMeetingId(Long meetingId);
    List<Comment> findAllCommentInfo();
}

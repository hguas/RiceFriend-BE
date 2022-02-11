package com.hanghae.mini2.riceFriend.repository;

import com.hanghae.mini2.riceFriend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

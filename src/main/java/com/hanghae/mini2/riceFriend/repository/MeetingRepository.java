package com.hanghae.mini2.riceFriend.repository;

import com.hanghae.mini2.riceFriend.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    // 전체 모임정보
    List<Meeting> findAllByOrderByCreatedAtDesc();
}

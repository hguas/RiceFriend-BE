package com.hanghae.mini2.riceFriend.repository;

import com.hanghae.mini2.riceFriend.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}

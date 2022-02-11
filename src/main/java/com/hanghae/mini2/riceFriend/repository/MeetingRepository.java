package com.hanghae.mini2.riceFriend.repository;

import com.hanghae.mini2.riceFriend.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

}

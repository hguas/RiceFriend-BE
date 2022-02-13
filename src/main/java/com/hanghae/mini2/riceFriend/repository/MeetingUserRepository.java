package com.hanghae.mini2.riceFriend.repository;

import com.hanghae.mini2.riceFriend.model.Meeting;
import com.hanghae.mini2.riceFriend.model.MeetingUser;
import com.hanghae.mini2.riceFriend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MeetingUserRepository extends JpaRepository<MeetingUser, Long> {

    Optional<MeetingUser> findMeetingUserByMeetingAndUser(Meeting meeting, User user);

//    Optional<MeetingUser> findMeetingUserByMeetingAndAndUser(Meeting meeting, User user);
}

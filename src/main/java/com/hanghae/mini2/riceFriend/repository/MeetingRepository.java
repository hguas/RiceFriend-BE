package com.hanghae.mini2.riceFriend.repository;

import com.hanghae.mini2.riceFriend.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    // 전체 모임정보
    @Query("select m " +
            "from Meeting m " +
            "inner join fetch m.restaurant r " +
            "inner join fetch m.user " +
            "left outer join fetch r.location order by m.createdAt desc")
    List<Meeting> findAllMeetingInfo();

    // 선택한 모임정보
    @Query("select m " +
            "from Meeting m " +
            "inner join fetch m.restaurant r " +
            "inner join fetch m.user " +
            "left outer join fetch r.location " +
            "where m.id = :meeting_id")
    Optional<Meeting> findMeetingInfo(@Param("meeting_id") Long meeting_id);
}
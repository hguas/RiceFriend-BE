package com.hanghae.mini2.riceFriend.service;

import com.hanghae.mini2.riceFriend.dto.response.CommentResponseDto;
import com.hanghae.mini2.riceFriend.dto.response.MeetingDetailResponseDto;
import com.hanghae.mini2.riceFriend.dto.response.MeetingResonseDto;
import com.hanghae.mini2.riceFriend.dto.response.MeetingUserResponseDto;
import com.hanghae.mini2.riceFriend.model.Comment;
import com.hanghae.mini2.riceFriend.model.Meeting;
import com.hanghae.mini2.riceFriend.model.MeetingUser;
import com.hanghae.mini2.riceFriend.repository.MeetingRepository;
import com.hanghae.mini2.riceFriend.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final RestaurantRepository restaurantRepository;

    // 맛집모임 목록 조회.
    @Transactional
    public List<MeetingResonseDto> findMeetingList() {
//        List<Meeting> meetings = meetingRepository.findAllByOrderByCreatedAtDesc();
//
//        List<MeetingResonseDto> meetingResonseDtos = new ArrayList<>();
//        for (Meeting meeting : meetings) {
//            int commentCount = meeting.getComments().size();// 댓글개수
//            int memberCount = meeting.getMeetingUsers().size();// 참여인원
//
//            MeetingResonseDto meetingResonseDto = MeetingResonseDto.builder()
//                    .nickname(meeting.getUser().getNickname())
//                    .userId(meeting.getUser().getId())
//                    .imgUrl(meeting.getRestaurant().getImageUrl())
//                    .locationId(meeting.getRestaurant().getLocation().getId())
//                    .meetingId(meeting.getId())
//                    .meetingTitle(meeting.getTitle())
//                    .content(meeting.getContent())
//                    .meetingDate(meeting.getDate())
//                    .limitMember(meeting.getLimitMember())
//                    .commentCount(commentCount)
//                    .memberCount(memberCount)
//                    .build();
//
//            meetingResonseDtos.add(meetingResonseDto);
//        }

        return meetingRepository.findAllByOrderByCreatedAtDesc().stream().map(meeting -> meeting.toMeetingDetailResponseDto()).collect(Collectors.toList());
    }

    // 맛집모임 정보를 조회.
    @Transactional
    public MeetingDetailResponseDto findMeeting(Long meeting_id) {
        Meeting meeting = meetingRepository.findById(meeting_id).orElseThrow(
                () -> new NullPointerException("해당 모임이 존재하지 않습니다."));

        int commentCount = meeting.getComments().size();// 댓글개수
        int memberCount = meeting.getMeetingUsers().size();// 참여인원

        // 맛집모임 기본정보 셋팅
        MeetingResonseDto meetingResonseDto = meeting.toMeetingDetailResponseDto();

        // 맛집모임 댓글목록, 참여유저정보 셋팅
        List<CommentResponseDto> commentResponseDtos = meeting.getComments().stream().map(comment -> comment.toCommentResponseDto()).collect(Collectors.toList());
        List<MeetingUserResponseDto> meetingUserResponseDtos = meeting.getMeetingUsers().stream().map(meetingUser -> meetingUser.toMeetingUserResponseDto()).collect(Collectors.toList());

        // 맛집모임 상세정보 셋팅
        return MeetingDetailResponseDto.builder()
                .meetingResonseDto(meetingResonseDto)
                .commentResponseDtos(commentResponseDtos)
                .meetingUserResponseDtos(meetingUserResponseDtos)
                .build();
    }

//    @Transactional
//    public MeetingDetailResponseDto createMeeting(MeetingRequestDto requestDto, Long userId) {
//        return new MeetingDetailResponseDto();
//    }

//    @Transactional
//    public MeetingDetailResponseDto updateMeeting(Long meeting_id, MeetingRequestDto requestDto) {
//        return new MeetingDetailResponseDto();
//    }

    @Transactional
    public void deleteMeeting(Long meeting_id) {
        meetingRepository.deleteById(meeting_id);
    }
}

package com.hanghae.mini2.riceFriend.service;

import com.hanghae.mini2.riceFriend.dto.request.MeetingRequestDto;
import com.hanghae.mini2.riceFriend.dto.response.CommentResponseDto;
import com.hanghae.mini2.riceFriend.dto.response.MeetingDetailResponseDto;
import com.hanghae.mini2.riceFriend.dto.response.MeetingResonseDto;
import com.hanghae.mini2.riceFriend.dto.response.MeetingUserResponseDto;
import com.hanghae.mini2.riceFriend.model.*;
import com.hanghae.mini2.riceFriend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MeetingService {

    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final LocationRepository locationRepository;
    private final MeetingRepository meetingRepository;
    private final MeetingUserRepository meetingUserRepository;

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

        // 맛집모임 목록정보 반환(toMeetingDetailResponseDto)
        return meetingRepository.findAllByOrderByCreatedAtDesc().stream().map(meeting -> meeting.toMeetingDetailResponseDto()).collect(Collectors.toList());
    }

    // 맛집모임 정보를 조회.
    @Transactional
    public MeetingDetailResponseDto findMeeting(Long meeting_id) {
        // 모임테이블 조회
        Meeting meeting = meetingRepository.findById(meeting_id).orElseThrow(
                () -> new NullPointerException("해당 모임이 존재하지 않습니다!"));

        int commentCount = meeting.getComments().size();// 댓글개수
        int memberCount = meeting.getMeetingUsers().size();// 참여인원

        // 맛집모임 기본정보 셋팅
        MeetingResonseDto meetingResonseDto = meeting.toMeetingDetailResponseDto();

        // 맛집모임 댓글목록, 참여유저정보 셋팅
        List<CommentResponseDto> commentResponseDtos = meeting.getComments().stream().map(comment -> comment.toCommentResponseDto()).collect(Collectors.toList());
        List<MeetingUserResponseDto> meetingUserResponseDtos = meeting.getMeetingUsers().stream().map(meetingUser -> meetingUser.toMeetingUserResponseDto()).collect(Collectors.toList());

        // 맛집모임 상세정보 반환(MeetingDetailResponseDto)
        return MeetingDetailResponseDto.builder()
                .meetingResonseDto(meetingResonseDto)
                .commentResponseDtos(commentResponseDtos)
                .meetingUserResponseDtos(meetingUserResponseDtos)
                .build();
    }

    // 맛집모임 정보 등록.
    @Transactional
    public void createMeeting(MeetingRequestDto requestDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NullPointerException("로그인을 해주세요!"));

        Location location = locationRepository.findById(requestDto.getLocationId()).orElseThrow(
                () -> new NullPointerException("해당 지역은 존재하지 않습니다!"));

        // RESTAURANT 테이블 저장
        Restaurant restaurant = requestDto.toRestaurantEntity(location, user);
        restaurantRepository.save(restaurant);

        // MEETING 테이블 저장
        Meeting meeting = requestDto.toMeetingEntity(user, restaurant);
        meetingRepository.save(meeting);

        // MEETINGUSER 테이블 저장
        // 모임 등록자는 해당 모임의 참여자로 자동등록 되어야 한다!
        MeetingUser meetingUser = MeetingUser.builder()
                .meeting(meeting)
                .user(user)
                .build();
        meetingUserRepository.save(meetingUser);
    }

    // 맛집모임 정보 수정.
    @Transactional
    public void updateMeeting(Long meeting_id, MeetingRequestDto requestDto) {
        // 모임테이블 조회
        Meeting meeting = meetingRepository.findById(meeting_id).orElseThrow(
                () -> new NullPointerException("해당 모임이 존재하지 않습니다!"));

        Restaurant restaurant = restaurantRepository.findById(meeting.getId()).orElseThrow(
                () -> new NullPointerException("해당 음식점은 존재하지 않습니다!"));

        Location location = locationRepository.findById(requestDto.getLocationId()).orElseThrow(
                () -> new NullPointerException("해당 지역은 존재하지 않습니다!"));

        meeting.updateMeeting(requestDto);
        restaurant.updateRestaurant(requestDto, location);
    }

    // 맛집모임 정보 삭제.
    @Transactional
    public void deleteMeeting(Long meeting_id, Long userId) {
        // 모임테이블 조회
        Meeting meeting = meetingRepository.findById(meeting_id).orElseThrow(
                () -> new NullPointerException("해당 모임이 존재하지 않습니다!"));

        // 서버에서 삭제권한 체크
        if (meeting.getUser().getId().equals(userId)) { // 모임 등록자 == 로그인 사용자
            // 해당 모임정보 삭제
            meetingRepository.deleteById(meeting_id);
        } else {
            throw new IllegalArgumentException("모임 작성자만 삭제하실 수 있습니다!");
        }

        // 이미 마감된 모임 조건!? 서버처리
    }
}

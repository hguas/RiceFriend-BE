package com.hanghae.mini2.riceFriend.service;

import com.hanghae.mini2.riceFriend.dto.request.MeetingRequestDto;
import com.hanghae.mini2.riceFriend.dto.response.CommentResponseDto;
import com.hanghae.mini2.riceFriend.dto.response.MeetingDetailResponseDto;
import com.hanghae.mini2.riceFriend.dto.response.MeetingResonseDto;
import com.hanghae.mini2.riceFriend.dto.response.MeetingUserResponseDto;
import com.hanghae.mini2.riceFriend.model.*;
import com.hanghae.mini2.riceFriend.repository.*;
import com.hanghae.mini2.riceFriend.utils.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MeetingService {

    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final LocationRepository locationRepository;
    private final MeetingRepository meetingRepository;
    private final MeetingUserRepository meetingUserRepository;

    private final S3Uploader s3Uploader;
    private final String imageDirName = "static";

    // 맛집모임 목록 조회.(테스트완료!)
    @Transactional
    public List<MeetingResonseDto> findMeetingList() {

        // 맛집모임 목록정보 반환(toMeetingDetailResponseDto)
        return meetingRepository.findAllMeetingInfo().stream().
                map(meeting -> meeting.toMeetingDetailResponseDto()).collect(Collectors.toList());
    }

    // 맛집모임 정보를 조회.(테스트완료!)
    @Transactional
    public MeetingDetailResponseDto findMeeting(Long meeting_id) {
        // 모임테이블 조회
        Meeting meeting = meetingRepository.findMeetingInfo(meeting_id).orElseThrow(
                () -> new NullPointerException("해당 모임이 존재하지 않습니다!"));

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


    // 맛집모임 정보 등록.(테스트완료!)
    @Transactional
    public void createMeeting(MeetingRequestDto requestDto, User user) {
//        User user = userRepository.findById(userId).orElseThrow(
//                () -> new NullPointerException("로그인을 해주세요!"));

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

    // 맛집모임 정보 수정.(테스트완료!)
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

    // 맛집모임 정보 삭제.(테스트완료!)
    @Transactional
    public void deleteMeeting(Long meeting_id, Long userId) {
        // 모임테이블 조회
        Meeting meeting = meetingRepository.findById(meeting_id).orElseThrow(
                () -> new NullPointerException("해당 모임이 존재하지 않습니다!"));

        // 서버에서 삭제권한 체크
        if (meeting.getUser().getId().equals(userId)) { // 모임 등록자 == 로그인 사용자
            // 해당 모임정보 삭제
            restaurantRepository.deleteById(meeting.getRestaurant().getId());
        } else {
            throw new IllegalArgumentException("모임 작성자만 삭제하실 수 있습니다!");
        }

        // 이미 마감된 모임 조건!? 서버처리
    }

    // 모임 참여.(테스트완료!)
    @Transactional
    public void createMeetingUser(Long meeting_id, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NullPointerException("로그인을 해주세요!"));

        // 모임테이블 조회
        Meeting meeting = meetingRepository.findById(meeting_id).orElseThrow(
                () -> new NullPointerException("해당 모임이 존재하지 않습니다!"));

        // 모임 참여자테이블 조회
        Optional<MeetingUser> meetingUserChk = meetingUserRepository.findMeetingUserByMeetingAndUser(meeting, user);

        // 모임 참가여부 체크
        if (meetingUserChk.isPresent()) {
            throw new IllegalArgumentException("이미 해당 모임에 참여하고 있습니다!");
        }

        MeetingUser meetingUser = MeetingUser.builder()
                .meeting(meeting)
                .user(user)
                .build();

        // 모임 참여.
        meetingUserRepository.save(meetingUser);
    }

    // 모임 탈퇴.(테스트완료!)
    @Transactional
    public void deleteMeetingUser(Long meeting_id, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NullPointerException("로그인을 해주세요!"));

        // 모임테이블 조회
        Meeting meeting = meetingRepository.findById(meeting_id).orElseThrow(
                () -> new NullPointerException("해당 모임이 존재하지 않습니다!"));

        // 모임 참여자테이블 조회
        MeetingUser meetingUser = meetingUserRepository.findMeetingUserByMeetingAndUser(meeting, user).orElseThrow(
                () -> new NullPointerException("해당 모임에 참여 정보가 없습니다!"));

        // 모임 탈퇴.
        meetingUserRepository.deleteById(meetingUser.getId());
    }
}

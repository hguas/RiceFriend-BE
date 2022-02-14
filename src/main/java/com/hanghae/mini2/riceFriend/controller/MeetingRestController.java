package com.hanghae.mini2.riceFriend.controller;

import com.hanghae.mini2.riceFriend.dto.request.MeetingRequestDto;
import com.hanghae.mini2.riceFriend.dto.response.MeetingDetailResponseDto;
import com.hanghae.mini2.riceFriend.dto.response.MeetingResonseDto;
import com.hanghae.mini2.riceFriend.service.MeetingService;
import com.hanghae.mini2.riceFriend.utils.S3Uploader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Api("Meeting Controller API")
@RequiredArgsConstructor
@RestController
public class MeetingRestController {

    private final MeetingService meetingService;

    @GetMapping("/api/meeting")
    @ApiOperation(value = "맛집모임 목록 조회.", notes = "맛집모임 목록을 조회한다.")
    public List<MeetingResonseDto> findMeetingList() {
        HashMap<String, Object> result = new HashMap<>();

        List<MeetingResonseDto> meetingResponseDtos = meetingService.findMeetingList();
        result.put("meetingInfo", meetingResponseDtos);
        result.put("result", "true");

        return meetingResponseDtos;
    }

    @GetMapping("/api/meeting/{meeting_id}")
    @ApiOperation(value = "맛집모임 정보를 조회.", notes = "선택한 맛집모임 정보를 조회한다.")
    public MeetingDetailResponseDto findMeeting(@PathVariable Long meeting_id) {
        HashMap<String, Object> result = new HashMap<>();

        MeetingDetailResponseDto meetingDetailResponseDto = meetingService.findMeeting(meeting_id);
        result.put("meetingInfo", meetingDetailResponseDto);
        result.put("result", "true");

        return meetingDetailResponseDto;
    }


    // 테스트용(파일업로드 구현중~~~)
    @PostMapping("/api/meeting/test")
    @ApiOperation(value = "맛집모임 정보 등록.", notes = "맛집모임 정보를 입력받아 등록한다.")
    public HashMap<String, Object> createMeeting(Long userId, @RequestParam("image")MultipartFile multipartFile) throws IOException {
        HashMap<String, Object> result = new HashMap<>();
        ///////////////////////////테스트 데이터영역////////////////////////////////
        userId = 2L;
        MeetingRequestDto meetingRequestDto = MeetingRequestDto.builder()
                .restaurantName("음식점이름_test2")
                .restaurantUrl("음식점url_test2")
                .locationId(11L)
                .meetingTitle("모임날짜_test2")
                .content("모임내용_test2")
                .meetingDate(LocalDateTime.now())
                .limitMember(5)
                .build();

        meetingService.createMeeting(meetingRequestDto, multipartFile, userId);
        /////////////////////////////////////////////////////////////////////////

        result.put("result", "true");

        return result;
    }


    // 테스트용(파일업로드 구현중~~~)
//    @PostMapping("/api/meeting")
//    @ApiOperation(value = "맛집모임 정보 등록.", notes = "맛집모임 정보를 입력받아 등록한다.")
//    public HashMap<String, Object> createMeeting(@RequestBody MeetingRequestDto requestDto, @RequestBody(required = false) MultipartFile multipartFile, Long userId) throws IOException {
//        HashMap<String, Object> result = new HashMap<>();
//
//        meetingService.createMeeting(requestDto, multipartFile, userId);
//        result.put("result", "true");
//
//        return result;
//    }

//    @PostMapping("/api/meeting")
//    @ApiOperation(value = "맛집모임 정보 등록.", notes = "맛집모임 정보를 입력받아 등록한다.")
//    public HashMap<String, Object> createMeeting(@RequestBody MeetingRequestDto requestDto, Long userId) {
//        HashMap<String, Object> result = new HashMap<>();
//
//        meetingService.createMeeting(requestDto, userId);
//        result.put("result", "true");
//
//        return result;
//    }

    @PutMapping("/api/meeting/{meeting_id}")
    @ApiOperation(value = "맛집모임 정보 수정.", notes = "맛집모임 정보를 수정한다.")
    public HashMap<String, Object> updateMeeting(@PathVariable Long meeting_id, @RequestBody MeetingRequestDto requestDto) {
        HashMap<String, Object> result = new HashMap<>();

        meetingService.updateMeeting(meeting_id, requestDto);
        result.put("result", "true");

        return result;
    }

    @DeleteMapping("/api/meeting/{meeting_id}")
    @ApiOperation(value = "맛집모임 정보 삭제.", notes = "맛집모임 정보를 삭제한다.")
    public HashMap<String, Object> deleteMeeting(@PathVariable Long meeting_id, Long userId) {
        HashMap<String, Object> result = new HashMap<>();

        meetingService.deleteMeeting(meeting_id, userId);
        result.put("result", "true");

        return result;
    }

    @PostMapping("/api/meeting/{meeting_id}/user")
    @ApiOperation(value = "모임 참여.", notes = "유저가 선택한 모임에 참여한다.")
    public HashMap<String, Object> createMeetingUser(@PathVariable Long meeting_id, Long userId) {
        HashMap<String, Object> result = new HashMap<>();

        meetingService.createMeetingUser(meeting_id, userId);
        result.put("result", "true");

        return result;
    }

    @DeleteMapping("/api/meeting/{meeting_id}/user")
    @ApiOperation(value = "모임 탈퇴.", notes = "유저가 참여한 모임에 탈퇴한다.")
    public HashMap<String, Object> deleteMeetingUser(@PathVariable Long meeting_id, Long userId) {
        HashMap<String, Object> result = new HashMap<>();

        meetingService.deleteMeetingUser(meeting_id, userId);
        result.put("result", "true");

        return result;
    }

    // 테스트용(성공!!!)
//    @GetMapping("/api/meeting/test")
//    @ApiOperation(value = "맛집모임 정보 등록.", notes = "맛집모임 정보를 입력받아 등록한다.")
//    public HashMap<String, Object> createMeeting(Long userId) {
//        HashMap<String, Object> result = new HashMap<>();
//
//        ///////////////////////////테스트 데이터영역////////////////////////////////
//        userId = 1L;
//        MeetingRequestDto meetingRequestDto = MeetingRequestDto.builder()
//                .restaurantName("음식점이름_test")
//                .restaurantUrl("음식점url_test")
//                .locationId(11L)
//                .meetingTitle("모임날짜_test")
//                .content("모임내용_test")
//                .meetingDate(LocalDateTime.now())
//                .limitMember(3)
//                .build();
//
//        meetingService.createMeeting(meetingRequestDto, userId);
//        /////////////////////////////////////////////////////////////////////////
//
//        result.put("result", "true");
//
//        return result;
//    }

    // 테스트용(성공!)
//    @GetMapping("/api/meeting/test2")
//    @ApiOperation(value = "맛집모임 정보 수정.", notes = "맛집모임 정보를 수정한다.")
//    public HashMap<String, Object> updateMeeting() {
//        HashMap<String, Object> result = new HashMap<>();
//
//        ///////////////////////////테스트 데이터영역////////////////////////////////
//        Long meeting_id = 1L;
//        MeetingRequestDto meetingRequestDto = MeetingRequestDto.builder()
//                .restaurantName("음식점이름_바꾸기완료!")
//                .restaurantUrl("음식점url_바꾸기완료!")
//                .locationId(30L)
//                .meetingTitle("모임날짜_바꾸기완료!")
//                .content("모임내용_바꾸기완료!")
//                .meetingDate(LocalDateTime.now())
//                .limitMember(20).build();
//        /////////////////////////////////////////////////////////////////////////
//
//        meetingService.updateMeeting(meeting_id, meetingRequestDto);
//        result.put("result", "true");
//
//        return result;
//    }

    // 테스트용(성공!)
//    @GetMapping("/api/meeting/test3")
//    @ApiOperation(value = "맛집모임 정보 삭제.", notes = "맛집모임 정보를 삭제한다.")
//    public HashMap<String, Object> deleteMeeting() {
//        HashMap<String, Object> result = new HashMap<>();
//
//        ///////////////////////////테스트 데이터영역////////////////////////////////
//        Long meeting_id = 1L;
//        Long userId = 1L;
//        /////////////////////////////////////////////////////////////////////////
//
//        meetingService.deleteMeeting(meeting_id, userId);
//        result.put("result", "true");
//
//        return result;
//    }

    // 테스트용(성공!)
//    @GetMapping("/api/meeting/test4")
//    @ApiOperation(value = "모임 참여.", notes = "유저가 선택한 모임에 참여한다.")
//    public HashMap<String, Object> createMeetingUser() {
//        HashMap<String, Object> result = new HashMap<>();
//
//        ///////////////////////////테스트 데이터영역////////////////////////////////
//        Long meeting_id = 2L;
//        Long userId = 2L;
//        /////////////////////////////////////////////////////////////////////////
//
//        meetingService.createMeetingUser(meeting_id, userId);
//        result.put("result", "true");
//
//        return result;
//    }

    // 테스트용(성공!)
//    @GetMapping("/api/meeting/test5")
//    @ApiOperation(value = "모임 탈퇴.", notes = "유저가 참여한 모임에 탈퇴한다.")
//    public HashMap<String, Object> deleteMeetingUser() {
//        HashMap<String, Object> result = new HashMap<>();
//
//        ///////////////////////////테스트 데이터영역////////////////////////////////
//        Long meeting_id = 2L;
//        Long userId = 2L;
//        /////////////////////////////////////////////////////////////////////////
//
//        meetingService.deleteMeetingUser(meeting_id, userId);
//        result.put("result", "true");
//
//        return result;
//    }
}
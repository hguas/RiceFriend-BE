package com.hanghae.mini2.riceFriend.controller;

import com.hanghae.mini2.riceFriend.dto.request.MeetingRequestDto;
import com.hanghae.mini2.riceFriend.dto.response.MeetingDetailResponseDto;
import com.hanghae.mini2.riceFriend.service.MeetingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Meeting Controller API")
@RequiredArgsConstructor
@RestController
public class MeetingRestController {

    private final MeetingService meetingService;

    @GetMapping("/api/meeting")
    @ApiOperation(value = "맛집모임 목록 조회.", notes = "맛집모임 목록을 조회한다.")
    public void findMeetingList() {
        List<MeetingDetailResponseDto> meetingResponseDtos = meetingService.findMeetingList();
//        return ;
    }

    @GetMapping("/api/meeting/{meet_id}")
    @ApiOperation(value = "맛집모임 정보를 조회.", notes = "선택한 맛집모임 정보를 조회한다.")
    public void findMeeting(@PathVariable Long meet_id) {
        MeetingDetailResponseDto meetingResponseDto = meetingService.findMeeting(meet_id);
//        return ;
    }

    @PostMapping("/api/register")
    @ApiOperation(value = "맛집모임 정보 등록.", notes = "맛집모임 정보를 입력받아 등록한다.")
    public void createMeeting(@RequestBody MeetingRequestDto requestDto) {
        Long userId = 1L;// 유저ID
        meetingService.createMeeting(requestDto, userId);
//        return ;
    }

    @PutMapping("/api/register/{meet_id}")
    @ApiOperation(value = "맛집모임 정보 수정.", notes = "맛집모임 정보를 수정한다.")
    public void updateMeeting(@PathVariable Long meet_id, @RequestBody MeetingRequestDto requestDto) {
        meetingService.updateMeeting(meet_id, requestDto);
//        return ;
    }

    @DeleteMapping("/api/register/{meet_id}")
    @ApiOperation(value = "맛집모임 정보 삭제.", notes = "맛집모임 정보를 삭제한다.")
    public void deleteMeeting(@PathVariable Long meet_id) {
        meetingService.deleteMeeting(meet_id);
//        return
    }
}
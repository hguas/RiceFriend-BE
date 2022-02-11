package com.hanghae.mini2.riceFriend.service;

import com.hanghae.mini2.riceFriend.dto.request.MeetingRequestDto;
import com.hanghae.mini2.riceFriend.dto.response.MeetingDetailResponseDto;
import com.hanghae.mini2.riceFriend.repository.MeetingRepository;
import com.hanghae.mini2.riceFriend.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MeetingService {

    private final RestaurantRepository restaurantRepository;
    private final MeetingRepository meetingRepository;

    @Transactional
    public List<MeetingDetailResponseDto> findMeetingList() {

        return new ArrayList<MeetingDetailResponseDto>();
    }

    @Transactional
    public MeetingDetailResponseDto findMeeting(Long meet_id) {

        return new MeetingDetailResponseDto();
    }

    @Transactional
    public MeetingDetailResponseDto createMeeting(MeetingRequestDto requestDto, Long userId) {
        return new MeetingDetailResponseDto();
    }

    @Transactional
    public MeetingDetailResponseDto updateMeeting(Long meet_id, MeetingRequestDto requestDto) {
        return new MeetingDetailResponseDto();
    }

    @Transactional
    public void deleteMeeting(Long meet_id) {
        meetingRepository.deleteById(meet_id);
    }
}

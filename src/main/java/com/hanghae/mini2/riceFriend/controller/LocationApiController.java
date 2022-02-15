package com.hanghae.mini2.riceFriend.controller;

import com.hanghae.mini2.riceFriend.dto.response.LocationResponseDto;
import com.hanghae.mini2.riceFriend.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class LocationApiController {

    private final LocationRepository locationRepository;

    @GetMapping("/api/location")
    public ResponseEntity<LocationResponseDto> getLocationList() {
        return ResponseEntity.ok(new LocationResponseDto(locationRepository.findAll(), "true"));
    }
}

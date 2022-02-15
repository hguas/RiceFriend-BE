package com.hanghae.mini2.riceFriend.dto.response;

import com.hanghae.mini2.riceFriend.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocationResponseDto {

    private List<Location> locationList;

    private String result;

}

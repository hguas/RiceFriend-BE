package com.hanghae.mini2.riceFriend.dto.response;

import com.hanghae.mini2.riceFriend.model.Location;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocationResponseDto {

    @ApiModelProperty(value = "지역 정보 리스트")
    private List<Location> locationList;

    @ApiModelProperty(value = "결과 여부")
    private String result;

}

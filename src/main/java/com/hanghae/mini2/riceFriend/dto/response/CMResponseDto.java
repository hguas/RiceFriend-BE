package com.hanghae.mini2.riceFriend.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CMResponseDto {

    @ApiModelProperty(value = "결과 여부")
    public String result;
}

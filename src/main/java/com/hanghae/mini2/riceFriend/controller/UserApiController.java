package com.hanghae.mini2.riceFriend.controller;

import com.hanghae.mini2.riceFriend.dto.request.LoginRequestDto;
import com.hanghae.mini2.riceFriend.dto.request.SignupRequestDto;
import com.hanghae.mini2.riceFriend.dto.response.CMResponseDto;
import com.hanghae.mini2.riceFriend.dto.response.LoginResponseDto;
import com.hanghae.mini2.riceFriend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Api("User Controller API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user/signup")
    @ApiOperation(value = "회원가입", hidden = true)
    public ResponseEntity<CMResponseDto> signup(@RequestBody @Valid SignupRequestDto requestDto) {
        return userService.signup(requestDto);
    }

    @PostMapping("/user/login")
    @ApiOperation(value = "로그인")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto requestDto,
                                                  HttpServletResponse response) {
        return userService.login(requestDto, response);
    }

    @PostMapping("/user/idCheck")
    @ApiOperation(value = "아이디 중복 체크", notes = "회원가입시 아이디 중복 여부를 체크한다.")
    public ResponseEntity<CMResponseDto> idCheck(@RequestBody SignupRequestDto requestDto) {
        return userService.idCheck(requestDto);
    }
}

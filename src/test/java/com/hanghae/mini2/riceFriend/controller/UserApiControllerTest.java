package com.hanghae.mini2.riceFriend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanghae.mini2.riceFriend.dto.request.SignupRequestDto;
import com.hanghae.mini2.riceFriend.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional // 테스트 후 롤백해준다.
class UserApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName(("회원 가입 처리 - 정상"))
    @Test
    void signUpSubmit_with_correct_input() throws Exception {
        //given
        SignupRequestDto signupRequestDto = new SignupRequestDto();
        signupRequestDto.setEmail("test@nate.com");
        signupRequestDto.setNickname("test");
        signupRequestDto.setPassword("1234");
        signupRequestDto.setPasswordCheck("1234");
        signupRequestDto.setGender("male");

        String content = objectMapper.writeValueAsString(signupRequestDto);

        mockMvc.perform(post("/api/user/signup")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk());

        Assertions.assertTrue(userRepository.findByEmail(signupRequestDto.getEmail()).isPresent());
    }
}
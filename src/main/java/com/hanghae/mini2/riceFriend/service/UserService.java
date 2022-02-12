package com.hanghae.mini2.riceFriend.service;

import com.hanghae.mini2.riceFriend.dto.request.SignupRequestDto;
import com.hanghae.mini2.riceFriend.model.Role;
import com.hanghae.mini2.riceFriend.model.User;
import com.hanghae.mini2.riceFriend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User signup(SignupRequestDto requestDto) {

        // 유효성 체크

        // 비밀번호 암호화
        String rawPassword = requestDto.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);

        // User 객체 생성
        User user = User.builder()
                .nickname(requestDto.getNickname())
                .email(requestDto.getEmail())
                .password(encPassword)
                .gender(requestDto.getGender())
                .role(Role.ROLE_USER)
                .build();

        return userRepository.save(user);
    }
}

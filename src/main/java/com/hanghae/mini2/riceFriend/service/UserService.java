package com.hanghae.mini2.riceFriend.service;

import com.hanghae.mini2.riceFriend.config.jwt.JwtAuthenticationProvider;
import com.hanghae.mini2.riceFriend.dto.request.LoginRequestDto;
import com.hanghae.mini2.riceFriend.dto.request.SignupRequestDto;
import com.hanghae.mini2.riceFriend.dto.response.CMResponseDto;
import com.hanghae.mini2.riceFriend.handler.ex.EmailNotFoundException;
import com.hanghae.mini2.riceFriend.handler.ex.InvalidException;
import com.hanghae.mini2.riceFriend.model.Role;
import com.hanghae.mini2.riceFriend.model.User;
import com.hanghae.mini2.riceFriend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    public ResponseEntity<CMResponseDto> signup(SignupRequestDto requestDto) {

        String email = requestDto.getEmail(); // 아이디
        String rawPassword = requestDto.getPassword();
        String pwCheck = requestDto.getPasswordCheck();

        // 유효성 체크
        if (!isPasswordMatched(email, rawPassword))
            throw new InvalidException("비밀번호에 아이디가 들어갈 수 없습니다.");

        if (!isExistEmail(email))
            throw new InvalidException("이미 존재하는 아이디 입니다.");

        if (!isDuplicatePassword(rawPassword, pwCheck)) {
            throw new InvalidException("비밀번호가 일치하지 않습니다.");
        }

        // 비밀번호 암호화
        String encPassword = passwordEncoder.encode(rawPassword);

        // User 객체 생성
        User user = User.builder()
                .nickname(requestDto.getNickname())
                .email(requestDto.getEmail())
                .password(encPassword)
                .gender(requestDto.getGender())
                .role(Role.ROLE_USER)
                .build();

        // user 저장
        userRepository.save(user);

        return ResponseEntity.ok(new CMResponseDto("true"));
    }

    public ResponseEntity<CMResponseDto> login(LoginRequestDto requestDto, HttpServletResponse response) {

        User userEntity = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(
                () -> new EmailNotFoundException("가입되지 않은 이메일입니다.")
        );

        if (!passwordEncoder.matches(requestDto.getPassword(), userEntity.getPassword()))
            throw new InvalidException("비밀번호를 다시 입력해주세요.");


        // 토큰 정보 생성
        String token = jwtAuthenticationProvider.createToken(userEntity.getNickname(), userEntity.getEmail());
        response.setHeader("X-AUTH-TOKEN", token);

        Cookie cookie = new Cookie("X-AUTH-TOKEN", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(new CMResponseDto("true"));
    }

    private boolean isDuplicatePassword(String rawPassword, String pwCheck) {
        return rawPassword.equals(pwCheck);
    }

    public boolean isExistEmail(String email) {
        return !userRepository.findByEmail(email).isPresent();
    }

    private boolean isPasswordMatched(String email, String rawPassword) {
        String domain = email.split("@")[0];
        return !rawPassword.contains(domain);
    }

    public ResponseEntity<CMResponseDto> idCheck(SignupRequestDto requestDto) {
        if(userRepository.findByEmail(requestDto.getEmail()).isPresent())
            return ResponseEntity.ok(new CMResponseDto("false"));
        return ResponseEntity.ok(new CMResponseDto("true"));
    }
}

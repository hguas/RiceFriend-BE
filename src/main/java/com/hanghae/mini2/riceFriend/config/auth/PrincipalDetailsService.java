package com.hanghae.mini2.riceFriend.config.auth;

import com.hanghae.mini2.riceFriend.handler.ex.EmailNotFoundException;
import com.hanghae.mini2.riceFriend.model.User;
import com.hanghae.mini2.riceFriend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User findUser = userRepository.findByEmail(email).orElseThrow(
                () -> new EmailNotFoundException("가입되지 않은 이메일입니다.")
        );

        return new PrincipalDetails(findUser);
    }
}

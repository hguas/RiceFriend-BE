package com.hanghae.mini2.riceFriend.config;

import com.hanghae.mini2.riceFriend.config.jwt.JwtAuthenticationFilter;
import com.hanghae.mini2.riceFriend.config.jwt.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // h2, swagger 사용에 대한 허용 (CSRF, FrameOptions 무시)
        // 보안예외처리(정적리소스, HTML)
        web
                .ignoring()
                .antMatchers("/swagger-ui.html", "/webjars/**", "/swagger/**")
                .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.httpBasic().disable()
                        .cors().configurationSource(corsConfigurationSource());

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 안함.
                .and()
                .formLogin().disable() // 기본 로그인 방식 안쓸거임.

                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/meeting/**").permitAll()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
                .anyRequest()
                .permitAll()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtAuthenticationProvider),
                        UsernamePasswordAuthenticationFilter.class);


    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedOrigin("http://ricefriend-bucket.s3-website.ap-northeast-2.amazonaws.com/");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.addExposedHeader("*");
        configuration.setAllowCredentials(true);
        configuration.validateAllowCredentials();
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}

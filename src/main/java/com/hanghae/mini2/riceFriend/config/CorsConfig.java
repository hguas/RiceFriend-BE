package com.hanghae.mini2.riceFriend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true); // 내 서버가 json을 자바스크립트에서 처리할 수 있게 할지 설정
        configuration.addAllowedMethod("*"); // 모든 ip에 응답을 허용
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*"); // 모든 http 메서드 요청을 허용

        source.registerCorsConfiguration("/api/**", configuration);
        return new CorsFilter(source);

//        @Override
//        public void addCorsMappings(CorsRegistry registry) {
//            registry.addMapping("/**")
//                    .exposedHeaders("X-AUTH-TOKEN")
//                    .allowCredentials(true)
//                    .allowedOrigins("http://localhost:3000");
//        }
    }
}

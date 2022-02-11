package com.hanghae.mini2.riceFriend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RiceFriendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiceFriendApplication.class, args);
	}

}

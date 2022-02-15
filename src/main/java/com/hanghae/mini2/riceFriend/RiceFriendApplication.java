package com.hanghae.mini2.riceFriend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RiceFriendApplication {

	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml,"
			+ "classpath:aws.yml";

	public static void main(String[] args) {
//		SpringApplication.run(RiceFriendApplication.class, args);
		new SpringApplicationBuilder(RiceFriendApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}

}

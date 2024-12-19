package com.kimandkang.workingoutsquirrel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WOSApplication {

	public static void main(String[] args) {
		SpringApplication.run(WOSApplication.class, args);
	}

}

package com.team13.WaitDoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WaitDocApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaitDocApplication.class, args);
	}


}

package com.igovern.program;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProgramServiceApplication {
	private static final Logger log =
	        LoggerFactory.getLogger(
	                ProgramServiceApplication.class
	        );
	public static void main(String[] args) {
		SpringApplication.run(ProgramServiceApplication.class, args);
		log.info("Program Service Started Successfully");
	}

}

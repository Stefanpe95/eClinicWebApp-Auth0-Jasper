package com.example.eClinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;

@SpringBootApplication
@EnableEmailTools
@EnableScheduling
public class EClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(EClinicApplication.class, args);

	}

}

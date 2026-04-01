package com.example.designation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class DesignationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesignationApplication.class, args);
	}

}

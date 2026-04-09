package com.example.designation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.designation.dto.DesignationContactsInfoDto;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = DesignationContactsInfoDto.class)
public class DesignationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesignationApplication.class, args);
	}

}

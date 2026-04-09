package com.example.company.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@ConfigurationProperties(prefix = "company")
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyContactsInfoDto {

	String message ; 
	Map<String, String>contactDetails; 
	List<String> onCallSupport;
}

package com.example.company.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "company")
public record CompanyContactsInfoDto(String message,Map<String, String>contactDetails, List<String> onCallSupport) {

}

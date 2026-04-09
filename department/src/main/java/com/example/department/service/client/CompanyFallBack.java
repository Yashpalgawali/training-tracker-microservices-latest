package com.example.department.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.department.dto.CompanyDto;

@Component
public class CompanyFallBack implements CompanyFeignClient {

	@Override
	public ResponseEntity<CompanyDto> retrieveCompanyById(String correlationId, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}

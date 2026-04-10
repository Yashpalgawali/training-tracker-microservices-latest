package com.example.department.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.department.dto.CompanyDto;


@FeignClient(name = "company", fallback = CompanyFallBack.class )
public interface CompanyFeignClient {

	@GetMapping(value= "/api/{id}",consumes = "application/json")
	public ResponseEntity<CompanyDto> retrieveCompanyById(@RequestHeader("trainingtracker-correlation-id") String correlationId, 
																		@PathVariable Long id);
}

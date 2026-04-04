package com.example.department.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.department.dto.CompanyDto;


@FeignClient(value = "company")
public interface CompanyFeignClient {

	@GetMapping(value= "/api/{id}",consumes = "application/json")
	public ResponseEntity<CompanyDto> retrieveCompanyById(@PathVariable Long id) ;
}

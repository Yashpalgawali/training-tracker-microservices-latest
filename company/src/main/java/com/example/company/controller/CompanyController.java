package com.example.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.company.constants.CompanyConstants;
import com.example.company.dto.CompanyContactsInfoDto;
import com.example.company.dto.CompanyDto;
import com.example.company.dto.ResponseDto;
import com.example.company.entity.Company;
import com.example.company.service.ICompanyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping(path="/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
@RequiredArgsConstructor
@Validated
public class CompanyController {

	private final ICompanyService compserv;
	
	@Value("${build.version}")
	private String buildVersion;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CompanyContactsInfoDto companyContactInfoDto;
	
	@PostMapping("/")
	public ResponseEntity<ResponseDto> createCompany(@Valid @RequestBody CompanyDto companyDto) {
		compserv.createCompany(companyDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(CompanyConstants.STATUS_201, CompanyConstants.MESSAGE_201));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Company> retrieveCompanyById(@PathVariable Long id) {
		
		Company company = compserv.retrieveCompanyById(id);
		return ResponseEntity.status(HttpStatus.OK).body(company);
	} 
	
	@GetMapping("/")
	public ResponseEntity<List<Company>> getAllCompaniesById() {
		
		var compList = compserv.getAllCompanies();
		return ResponseEntity.status(HttpStatus.OK).body(compList);
	}
	
	@GetMapping("/build-info")
	public ResponseEntity<String> getBuildInfo(){
		return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
	}
	
	@GetMapping("/java-version")
	public ResponseEntity<String> getJavaVersion(){
		return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
	}
	
	@GetMapping("/contact-info")
	public ResponseEntity<CompanyContactsInfoDto> getContactInfo(){
		return ResponseEntity.status(HttpStatus.OK).body(companyContactInfoDto);
	}
}

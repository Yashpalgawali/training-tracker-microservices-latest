package com.example.designation.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.designation.dto.DesignationContactsInfoDto;
import com.example.designation.dto.DesignationDto;
import com.example.designation.dto.ResponseDto;
import com.example.designation.entity.Designation;
import com.example.designation.service.IDesignationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value="/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class DesignationController {

	private final IDesignationService desigserv;
	
	@Value("${build.version}")
	private String buildVersion;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private DesignationContactsInfoDto designationContactInfoDto;
	
	private final Logger logger = LoggerFactory.getLogger(DesignationController.class);
	
	@PostMapping("/")
	public ResponseEntity<ResponseDto> createDesignation(@Valid @RequestBody DesignationDto desigDto) {
		desigserv.createDesignation(desigDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.toString(), "Designation "+desigDto.getDesignation()+" is created successfully"));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Designation> getDesignationById(@PathVariable Long id) {
		Designation designation = desigserv.getDesignationById(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(designation);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Designation>> getAllDesignations() {
		List<Designation> desigList = desigserv.getAllDesignations();
		return ResponseEntity.status(HttpStatus.OK).body(desigList);
	}
	
	@PutMapping("/")
	public ResponseEntity<ResponseDto> updateDesignations(@Valid @RequestBody DesignationDto desigDto) {
		desigserv.updateDesignation(desigDto);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(), "Designation "+desigDto.getDesignation()+" is updated successfully"));
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
	public ResponseEntity<DesignationContactsInfoDto> getContactInfo(){
		logger.debug("Invoked department controller contacts info {} ");
		return ResponseEntity.status(HttpStatus.OK).body(designationContactInfoDto);
	}
}

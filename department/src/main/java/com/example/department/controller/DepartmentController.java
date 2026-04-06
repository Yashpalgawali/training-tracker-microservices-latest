package com.example.department.controller;

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

import com.example.department.constants.DepartmentConstants;
import com.example.department.dto.DepartmentDto;
import com.example.department.dto.DepartmentsContactInfoDto;
import com.example.department.dto.ResponseDto;
import com.example.department.service.IDepartmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value="api",produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@RequiredArgsConstructor
public class DepartmentController {

	private final IDepartmentService deptserv;
	
	@Value("${build.version}")
	private String buildVersion;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private DepartmentsContactInfoDto departmentContactInfoDto;
	
	@PostMapping("/")
	public ResponseEntity<ResponseDto> createDepartment(@Valid @RequestBody DepartmentDto departmentDto){

		deptserv.createDepartment(departmentDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(DepartmentConstants.STATUS_201,DepartmentConstants.MESSAGE_201));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id){

		DepartmentDto dept = deptserv.getDepartmentById(id);
		return ResponseEntity.status(HttpStatus.OK).body(dept);
	}

	@GetMapping("/")
	public ResponseEntity<List<DepartmentDto>> getAllDepartments() {		
		List<DepartmentDto> deptList = deptserv.getAllDepartments();
		return ResponseEntity.status(HttpStatus.OK).body(deptList);
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
	public ResponseEntity<DepartmentsContactInfoDto> getContactInfo(){
		return ResponseEntity.status(HttpStatus.OK).body(departmentContactInfoDto);
	}
}

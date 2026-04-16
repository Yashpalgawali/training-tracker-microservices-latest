package com.example.department.controller;

import java.util.List;
import java.util.concurrent.TimeoutException;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.department.constants.DepartmentConstants;
import com.example.department.dto.DepartmentDto;
import com.example.department.dto.DepartmentsContactInfoDto;
import com.example.department.dto.ResponseDto;
import com.example.department.service.IDepartmentService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
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
	
	private static final Logger logger =  LoggerFactory.getLogger(DepartmentController.class);
	
	@PostMapping("/")
	public ResponseEntity<ResponseDto> createDepartment(@Valid @RequestBody DepartmentDto departmentDto){

		deptserv.createDepartment(departmentDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(DepartmentConstants.STATUS_201,DepartmentConstants.MESSAGE_201));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DepartmentDto> getDepartmentById(@RequestHeader("trainingtracker-correlation-id")String correlationId,@PathVariable Long id){

		DepartmentDto dept = deptserv.getDepartmentById(id,correlationId);
		return ResponseEntity.status(HttpStatus.OK).body(dept);
	}

	@GetMapping("/")
	public ResponseEntity<List<DepartmentDto>> getAllDepartmentsApi(@RequestHeader("trainingtracker-correlation-id") String correlationId ) {		
		List<DepartmentDto> deptList = deptserv.getAllDepartments(correlationId);
	 	
		logger.debug("training tracker -correlation-id found : {} ",correlationId);
		return ResponseEntity.status(HttpStatus.OK).body(deptList);
	}
	
	@Retry(name = "getBuildInfo", fallbackMethod = "getBuildInfoFallBack")
	@GetMapping("/build-info")
	public ResponseEntity<String> getBuildInfo() throws TimeoutException{
		logger.error("getBuildInfo invoked");
//		throw new TimeoutException();
		return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
	}
	
	public ResponseEntity<String> getBuildInfoFallBack(Throwable throwable){
		logger.error("getBuildInfoFallBack invoked");
		return ResponseEntity.status(HttpStatus.OK).body("0.7");
	}
	
	@GetMapping("/java-version")
	@RateLimiter(name = "getJavaVersion", fallbackMethod = "getJavaVersionFallBack")
	public ResponseEntity<String> getJavaVersion(){
		return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
	}
	
	public ResponseEntity<String> getJavaVersionFallBack(Throwable throwable) {
		return ResponseEntity.status(HttpStatus.OK).body("JAVA 17");
	}
	
	@GetMapping("/contact-info")
	public ResponseEntity<DepartmentsContactInfoDto> getContactInfo(){
		logger.debug("Departments contact info invoked");
		return ResponseEntity.status(HttpStatus.OK).body(departmentContactInfoDto);
	}
}

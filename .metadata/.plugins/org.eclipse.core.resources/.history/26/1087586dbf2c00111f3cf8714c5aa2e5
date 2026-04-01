package com.example.department.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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
import com.example.department.dto.ResponseDto;
import com.example.department.entity.Department;
import com.example.department.service.IDepartmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api")
@Validated
@RequiredArgsConstructor
public class DepartmentController {

	private final IDepartmentService deptserv;
	
	@PostMapping("/")
	public ResponseEntity<ResponseDto> createDepartment(@Valid @RequestBody DepartmentDto departmentDto){
		
		deptserv.createDepartment(departmentDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(DepartmentConstants.STATUS_201,DepartmentConstants.MESSAGE_201));
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable Long id){
		
		Department dept = deptserv.getDepartmentById(id);
		return ResponseEntity.status(HttpStatus.OK).body(dept);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Department>> getAllDepartments() {		
		List<Department> deptList = deptserv.getAllDepartments();
		return ResponseEntity.status(HttpStatus.OK).body(deptList);
	}
}

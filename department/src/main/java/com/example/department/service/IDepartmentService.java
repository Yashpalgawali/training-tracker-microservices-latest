package com.example.department.service;

import java.util.List;

import com.example.department.dto.DepartmentDto;

public interface IDepartmentService {

	public void createDepartment(DepartmentDto deptDto);
	
	public DepartmentDto getDepartmentById(Long deptId,String correlationId);
	
	public List<DepartmentDto> getAllDepartments(String correlationId);
	
}

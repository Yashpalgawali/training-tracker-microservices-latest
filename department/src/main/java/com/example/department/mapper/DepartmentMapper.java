package com.example.department.mapper;

import com.example.department.dto.DepartmentDto;
import com.example.department.entity.Department;

public class DepartmentMapper {

	public static DepartmentDto mapToDepartmentDto(Department department,DepartmentDto deptDto) {
		deptDto.setDepartmentId(department.getDepartmentId());
		deptDto.setDepartmentName(department.getDepartmentName());
		deptDto.setCompanyId(department.getCompanyId());
		
		return deptDto;
	}
	
	public static Department mapToDepartment(DepartmentDto deptDto, Department department) {
		department.setCompanyId(deptDto.getDepartmentId());
		department.setDepartmentName(deptDto.getDepartmentName());
		department.setCompanyId(deptDto.getCompanyId());
		
		return department;
	}
}

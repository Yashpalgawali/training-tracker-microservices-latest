package com.example.department.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.department.dto.CompanyDto;
import com.example.department.dto.DepartmentDto;
import com.example.department.entity.Department;
import com.example.department.exception.GlobalException;
import com.example.department.exception.ResourceAlreadyExistsException;
import com.example.department.exception.ResourceNotFoundException;
import com.example.department.mapper.DepartmentMapper;
import com.example.department.repository.DepartmentRepository;
import com.example.department.service.IDepartmentService;
import com.example.department.service.client.CompanyFeignClient;

import lombok.RequiredArgsConstructor;

@Service("deptserv")
@RequiredArgsConstructor
public class DepartmentServImpl implements IDepartmentService {

	private final DepartmentRepository deptrepo;

	private final CompanyFeignClient companyclient;

	@Override
	public void createDepartment(DepartmentDto deptDto) {
		Department dept = DepartmentMapper.mapToDepartment(deptDto, new Department());
		Optional<Department> found = deptrepo.findByDepartmentName(deptDto.getDepartmentName());
		if (found.isPresent()) {
			throw new ResourceAlreadyExistsException("Department", "department", deptDto.getDepartmentName());
		}

		var savedDept = deptrepo.save(dept);
		if (savedDept == null) {
			throw new GlobalException("Department " + deptDto.getDepartmentName() + " is not saved");
		}
	}

	@Override
	public DepartmentDto getDepartmentById(Long deptId) {

		Department department = deptrepo.findById(deptId)
				.orElseThrow(() -> new ResourceNotFoundException("Department", "Department ID", "" + deptId));

		DepartmentDto mappedDto = DepartmentMapper.mapToDepartmentDto(department, new DepartmentDto());

		try {
			ResponseEntity<CompanyDto> companyDto = companyclient.retrieveCompanyById(mappedDto.getCompanyId());
			if (companyDto.getStatusCode().is2xxSuccessful()) {
				CompanyDto company = companyDto.getBody();

				mappedDto.setCompanyName(company.getCompanyName());
			} else {
				mappedDto.setCompanyName("Company details not available");
			}

		} catch (Exception e) {
			// Fallback if service fails
			mappedDto.setCompanyName("Company service unavailable");
		}
		return mappedDto;

	}

	@Override
	public List<DepartmentDto> getAllDepartments() {
		var deptList = deptrepo.findAll();
		List<DepartmentDto> deptDtoList = deptList.stream().map((dept) -> {
			ResponseEntity<CompanyDto> companyDto = companyclient.retrieveCompanyById(dept.getCompanyId());
			CompanyDto body = companyDto.getBody();

			DepartmentDto deptDto = new DepartmentDto();
			deptDto.setDepartmentId(dept.getDepartmentId());
			deptDto.setDepartmentName(dept.getDepartmentName());
			deptDto.setCompanyName(body.getCompanyName());

			return deptDto;

		}).collect(Collectors.toList());
		if (deptList.size() > 0) {
			return deptDtoList;
		}
		throw new GlobalException("No Department(s) found");
	}

}

package com.example.department.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.department.entity.Department;
import java.util.List;


@Repository("deptrepo")
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	Optional<Department> findByDepartmentName(String departmentName);
	
	List<Department> findByCompanyId(Long companyId);
}

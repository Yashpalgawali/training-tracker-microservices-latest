package com.example.department.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tbl_department")
@Getter
@Setter @AllArgsConstructor @NoArgsConstructor
public class Department extends BaseEntity{

	@Id
	@SequenceGenerator(name = "dept_seq" ,allocationSize = 1,initialValue = 1)
	@GeneratedValue(generator = "dept_seq",strategy = GenerationType.SEQUENCE)
	Long departmentId;
	
	String departmentName;
	
	Long companyId;
}

package com.example.department.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentDto {

	Long departmentId;
	
	@NotBlank(message = "Department name can't be blank")
	@Size(min = 2, max = 20, message = "Department name must have at lease 2 characters")
	String departmentName;

	@NotNull(message = "Company ID can't be blank")
	Long companyId;

	String companyName;

}

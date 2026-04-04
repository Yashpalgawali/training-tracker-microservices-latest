package com.example.company.mapper;

import com.example.company.dto.CompanyDto;
import com.example.company.entity.Company;

public class CompanyMapper {
	
	public static CompanyDto mapToCompanyDto(Company company, CompanyDto companyDto) {
		
		companyDto.setCompanyId(company.getCompanyId());
		companyDto.setCompanyName(company.getCompanyName());		
		return companyDto;
	}
	
	public static Company mapToCompany(CompanyDto companyDto , Company company) {
		
		company.setCompanyId(companyDto.getCompanyId());
		company.setCompanyName(companyDto.getCompanyName());		
		return company;
	}
}

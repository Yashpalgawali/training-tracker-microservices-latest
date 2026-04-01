package com.example.company.service;

import java.util.List;

import com.example.company.dto.CompanyDto;
import com.example.company.entity.Company;

public interface ICompanyService {

	public void createCompany(CompanyDto companyDto);
	
	public Company retrieveCompanyById(Long id);
	
	public List<Company> getAllCompanies();
	
	public void updateCompany(CompanyDto companyDto);
}

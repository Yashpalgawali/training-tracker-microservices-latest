package com.example.designation.service;

import java.util.List;

import com.example.designation.dto.DesignationDto;
import com.example.designation.entity.Designation;

public interface IDesignationService {

	public void createDesignation(DesignationDto designationDto);

	public Designation getDesignationById(Long id);

	public List<Designation> getAllDesignations();
	
	public void updateDesignation(DesignationDto designationDto);
}

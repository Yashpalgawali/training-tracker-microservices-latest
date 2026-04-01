package com.example.designation.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.designation.dto.DesignationDto;
import com.example.designation.entity.Designation;
import com.example.designation.exception.GlobalException;
import com.example.designation.exception.ResourceAlreadyExistsException;
import com.example.designation.exception.ResourceNotFoundException;
import com.example.designation.exception.ResourceNotModifiedException;
import com.example.designation.mapper.DesignationMapper;
import com.example.designation.repository.DesignationRepository;
import com.example.designation.service.IDesignationService;

import lombok.RequiredArgsConstructor;

@Service("desigserv")
@RequiredArgsConstructor
public class DesignationServImpl implements IDesignationService {

	private final DesignationRepository desigrepo;
	
	@Override
	public void createDesignation(DesignationDto designationDto) {
		
		Optional<Designation> isDesignationPresent = desigrepo.findByDesignation(designationDto.getDesignation());
		if(isDesignationPresent.isPresent()) {
			throw new ResourceAlreadyExistsException("Designation","name", designationDto.getDesignation());
		}
		
		Designation mappedDesignation = DesignationMapper.maptoDesignation(designationDto, new Designation());
		Designation savedDesignation = desigrepo.save(mappedDesignation);

		if(savedDesignation == null) {
			throw new GlobalException("Designation "+designationDto.getDesignation()+" is not saved");
		}
	}

	@Override
	public Designation getDesignationById(Long id) {
 
		Designation designation = desigrepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Designation", "designation", ""+id));
		return designation;
	}

	@Override
	public List<Designation> getAllDesignations() {
		var desigList = desigrepo.findAll();
		if(desigList.size()> 0 )
			return desigList;
		throw new GlobalException("No Designation(s) found");
	}

	@Override
	@Transactional
	public void updateDesignation(DesignationDto designationDto) {
		
		Designation desigDto = this.getDesignationById(designationDto.getDesignationId());
		
		int result = desigrepo.updateDesignation(desigDto.getDesignationId(), desigDto.getDesignation());
		if(result < 0 ) {
			throw new ResourceNotModifiedException("Designation" , "designation" , ""+designationDto.getDesignation());
		}
	}

}

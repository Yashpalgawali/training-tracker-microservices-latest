package com.example.designation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 36665167444751556L;

	public ResourceAlreadyExistsException(String resourceName,String fieldName,String fieldValue) {
		super(String.format("%s resource is already present with %s data : '%s' ",resourceName,fieldName,fieldValue));
	}

	
}

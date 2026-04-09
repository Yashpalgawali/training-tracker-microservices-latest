package com.example.department.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6193371562810931079L;

	public ResourceAlreadyExistsException(String resourceName,String fieldName, String fieldValue) {
		super(String.format("%s is already present given data %s for input : '%s' ", resourceName,fieldName, fieldValue));
	}

	
}

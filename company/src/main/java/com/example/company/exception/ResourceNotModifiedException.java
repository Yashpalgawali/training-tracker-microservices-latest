package com.example.company.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceNotModifiedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2786149809004159978L;

	public ResourceNotModifiedException(String resourceName,String fieldName,String fieldValue) {
		super(String.format("%s not updated with the given input data %s : '%s' ",resourceName,fieldName, fieldValue ));
	}

	
}

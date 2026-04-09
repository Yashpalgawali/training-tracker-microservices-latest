package com.example.department.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
public class GlobalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6193371562810931079L;

	public GlobalException(String message) {
		super(message);
	}

	
}

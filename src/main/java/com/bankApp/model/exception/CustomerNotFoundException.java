package com.bankApp.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -2085653895711916248L;

	public CustomerNotFoundException(String message) {
		super(message);
	}
}
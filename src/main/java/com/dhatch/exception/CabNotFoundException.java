package com.dhatch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CabNotFoundException extends RuntimeException {

	public CabNotFoundException(String message, Throwable cause) {
		super(message, cause);

	}

	public CabNotFoundException(String message) {
		super(message);

	}

	public CabNotFoundException(Throwable cause) {
		super(cause);

	}

}
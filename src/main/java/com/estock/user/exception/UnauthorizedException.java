package com.estock.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.UNAUTHORIZED, reason = "Expired or invalid JWT")
public class UnauthorizedException extends RuntimeException {
	
	public UnauthorizedException(String message) {
		super(message);
	}
	public UnauthorizedException() {
	}

}

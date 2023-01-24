package com.estock.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.UNAUTHORIZED, reason = "Expired or invalid JWT")
public class UnauthorizedException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2613490246180445290L;
	
	public UnauthorizedException(String message) {
		super(message);
	}

	public UnauthorizedException(String msg, Exception e) {
		super(msg, e);
	}
	
	public UnauthorizedException() {
	}

}

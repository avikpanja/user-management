package com.estock.user.exception;

public class InvalidTokenException extends RuntimeException {
	
	private static final long serialVersionUID = 2613490246180445290L;
	
	public InvalidTokenException(String message) {
		super(message);
	}

	public InvalidTokenException(String msg, Exception e) {
		super(msg, e);
	}
	
	public InvalidTokenException() {
	}
}

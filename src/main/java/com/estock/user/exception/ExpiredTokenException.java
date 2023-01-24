package com.estock.user.exception;

public class ExpiredTokenException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4741081344541346594L;

	public ExpiredTokenException(String message) {
		super(message);
	}

	public ExpiredTokenException(String msg, Exception e) {
		super(msg, e);
	}
	
	public ExpiredTokenException() {
	}
}

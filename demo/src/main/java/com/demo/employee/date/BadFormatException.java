package com.demo.employee.date;

public class BadFormatException extends RuntimeException {
 
	private String message;

	public BadFormatException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}

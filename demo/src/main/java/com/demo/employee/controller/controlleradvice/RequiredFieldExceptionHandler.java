package com.demo.employee.controller.controlleradvice;

public class RequiredFieldExceptionHandler extends RuntimeException {
	private String message;

	public RequiredFieldExceptionHandler(String message) {
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

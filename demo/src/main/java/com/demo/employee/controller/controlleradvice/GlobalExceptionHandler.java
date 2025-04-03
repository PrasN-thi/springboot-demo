package com.demo.employee.controller.controlleradvice;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(RequiredFieldExceptionHandler.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(RequiredFieldExceptionHandler ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("errorMessage", ex.getMessage());
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

}

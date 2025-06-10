package com.ss.spring_security.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

// This class handles exceptions globally (for all controllers)
@ControllerAdvice
public class GlobalExceptionHandler {

	// This method handles custom TodoAPIException
	@ExceptionHandler(TodoAPIException.class)
	public ResponseEntity<ErrorDetails> handleTodoAPIException(TodoAPIException exception, WebRequest webRequest) {

		// Create an error response object with time, message, and request details
		ErrorDetails errorDetails = new ErrorDetails(
			LocalDateTime.now(),                     // Current time
			exception.getMessage(),                 // Custom exception message
			webRequest.getDescription(false)        // Request details (like URL)
		);

		// Send the error response with 400 Bad Request status
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}

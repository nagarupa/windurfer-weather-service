package com.weather.api.exception;

import com.weather.api.exception.handler.InvalidDateException;
import com.weather.api.exception.handler.WindSurferLocationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.Date;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(WindSurferLocationNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleWindSurferLocationNotFoundException(WindSurferLocationNotFoundException ex, WebRequest request) {
		
		ErrorDetails errorDetails = new ErrorDetails(
				new Date(),
				HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.name(),
				ex.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(InvalidDateException.class)
	public final ResponseEntity<ErrorDetails> handleInvalidDateException(InvalidDateException ex, WebRequest request) {
		
		ErrorDetails errorDetails = new ErrorDetails(
				new Date(), 
				HttpStatus.NOT_ACCEPTABLE.value(),
				HttpStatus.NOT_ACCEPTABLE.name(),
				ex.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllOtherExceptions(Exception ex, WebRequest request) {

		ErrorDetails errorDetails = new ErrorDetails(
				new Date(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.name(),
				ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
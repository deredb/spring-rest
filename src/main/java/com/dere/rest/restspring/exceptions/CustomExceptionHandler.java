package com.dere.rest.restspring.exceptions;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = { RecordNotFoundException.class })
	public ResponseEntity<Object> handleConflict(RecordNotFoundException noEleExc) {
		
		return new ResponseEntity<>(noEleExc.getMessage(), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintValidationException(ConstraintViolationException e) {

		Map<String, String> errors = new HashMap<String, String>();
		e.getConstraintViolations().forEach(violation -> {

			errors.put(violation.getPropertyPath().toString(), violation.getMessage());

		});
		return error(errors, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
	  HttpRequestMethodNotSupportedException ex, 
	  HttpHeaders headers, 
	  HttpStatus status, 
	  WebRequest request) {
	    StringBuilder builder = new StringBuilder();
	    builder.append(ex.getMethod());
	    builder.append(
	      " method is not supported for this request. Supported methods are ");
	    ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
	    return error(builder.toString(), HttpStatus.METHOD_NOT_ALLOWED);
	   
	}

	
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex) {
		return error(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private ResponseEntity<Object> error(Object object, HttpStatus statusCode) {
		return new ResponseEntity<>(object, statusCode);
	}


}

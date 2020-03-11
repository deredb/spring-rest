package com.dere.rest.restspring.exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value= {RecordNotFoundException.class})
	protected ResponseEntity<Object> handleConflict( 
			RecordNotFoundException noEleExc, WebRequest request){
	    	return new ResponseEntity<>(noEleExc.getMessage(), HttpStatus.NOT_FOUND);
	  
		
	}
}

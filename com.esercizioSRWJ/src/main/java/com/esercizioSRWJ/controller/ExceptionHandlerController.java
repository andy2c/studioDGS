package com.esercizioSRWJ.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.esercizioSRWJ.exception.AfterDateError;
import com.esercizioSRWJ.exception.BeforeDateError;
import com.esercizioSRWJ.exception.EntityNotFoundError;
import com.esercizioSRWJ.exception.FormatError;
import com.esercizioSRWJ.exception.MaxLengthError;
import com.esercizioSRWJ.exception.RequiredFieldError;
import com.esercizioSRWJ.exception.UniqueFieldError;

@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(value = AfterDateError.class)
	public ResponseEntity<Object> exception(AfterDateError e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(value = BeforeDateError.class)
	public ResponseEntity<Object> exception(BeforeDateError e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(value = EntityNotFoundError.class)
	public ResponseEntity<Object> exception(EntityNotFoundError e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = FormatError.class)
	public ResponseEntity<Object> exception(FormatError e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(value = MaxLengthError.class)
	public ResponseEntity<Object> exception(MaxLengthError e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(value = RequiredFieldError.class)
	public ResponseEntity<Object> exception(RequiredFieldError e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(value = UniqueFieldError.class)
	public ResponseEntity<Object> exception(UniqueFieldError e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Object> exception(HttpRequestMethodNotSupportedException e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
	}

}

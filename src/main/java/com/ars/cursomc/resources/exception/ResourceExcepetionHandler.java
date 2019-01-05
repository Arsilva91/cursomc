package com.ars.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ars.cursomc.services.exception.ObjectNotFoundExcepetion;

@ControllerAdvice
public class ResourceExcepetionHandler {
	
	@ExceptionHandler(ObjectNotFoundExcepetion.class)
	public ResponseEntity<StanderError> objectNotFound(ObjectNotFoundExcepetion e, HttpServletRequest request) {
		StanderError err = new StanderError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
}
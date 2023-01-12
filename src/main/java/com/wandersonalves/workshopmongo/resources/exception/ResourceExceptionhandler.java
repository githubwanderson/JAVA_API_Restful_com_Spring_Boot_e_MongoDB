package com.wandersonalves.workshopmongo.resources.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wandersonalves.workshopmongo.services.exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionhandler {	

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		Long timeSistem = System.currentTimeMillis();
		HttpStatus status = HttpStatus.NOT_FOUND;
		String erro = "Não encontrado";
		String msg	= e.getMessage(); 
		String path = request.getRequestURI(); 
		StandardError err = new StandardError( timeSistem , status.value() , erro , msg , path );
		
		return ResponseEntity.status(status).body(err);
	}
	
}

package com.soccergao.exception;

import javax.persistence.EntityNotFoundException;

import org.apache.http.auth.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
	
//	@ExceptionHandler({Exception.class})
//    public ResponseEntity<String> handleException(Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//    }
//	
//	@ExceptionHandler({RuntimeException.class})
//    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//    }
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> handleEntityNotFoundExceptionHandler(EntityNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}

package com.ssafy.final_project.controller;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.final_project.error.HouseLikeException;
import com.ssafy.final_project.error.NotSatisfiedAthorityException;

@RestControllerAdvice
public class ExceptionRestControllerAdvice {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> TotalExceptionHandel(Exception e) {
		
		System.out.println(e.getClass().getName());
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NotSatisfiedAthorityException.class)
	public ResponseEntity<?> SecurityExceptionHandel(NotSatisfiedAthorityException e) {
		
		
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(HouseLikeException.class)
	public ResponseEntity<?> HouseLikeException(HouseLikeException e){
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity<?> DuplicateId(DuplicateKeyException e){
		return new ResponseEntity<String>("아이디 중복",HttpStatus.CONFLICT);
	}
}

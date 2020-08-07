package com.gospec.exception;

import java.sql.SQLException;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.gospec.domain.ErrorMessageDetail;

@ControllerAdvice
public class ExceptionControllerAdvice{
	
	@ExceptionHandler(value = {SQLException.class})
	public ResponseEntity<ErrorMessageDetail> ex(SQLException e, WebRequest request) {
		System.out.println(e.getMessage());
		ErrorMessageDetail emd = new ErrorMessageDetail(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorMessageDetail>(emd, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}

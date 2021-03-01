package com.FoodSite.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Vijay
 * Exception Handler FoodSiteExceptionHandler
 */
@RestControllerAdvice
public class FoodSiteExceptionHandler {

	/**
	 * @param NoDataFoundException
	 * @return CustomMessage
	 * This method Handles the Exception
	 */
	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<CustomMessage> handleNoDataFoundException(NoDataFoundException e)
	{
		CustomMessage customMessageObj=new CustomMessage();
		customMessageObj.setError("400");
		customMessageObj.setMessage(e.getMessage());
		customMessageObj.setStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<CustomMessage>(customMessageObj,HttpStatus.BAD_REQUEST);
	}
}

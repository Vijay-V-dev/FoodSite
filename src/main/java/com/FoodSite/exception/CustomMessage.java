package com.FoodSite.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Vijay
 * CustomMessage class contains the parameters which are used to throw the 
 * Exception information.
 */
public class CustomMessage {

	private String error;
	private String message;
	private HttpStatus status;
	public CustomMessage(String error, String message, HttpStatus status) {
		super();
		this.error = error;
		this.message = message;
		this.status = status;
	}
	public CustomMessage() {
		super();
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	
}

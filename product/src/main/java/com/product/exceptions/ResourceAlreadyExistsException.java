package com.product.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = -371543894305463744L;
	private String message;
    public ResourceAlreadyExistsException() {
    }
 
    public ResourceAlreadyExistsException(String message) {
    	 this.message = message;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

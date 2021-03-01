package com.product.exceptions;

import java.util.ArrayList;
import java.util.List;

public class BadResourceException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private List<String> errorMessages = new ArrayList<>();
    
    public BadResourceException() {
    }
 
    public BadResourceException(String msg) {
        super(msg);
    }
    
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }
 
    public void addErrorMessage(String message) {
        this.errorMessages.add(message);
    }
}
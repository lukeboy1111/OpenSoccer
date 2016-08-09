package com.lukeyboy1.utility.exceptions;

public class SpecificException extends Exception {
	private String exceptionText;


	public SpecificException(String str) {
		exceptionText = str;
	}

	public String getErrorMessage() {
		return exceptionText;
	}
	
	public String getExceptionText() {
		return exceptionText;
	}

	public void setExceptionText(String exceptionText) {
		this.exceptionText = exceptionText;
	}
	
	
	
}

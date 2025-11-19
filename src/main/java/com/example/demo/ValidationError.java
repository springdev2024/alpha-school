package com.example.demo;

public class ValidationError {
	
	private boolean hasError;
	private String error;
	
	public ValidationError(String erorr) {
		this.error = error;
		this.hasError = true;
	}
	
	public boolean isHasError() {
		return hasError;
	}
	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}

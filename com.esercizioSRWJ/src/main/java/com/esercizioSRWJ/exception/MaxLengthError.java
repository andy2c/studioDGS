package com.esercizioSRWJ.exception;

public class MaxLengthError extends FieldError {
	private int maxLength;
	public MaxLengthError(String field,int maxLength) {
		super(field);
		this.maxLength=maxLength;
	
	}
	public int getMaxLength() {
		return maxLength;
	}
	
	
}

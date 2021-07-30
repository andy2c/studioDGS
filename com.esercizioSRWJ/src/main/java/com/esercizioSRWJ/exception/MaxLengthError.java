package com.esercizioSRWJ.exception;

public class MaxLengthError extends FieldError {
	public MaxLengthError(String field,Integer maxLength) {
		super(field, maxLength);
		this.setMessage("Il campo " + this.field + 
				" deve essere al massimo di "+ this.maxLength + " caratteri");
	
	}
}

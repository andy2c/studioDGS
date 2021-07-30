package com.esercizioSRWJ.exception;

public class UniqueFieldError extends FieldError {
	public UniqueFieldError(String field) {
		super(field);
		this.setMessage("Il valore del campo " +this.field+ " è già presente");
	
	}
	
	
	
}
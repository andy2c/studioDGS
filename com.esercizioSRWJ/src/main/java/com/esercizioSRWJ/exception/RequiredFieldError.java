package com.esercizioSRWJ.exception;

public class RequiredFieldError  extends FieldError {
	public RequiredFieldError(String field) {
		super(field);
		this.setMessage("Il campo " + this.field+ " è richiesto ");
	}
	
}
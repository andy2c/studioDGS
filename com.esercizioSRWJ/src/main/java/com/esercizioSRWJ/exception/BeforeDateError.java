package com.esercizioSRWJ.exception;

public class BeforeDateError extends FieldError{

	public BeforeDateError(String field) {
		super(field);
		this.setMessage("Il campo " + this.field+" ha una data che precede il limite consentito");
	}

}
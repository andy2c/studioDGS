package com.esercizioSRWJ.exception;

public class AfterDateError extends FieldError{

	public AfterDateError(String field) {
		super(field);
		this.setMessage("Il campo " +this.field+
							" ha una data che supera il limite consentito");
	}

}

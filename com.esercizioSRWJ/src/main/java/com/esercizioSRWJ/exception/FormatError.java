package com.esercizioSRWJ.exception;

public class FormatError  extends FieldError {
	public FormatError(String field) {
		super(field);
		this.setMessage("Il campo " + this.field+ " non è nel formato previsto");
	}
	
}

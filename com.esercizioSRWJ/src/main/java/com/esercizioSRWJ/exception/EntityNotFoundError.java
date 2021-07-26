package com.esercizioSRWJ.exception;

public class EntityNotFoundError extends FieldError{
	public EntityNotFoundError(String field) {
		super(field);
	}
}

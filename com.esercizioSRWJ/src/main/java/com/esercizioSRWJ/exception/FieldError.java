package com.esercizioSRWJ.exception;

public class FieldError extends Exception {

	protected String field;
	protected String message;
	protected Integer maxLength;

	public FieldError(String field) {
		super();
		this.field = field;
	}

	public FieldError(String field, Integer maxLength) {
		super();
		this.field = field;
		this.maxLength = maxLength;
	}
	
	public String getField() {
		return field;
	}
	
	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}
	
	public String getDescription(Exception ex) {
		return this.message;
	}
	

//	public String getDescription(Exception ex) {
//		
//		String clss = String.valueOf(ex.getClass().getSimpleName());
		
//		switch(clss) {
//		  case "RequiredFieldError": {
//			  RequiredFieldError rq = (RequiredFieldError) ex;
//			  return "Il campo " + rq.getField()+ " � richiesto ";
//		  }
//		  case "MaxLengthError": {
//			  MaxLengthError rq = (MaxLengthError) ex;
//			  return "Il campo " + rq.getField()+ " deve essere al massimo di "+ rq.getMaxLength()+ " caratteri";
//		  }
		  
//		  case "UniqueFieldError": {
//			  UniqueFieldError rq = (UniqueFieldError) ex;
//			  return "Il campo " + rq.getField()+ " deve essere univoco";
//		  }
		  
//		  case "FormatError": {
//			  FormatError rq = (FormatError) ex;
//			  return "Il campo " + rq.getField()+ " non � nel formato previsto";
//		  }
		  
//		  case "EntityNotFoundError": {
//			  EntityNotFoundError rq = (EntityNotFoundError) ex;
//			  return   rq.getField()+ " non trovato ";
//		  }
		  
//		  case "AfterDateError": {
//			  AfterDateError rq = (AfterDateError) ex;
//			  return   "Il campo " + rq.getField()+ " ha una data che supera il limite consentito";
//		  }
		  
//		  case "BeforeDateError": {
//			  BeforeDateError rq = (BeforeDateError) ex;
//			  return   "Il campo " + rq.getField()+ " ha una data che precede il limite consentito";
//		  }
		  
//		  default:
//			  return "";
//		}

//	}
	
}

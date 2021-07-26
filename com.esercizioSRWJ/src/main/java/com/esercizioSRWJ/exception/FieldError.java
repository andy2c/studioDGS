package com.esercizioSRWJ.exception;

public class FieldError extends Exception {

	private String field;

	public FieldError(String field) {
		super();
		this.field = field;
	}

	public String getDescription(Exception ex) {
		
		String clss = String.valueOf(ex.getClass().getSimpleName());
		
		switch(clss) {
		  case "RequiredFieldError": {
			  RequiredFieldError rq = (RequiredFieldError) ex;
			  return "Il campo " + rq.getField()+ " � richiesto ";
		  }
		  case "MaxLengthError": {
			  MaxLengthError rq = (MaxLengthError) ex;
			  return "Il campo " + rq.getField()+ " deve essere al massimo di "+ rq.getMaxLength()+ " caratteri";
		  }
		  
		  case "UniqueFieldError": {
			  UniqueFieldError rq = (UniqueFieldError) ex;
			  return "Il campo " + rq.getField()+ " deve essere univoco";
		  }
		  
		  case "FormatError": {
			  FormatError rq = (FormatError) ex;
			  return "Il campo " + rq.getField()+ " non � nel formato previsto";
		  }
		  
		  case "EntityNotFoundError": {
			  EntityNotFoundError rq = (EntityNotFoundError) ex;
			  return   rq.getField()+ " non trovato ";
		  }
		  
		  case "AfterDateError": {
			  AfterDateError rq = (AfterDateError) ex;
			  return   "Il campo " + rq.getField()+ " ha una data che supera il limite consentito";
		  }
		  
		  case "BeforeDateError": {
			  BeforeDateError rq = (BeforeDateError) ex;
			  return   "Il campo " + rq.getField()+ " ha una data che precede il limite consentito";
		  }
		  
		  default:
			  return "";
		}

//		if(ex instanceof RequiredFieldError) {
//			RequiredFieldError rq = (RequiredFieldError) ex;
//			return "Il campo " + rq.getField()+ " � richiesto ";
//		}
//		if(ex instanceof MaxLengthError) {
//			MaxLengthError rq = (MaxLengthError) ex;
//			return "Il campo " + rq.getField()+ " deve essere al massimo di "+ rq.getMaxLength()+ " caratteri";
//		}
//		if(ex instanceof UniqueFieldError) {
//			UniqueFieldError rq = (UniqueFieldError) ex;
//			return "Il campo " + rq.getField()+ " deve essere univoco";
//		}
//		if(ex instanceof FormatError) {
//			FormatError rq = (FormatError) ex;
//			return "Il campo " + rq.getField()+ " non � nel formato previsto";
//		}
//		if(ex instanceof EntityNotFoundError) {
//			EntityNotFoundError rq = (EntityNotFoundError) ex;
//			return   rq.getField()+ " non trovato ";
//		}
		
//		return "";

	}

	public String getField() {
		return field;
	}
	
	
	
	
	
	
}

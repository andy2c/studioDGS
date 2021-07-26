package com.esercizioSRWJ.exception;

public class GenericError extends Exception {
	public static final int C_999 = 999;
	public static final int A_001 = 001;
	public static final int A_002= 002;
	public static final int A_003= 003;
	public static final int A_004= 004;
	public static final int A_005= 005;
	public static final int B_001= 001;
	public static final int B_002= 002;
	public static final int B_003= 003;
	public static final int B_004= 004;
	public static final int C_001= 001;
	public static final int C_002= 002;
	public static final int C_003= 003;
	public static final int D_001= 001;
	public static final int D_002= 002;
	public static final int D_003= 003;
	public static final int B_005 = 005;
	
	private int code;
	public GenericError(int code) {
		this.code=code;
	}

	public int getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return "Errore sconosciuto n." + code+", contattare l'amministratore";
	}
	
	
	public static Integer getCode(Exception ex) {

		if(ex instanceof RequiredFieldError) {
			RequiredFieldError rq = (RequiredFieldError) ex;
			if(rq.getField().equals("codiceCollo"))
			return GenericError.A_001;
			if(rq.getField().equals("peso"))
			return GenericError.B_001;
			if(rq.getField().equals("prezzoConsegna"))
			return GenericError.C_001;
			
				
			
		}
		if(ex instanceof MaxLengthError) {
			MaxLengthError rq = (MaxLengthError) ex;
		   if(rq.getField().equals("codiceCollo"))
			   return GenericError.A_002;
		   if(rq.getField().equals("peso"))
			   return GenericError.B_002;
		   if(rq.getField().equals("prezzoConsegna"))
			   return GenericError.C_002;
			   
		}
		if(ex instanceof UniqueFieldError) {
			UniqueFieldError rq = (UniqueFieldError) ex;
			if(rq.getField().equals("codiceCollo"))
				   return GenericError.A_005;
		 
		}
		if(ex instanceof FormatError) {
			FormatError rq = (FormatError) ex;
		
		}
		
		
		return null;

	}
	
	
	
	
	
}

package com.esercizioSRWJ.utilities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;

import org.apache.commons.lang3.RandomStringUtils;

public class GeneralUtilities {
	
	//CONTROLLI GENERALI NULL E LUNGHEZZA
	
	public static boolean checkIfNull(Object data) {
		if (data == null)
			return true;
		return false;
	}
	
	public static boolean checkIfLonger(String data, Integer length) {
		return data.length()>length;
	}
	
	public static boolean checkIfLonger(LocalDateTime data, Integer length) {
		return checkIfLonger(String.valueOf(data), length);
	}
	
	public static boolean checkIfLonger(Double data, Integer length) {
		return checkIfLonger(String.valueOf(data), length);
	}
	
	public static boolean checkIfLonger(Integer data, Integer length) {
		return checkIfLonger(String.valueOf(data), length);
	}
	
	//OPERAZIONI SULLE DATE
	
	public static Timestamp getNow() {
		return new Timestamp(System.currentTimeMillis());
	}

	
//	public boolean afterDate(Timestamp baseDate, String dateToCmopare) {
//		return this.afterDate(baseDate, Timestamp.valueOf(dateToCmopare));
//	}
//	
//	public boolean afterDate(Timestamp baseDate) {
//		
//		return this.afterDate(baseDate, new Timestamp(System.currentTimeMillis()));
//	}
//	
//	public boolean afterDate(Timestamp baseDate, Timestamp dateToCompare) {
//		
//		if(dateToCompare == null) {
//			// lancia exception
//			return false;
//		}
//		
//		else if(baseDate == null || baseDate.after(dateToCompare))
//			return true;
//			
//			return false;
//	}
	
	public static LocalDateTime newLocalDateTime() {
		return LocalDateTime.now();
	}
	
	public static boolean afterDate(LocalDateTime baseDate) {
		return GeneralUtilities.afterDate(baseDate, LocalDateTime.now());
	}
	
	public static boolean afterDate(LocalDateTime baseDate, LocalDateTime dateToCompare) {
		
		if(dateToCompare == null || baseDate == null)
			return false;

		return baseDate.isAfter(dateToCompare);
	}
	
	public boolean beforeDate(Timestamp baseDate, String dateToCompare) {
		return this.beforeDate(baseDate, Timestamp.valueOf(dateToCompare));
	}
	
	public boolean beforeDate(Timestamp baseDate) {
		
		return this.beforeDate(baseDate, new Timestamp(System.currentTimeMillis()));
	}
	
	public boolean beforeDate(Timestamp baseDate, Timestamp dateToCompare) {
		
		if(dateToCompare == null) {
			// lancia exception
			return false;
		}
		
		else if(baseDate == null || baseDate.before(dateToCompare))
			return true;
			
			return false;
	}
	
	
	
	public String generateRandomString(int len) {
		
		int length = len;
	    boolean useLetters = true;
	    boolean useNumbers = true;
	    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);	    
	    
	    return generatedString;
	}
	
}

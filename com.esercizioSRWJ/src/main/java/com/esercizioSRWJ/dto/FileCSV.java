package com.esercizioSRWJ.dto;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Value;

public class FileCSV {
	
	private String intestazione;
	@Value("${my.csv.dateFormat}")
	private String dateFormat;

	public FileCSV() {
		super();
		this.intestazione = "01"+String.valueOf(new SimpleDateFormat(dateFormat));
	}

	public String getIntestazione() {
		return intestazione;
	}

	public String getDateFormat() {
		return dateFormat;
	}
	
	
	
	

}

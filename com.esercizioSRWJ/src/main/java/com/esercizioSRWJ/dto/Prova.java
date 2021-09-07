package com.esercizioSRWJ.dto;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

import com.esercizioSRWJ.customAnnotaions.FieldPropertiesForCsv;
import com.esercizioSRWJ.model.RichiestaConsegna;
import com.opencsv.bean.CsvBindByPosition;

public class Prova {
	
	@CsvBindByPosition(position = 0)
	@FieldPropertiesForCsv(totalFieldLenght = 20)
	private String codiceCollo;
	@CsvBindByPosition(position = 2)
	@FieldPropertiesForCsv(totalFieldLenght = 20)
	private Double peso;
	@CsvBindByPosition(position = 3)
	@FieldPropertiesForCsv(totalFieldLenght = 20, bufferRequired = false)
	private Double prezzoConsegnna;
	@CsvBindByPosition(position = 1)
	private String separatore;

	public Prova() {
		super();
	}
	
	public Prova(String codiceCollo, Double peso, Double prezzoConsegnna, String separatore) {
		super();
		this.codiceCollo = codiceCollo;
		this.peso = peso;
		this.prezzoConsegnna = prezzoConsegnna;
		this.separatore = separatore;
	}
	
	public Prova(RichiestaConsegna r) {
		super();
		this.codiceCollo = r.getCodiceCollo();
		this.peso = r.getPeso();
		this.prezzoConsegnna = r.getPrezzoConsegna();
		this.separatore = "-";
	}
	
	public Prova(RichiestaConsegna r, String separatore) {
		super();
		this.codiceCollo = r.getCodiceCollo();
		this.peso = r.getPeso();
		this.prezzoConsegnna = r.getPrezzoConsegna();
		this.separatore = separatore;
	}

	public String getCodiceCollo() {
		return codiceCollo;
	}

	public void setCodiceCollo(String codiceCollo) {
		this.codiceCollo = codiceCollo;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getPrezzoConsegnna() {
		return prezzoConsegnna;
	}

	public void setPrezzoConsegnna(Double prezzoConsegnna) {
		this.prezzoConsegnna = prezzoConsegnna;
	}

	public String getSeparatore() {
		return separatore;
	}

	public void setSeparatore(String separatore) {
		this.separatore = separatore;
	}
	
	
	
	
//	@CsvBindByPosition(position = 0)
//	String stringa1;
//	
//	@CsvBindByPosition(position = 1)
//	String stringa2;
//
//	public Prova() {
//		super();
//	}
//
//	public Prova(RichiestaConsegna r) {
//		super();
//		this.stringa1 = r.getCodiceCollo();
//		this.stringa2 = String.valueOf(r.getPeso())+" "+String.valueOf(r.getPrezzoConsegna());
//	}
//
//	public String getStringa1() {
//		return stringa1;
//	}
//
//	public void setStringa1(String stringa1) {
//		this.stringa1 = stringa1;
//	}
//
//	public String getStringa2() {
//		return stringa2;
//	}
//
//	public void setStringa2(String stringa2) {
//		this.stringa2 = stringa2;
//	}
	
	
	
//	private void preparaPerCSV(List<RichiestaConsegna> lista) {
//		for(RichiestaConsegna r : lista) {
//			this.stringa1.add(r.getCodiceCollo());
//			this.stringa2.add(r.getPeso()+" "+r.getPrezzoConsegna());
//		}		
//	}
//
//	public List<String> getStringa1() {
//		return stringa1;
//	}
//
//	public void setStringa1(List<String> stringa1) {
//		this.stringa1 = stringa1;
//	}
//
//	public List<String> getStringa2() {
//		return stringa2;
//	}
//
//	public void setStringa2(List<String> stringa2) {
//		this.stringa2 = stringa2;
//	}
	

}

package com.esercizioSRWJ.dto;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.esercizioSRWJ.customAnnotaions.FieldPropertiesForCsv;
import com.esercizioSRWJ.model.RichiestaConsegna;
import com.opencsv.bean.CsvBindByPosition;

public class Prova2 {
	
	@CsvBindByPosition(position = 0)
	@FieldPropertiesForCsv(totalFieldLenght = 20, buffer = " ", bufferRequired = true)
	private String codiceCollo;
	
	@CsvBindByPosition(position = 1)
	@FieldPropertiesForCsv(totalFieldLenght = 20, buffer = " ", bufferRequired = true)
	private String peso;
	
	@CsvBindByPosition(position = 2)
	@FieldPropertiesForCsv(totalFieldLenght = 20, buffer = " ", bufferRequired = false)
	private String prezzoConsegna;
	public Prova2(RichiestaConsegna p) {
		this.codiceCollo = p.getCodiceCollo();
		this.peso = String.valueOf(p.getPeso());
		this.prezzoConsegna = String.valueOf(p.getPrezzoConsegna());
	}
	public String getCodiceCollo() {
		return codiceCollo;
	}
	public void setCodiceCollo(String codiceCollo) {
		this.codiceCollo = codiceCollo;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	public String getPrezzoConsegna() {
		return prezzoConsegna;
	}
	public void setPrezzoConsegna(String prezzoConsegna) {
		this.prezzoConsegna = prezzoConsegna;
	}
	@Override
	public String toString() {
		return "Prova2 [codiceCollo=" + codiceCollo + ", peso=" + peso + ", prezzoConsegna=" + prezzoConsegna + "]";
	}
	
	

}

package com.esercizioSRWJ.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.esercizioSRWJ.csv.LocalDateConverter;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;

@Entity
@NamedQuery(name = "RichiestaConsegna.findByCodiceCollo", 
			query = "SELECT r FROM RichiestaConsegna r WHERE r.codiceCollo = :codice ")
@NamedQuery(name = "RichiestaConsegna.findAllWithoutDate",
			query = " select rc.codiceCollo, rc.peso, rc.prezzoConsegna  from RichiestaConsegna rc ")
@Table(name="richiesta_consegna")
public class RichiestaConsegna implements Serializable{
	
	//immagino che codiceCollo sia la PK  e che quindi debba essere costruita o letta in backend
	@Id
	@Column(name="codice_collo")
//	@CsvBindByPosition(position = 0)
	private String codiceCollo;
	
	@Column(name="peso")
//	@CsvBindByPosition(position = 1)
	private Double peso;
	
	@Column(name="prezzo_consegna")
//	@CsvBindByPosition(position = 2)
	private Double prezzoConsegna;
	
	@Column(name="dt_creation")
//	@CsvCustomBindByPosition(position = 3, converter = LocalDateConverter.class)
	private LocalDateTime dtCreation;
	
	public RichiestaConsegna() {
		super();
	}
	
	public RichiestaConsegna(String id) {
		this.codiceCollo = id;
		this.peso = 10D;
		this.prezzoConsegna = 10.5D;
		this.dtCreation = LocalDateTime.now();
	}
	
	public RichiestaConsegna(String codiceCollo, Double peso, Double prezzoConsegna) {
		super();
		this.codiceCollo = codiceCollo;
		this.peso = peso;
		this.prezzoConsegna = prezzoConsegna;
	}

	public RichiestaConsegna(String codiceCollo, Double peso, Double prezzoConsegna, LocalDateTime dtCreation) {
		super();
		this.codiceCollo = codiceCollo;
		this.peso = peso;
		this.prezzoConsegna = prezzoConsegna;
		this.dtCreation = dtCreation;
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

	public Double getPrezzoConsegna() {
		return prezzoConsegna;
	}

	public void setPrezzoConsegna(Double prezzoConsegna) {
		this.prezzoConsegna = prezzoConsegna;
	}

	public LocalDateTime getDtCreation() {
		return dtCreation;
	}

	public void setDtCreation(LocalDateTime dtCreation) {
		this.dtCreation = dtCreation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codiceCollo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RichiestaConsegna other = (RichiestaConsegna) obj;
		return Objects.equals(codiceCollo, other.codiceCollo);
	}

	@Override
	public String toString() {
		return "RichiestaConsegna [codiceCollo=" + codiceCollo + ", peso=" + peso + ", prezzoConsegna=" + prezzoConsegna
				+ ", dtCreation=" + dtCreation + "]";
	}

	
	
	

}

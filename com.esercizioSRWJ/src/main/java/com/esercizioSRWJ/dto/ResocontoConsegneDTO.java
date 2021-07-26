package com.esercizioSRWJ.dto;

import java.util.List;
import java.util.Objects;

import com.esercizioSRWJ.model.RichiestaConsegna;


public class ResocontoConsegneDTO {
	private Double prezzoTotale=0D;
	private Double pesoTotale=0D;
	private List<RichiestaConsegna> listaConsegne;
	public ResocontoConsegneDTO() {
		super();
	}
	public ResocontoConsegneDTO(Double prezzoTotale, Double pesoTotale, List<RichiestaConsegna> listaConsegne) {
		super();
		this.prezzoTotale = prezzoTotale;
		this.pesoTotale = pesoTotale;
		this.listaConsegne = listaConsegne;
	}
	public ResocontoConsegneDTO(List<RichiestaConsegna> list) {
		this.listaConsegne = list;
		
		for(RichiestaConsegna riCon : list) {
			this.pesoTotale = Double.sum(this.pesoTotale, riCon.getPeso());
			this.prezzoTotale = Double.sum(this.prezzoTotale, riCon.getPrezzoConsegna());
	
		}
		
	}
	
	public Double getPrezzoTotale() {
		return prezzoTotale;
	}
	public void setPrezzoTotale(Double prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}
	public Double getPesoTotale() {
		return pesoTotale;
	}
	public void setPesoTotale(Double pesoTotale) {
		this.pesoTotale = pesoTotale;
	}
	public List<RichiestaConsegna> getListaConsegne() {
		return listaConsegne;
	}
	public void setListaConsegne(List<RichiestaConsegna> listaConsegne) {
		this.listaConsegne = listaConsegne;
	}
	
	@Override
	public String toString() {
		return "ResocontoConsegneDTO [prezzoTotale=" + prezzoTotale + ", pesoTotale=" + pesoTotale + ", listaConsegne="
				+ listaConsegne + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(listaConsegne, pesoTotale, prezzoTotale);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResocontoConsegneDTO other = (ResocontoConsegneDTO) obj;
		return Objects.equals(listaConsegne, other.listaConsegne) && Objects.equals(pesoTotale, other.pesoTotale)
				&& Objects.equals(prezzoTotale, other.prezzoTotale);
	}
	

}
package com.esercizioSRWJ.repository;

import org.springframework.data.repository.CrudRepository;

import com.esercizioSRWJ.model.RichiestaConsegna;

public interface RichiestaConsegnaRepository extends CrudRepository<RichiestaConsegna, String>{
	
	public RichiestaConsegna findByCodiceCollo(String codiceCollo);

}

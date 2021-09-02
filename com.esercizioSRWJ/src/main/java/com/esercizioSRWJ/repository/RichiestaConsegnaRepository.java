package com.esercizioSRWJ.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.esercizioSRWJ.model.RichiestaConsegna;

public interface RichiestaConsegnaRepository extends CrudRepository<RichiestaConsegna, String>{
	
	public RichiestaConsegna findByCodiceCollo(String codiceCollo);

//    @Query(" select rc.codiceCollo, rc.peso, rc.prezzoConsegna "
//            + " from RichiestaConsegna rc ")
//    List<RichiestaConsegna> findAllWithoutDate();

}

package com.esercizioSRWJ.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esercizioSRWJ.model.RichiestaConsegna;
import com.esercizioSRWJ.repository.RichiestaConsegnaRepository;


@Service
public class RichiestaConsegnaService {
	
	@Autowired
	private RichiestaConsegnaRepository richiestaConsegnaRepository;
	
	public List<RichiestaConsegna> findAll() {
		return (List<RichiestaConsegna>) this.richiestaConsegnaRepository.findAll();
	}
	
	public RichiestaConsegna findById(String id) {
		return this.richiestaConsegnaRepository.findByCodiceCollo(id);
	}
	
	public void save(RichiestaConsegna model) {
		if(model.getDtCreation() == null) {
			LocalDateTime now = LocalDateTime.now();
			model.setDtCreation(now);
		}
		this.richiestaConsegnaRepository.save(model);
	}
	
	public void update(RichiestaConsegna model) {
		if(model.getDtCreation() == null) {
			LocalDateTime now = LocalDateTime.now();
			model.setDtCreation(now);
		}
		this.richiestaConsegnaRepository.save(model);
	}
	
	public void delete(String id) {
		this.richiestaConsegnaRepository.deleteById(id);
	}
	
}

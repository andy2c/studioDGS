package com.esercizioSRWJ.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esercizioSRWJ.exception.AfterDateError;
import com.esercizioSRWJ.exception.MaxLengthError;
import com.esercizioSRWJ.exception.RequiredFieldError;
import com.esercizioSRWJ.exception.UniqueFieldError;
import com.esercizioSRWJ.model.RichiestaConsegna;
import com.esercizioSRWJ.repository.RichiestaConsegnaRepository;
import com.esercizioSRWJ.validate.RichiestaConsegnaValidate;

@Service
public class DbService {
	
	@Autowired
	private RichiestaConsegnaRepository richiestaConsegnaRepository;
	
	@Autowired
	private RichiestaConsegnaValidate validate;
	
	public List<RichiestaConsegna> findAll() {
		return (List<RichiestaConsegna>) this.richiestaConsegnaRepository.findAll();
	}
	
	public RichiestaConsegna findById(String id) 
			throws RequiredFieldError, MaxLengthError, UniqueFieldError {
		this.validate.validateAttributePK(id, false);
		return this.richiestaConsegnaRepository.findByCodiceCollo(id);
	}
	
	public void save(RichiestaConsegna model) 
			throws RequiredFieldError, MaxLengthError, UniqueFieldError, AfterDateError {
		if(model.getDtCreation() == null) 
			model.setDtCreation(LocalDateTime.now());
		this.validate.validate(model, true);
		this.richiestaConsegnaRepository.save(model);
	}
	
	public void update(RichiestaConsegna model) 
			throws RequiredFieldError, MaxLengthError, UniqueFieldError, AfterDateError {
		if(model.getDtCreation() == null) 
			model.setDtCreation(LocalDateTime.now());
		this.validate.validate(model);
		this.richiestaConsegnaRepository.save(model);
	}
	
	public void delete(String id) 
			throws RequiredFieldError, MaxLengthError, UniqueFieldError {
		this.validate.validateAttributePK(id, false);
		this.richiestaConsegnaRepository.deleteById(id);
	}

}

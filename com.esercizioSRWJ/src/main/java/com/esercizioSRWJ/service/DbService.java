package com.esercizioSRWJ.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
	
	@Autowired
	EntityManager em;
	
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
	
	//ESEMPI QUERY BUILDER
	
		//esempio CriteriaBuilder.In
	
	public List<RichiestaConsegna> findByPeso(String peso) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<RichiestaConsegna> cq = cb.createQuery(RichiestaConsegna.class);
		Root<RichiestaConsegna> root = cq.from(RichiestaConsegna.class);
		
		List conditions = Arrays.asList(new String [] {peso});
		
		Expression<String> exp = root.get("peso");
		Predicate in = exp.in(conditions);
		
		cq.where(in);
		
		CriteriaQuery<RichiestaConsegna> select = cq.select(root);
		TypedQuery<RichiestaConsegna> query = em.createQuery(select);
		
		return query.getResultList();
		
		
		/*
		 * esempio con join
		 * 
		    final Root<Tabella> tabellaRoot = criteriaQuery.from(Tabella.class);
			Join<Tabella, TabellaPerJoin> join1 = tabellaRoot.join("joinColumnName", JoinType.LEFT);
			
			Predicate predicate = criteriaBuilder.equal(TabellaPerJoin.<String> get("recipient"), recepientValue;
			criteria.add(predicate);
			criteriaQuery.where(predicate);
			criteriaQuery.distinct(true);
		 */
    }
	
	
		//esempio CriteriaBuilder.In con più condizioni where
	
	public List<RichiestaConsegna> findByPesoAndPrezzoConsegna(String peso, String prezzoCon){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<RichiestaConsegna> cq = cb.createQuery(RichiestaConsegna.class);
		
		Root<RichiestaConsegna> root = cq.from(RichiestaConsegna.class);
		
		cq.where(cb.and(
				cb.equal(root.get("peso"), peso),
				cb.equal(root.get("prezzoConsegna"), prezzoCon)
				));
		
		CriteriaQuery<RichiestaConsegna> select = cq.select(root);
		TypedQuery<RichiestaConsegna> query = em.createQuery(select);
		
		return query.getResultList();
		
	}
	
		//esempio Expression.In
	
	public List<RichiestaConsegna> findByPrezzoConsegna(String prezzoCon){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<RichiestaConsegna> cq = cb.createQuery(RichiestaConsegna.class);
		Root<RichiestaConsegna> root = cq.from(RichiestaConsegna.class);
		
		return em.createQuery(
					cq.select(root).where(
						root.get("prezzoConsegna").in(prezzoCon)
						)).getResultList();
		
	}
	
		//esempio named query - l'effettiva named query è in model.RichiestaConsegna
	
	public List<RichiestaConsegna> findByCodiceCollo(String codiceCollo) {
		TypedQuery<RichiestaConsegna> q = em.createNamedQuery("RichiestaConsegna.findByCodiceCollo", 
				RichiestaConsegna.class);
		q.setParameter("codice", codiceCollo);
		return q.getResultList();
	}
	
	public List<RichiestaConsegna> findAllWithoutDate(){
		TypedQuery<RichiestaConsegna> q = em.createNamedQuery("RichiestaConsegna.findAllWithoutDate", 
				RichiestaConsegna.class);
		return q.getResultList();
		
	}
	
		//esempio native query
	
	public List<RichiestaConsegna> findByPesoAndPrezzoOrderAScelta(String peso,
																	String prezzo,
																	String orderBy){
		String query = " SELECT * FROM richiesta_consegna r "
						+ " WHERE peso >= "+peso+" AND prezzo_consegna >= "+prezzo 
						+ " ORDER BY "+orderBy;
		return em.createNativeQuery(query, RichiestaConsegna.class).getResultList();
	}

}

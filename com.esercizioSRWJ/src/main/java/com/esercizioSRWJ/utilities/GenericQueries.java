package com.esercizioSRWJ.utilities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

public class GenericQueries <T> {
	
	
	private EntityManager em;
	
    private final Class<T> type;

    public GenericQueries(Class<T> type, EntityManager em) {
         this.type = type;
         this.em = em;
    }

    public Class<T> getMyType() {
        return this.type;
    }
	
	public List<T> findByPesoAndPrezzoConsegna(String peso, String prezzoCon){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(type);
		
		Root<T> root = cq.from(type);
		
		cq.where(cb.and(
				cb.equal(root.get("peso"), peso),
				cb.equal(root.get("prezzoConsegna"), prezzoCon)
				));
		
		CriteriaQuery<T> select = cq.select(root);
		TypedQuery<T> query = em.createQuery(select);
		
		return query.getResultList();
	}

}

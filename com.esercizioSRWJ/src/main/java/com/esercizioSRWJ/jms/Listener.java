package com.esercizioSRWJ.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.esercizioSRWJ.exception.AfterDateError;
import com.esercizioSRWJ.exception.MaxLengthError;
import com.esercizioSRWJ.exception.RequiredFieldError;
import com.esercizioSRWJ.exception.UniqueFieldError;
import com.esercizioSRWJ.model.RichiestaConsegna;
import com.esercizioSRWJ.service.DbService;


@Component
public class Listener {
	
	DbService dbService;
//	RichiestaConsegnaValidate riConVal;
	
	@Autowired
	public Listener(DbService dbService
//				, RichiestaConsegnaValidate riConVal
			){
		super();
		this.dbService = dbService;
//		this.riConVal = riConVal;
	}
	
	

    @JmsListener(destination = "standalone.queue")
    public void consume(RichiestaConsegna riCon) {
    	
//    	try {
//			this.riConVal.validate(riCon, true);
    	try {
			this.dbService.save(riCon);
		} catch (RequiredFieldError e) {
			System.out.println(e.getDescription(e));
		} catch (MaxLengthError e) {
			System.out.println(e.getDescription(e));
		} catch (UniqueFieldError e) {
			System.out.println(e.getDescription(e));
		} catch (AfterDateError e) {
			System.out.println(e.getDescription(e));
		}
        
    }
}
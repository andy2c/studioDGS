package com.esercizioSRWJ.component;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import com.esercizioSRWJ.exception.AfterDateError;
import com.esercizioSRWJ.exception.MaxLengthError;
import com.esercizioSRWJ.exception.RequiredFieldError;
import com.esercizioSRWJ.exception.UniqueFieldError;
import com.esercizioSRWJ.model.RichiestaConsegna;
import com.esercizioSRWJ.service.RichiestaConsegnaService;
import com.esercizioSRWJ.validate.RichiestaConsegnaValidate;



@Component
public class Listener {
	
	RichiestaConsegnaService riConSer;
	RichiestaConsegnaValidate riConVal;
	
	@Autowired
	public Listener(RichiestaConsegnaService riConSer,
					RichiestaConsegnaValidate riConVal) {
		super();
		this.riConSer = riConSer;
		this.riConVal = riConVal;
	}
	
	

    @JmsListener(destination = "standalone.queue")
    public void consume(RichiestaConsegna riCon) {
    	
    	try {
			this.riConVal.validate(riCon, true);
	    	this.riConSer.save(riCon);
	        System.out.println("salvato: " + riCon);
		} catch (RequiredFieldError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MaxLengthError e) {
			System.out.println(e.getDescription(e));
		} catch (UniqueFieldError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AfterDateError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
package com.esercizioSRWJ.controller;


import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.esercizioSRWJ.config.Settings;
import com.esercizioSRWJ.dto.ResocontoConsegneDTO;
import com.esercizioSRWJ.exception.AfterDateError;
import com.esercizioSRWJ.exception.MaxLengthError;
import com.esercizioSRWJ.exception.RequiredFieldError;
import com.esercizioSRWJ.exception.UniqueFieldError;
import com.esercizioSRWJ.model.RichiestaConsegna;
import com.esercizioSRWJ.service.RichiestaConsegnaService;
import com.esercizioSRWJ.validate.RichiestaConsegnaValidate;


@RestController
@RequestMapping(value = "/api")
public class RichiestaConsegnaController {
	
	//SETTAGGIO ELEMETI CLASSSE
	

	private JmsTemplate jmsTemplate;
	private Queue queue;
	private RichiestaConsegnaService richiestaConsegnaService;
	private RichiestaConsegnaValidate richiestaConsegnaValidate;
	
	@Value("${my.greeting: hello}")
	private String greetingMessage;
	
	@Value("${my.list.value: Francesco}")
	private List<String> listGreetings;
	
	@Value("#{${my.esMapValues}}")
	private Map<String, String> esMap;
	
	@Autowired
	private Settings settings;
	
	@Autowired
	public RichiestaConsegnaController(JmsTemplate jmsTemplate, 
										Queue queue,
										RichiestaConsegnaService richiestaConsegnaService, 
										RichiestaConsegnaValidate richiestaConsegnaValidate) {
		super();
		this.jmsTemplate = jmsTemplate;
		this.queue = queue;
		this.richiestaConsegnaService = richiestaConsegnaService;
		this.richiestaConsegnaValidate = richiestaConsegnaValidate;
	}
	
	
	// MAPPATURA REST

	@RequestMapping("/hello")
	public String hello() {
		return this.greetingMessage+" "+this.listGreetings+this.esMap;
	}
	
	@RequestMapping("/settings")
	public String setting() {
		return this.settings.getConnection();
	}
	
	@RequestMapping("/goHome")
	public ModelAndView goHome() {
		ModelAndView ret= new ModelAndView("home");
		return ret;
	}
	
	@RequestMapping(method=RequestMethod.PUT, value ="/resoconto")
	public String mettiInCoda(@RequestBody RichiestaConsegna riCon) {
		jmsTemplate.convertAndSend(queue, riCon);
		return "messaggio aggiunto in coda";
	}
	
	@RequestMapping(method=RequestMethod.GET, value ="/resoconto/{id}")
	public String mettiInCodaMocked(@PathVariable String id) {
		RichiestaConsegna riCon = new RichiestaConsegna(id, 10D, 10D);
		
		jmsTemplate.convertAndSend(queue, riCon);
		return "messaggio aggiunto in coda";
	}
	
//	@RequestMapping("/consegne/resoconto")
//	public ModelAndView findConsegne() {
//		ModelAndView ret = new ModelAndView("consegne");
//		ResocontoConsegneDTO resocontoDTO = new ResocontoConsegneDTO(this.richiestaConsegnaService.findAll());
//		ret.addObject("resocontoDTO",resocontoDTO);
//		return ret;
//	}
	
	@RequestMapping("/consegne/resoconto")
	public String findAllConsegne() {
		ResocontoConsegneDTO resocontoDTO = new ResocontoConsegneDTO(this.richiestaConsegnaService.findAll());
		return "resoconto consegne : "+resocontoDTO;
	}
	
	@RequestMapping(method=RequestMethod.POST, value ="/saveConsegna")
	public String saveConsegna(@RequestBody RichiestaConsegna riCon) 
			throws RequiredFieldError, MaxLengthError, UniqueFieldError, AfterDateError {
//		try {
			this.richiestaConsegnaValidate.validate(riCon, true);
			this.richiestaConsegnaService.save(riCon);
			return "inserimento effettuato con successo";
//		}
//		catch (Exception e) {
//        return "errore, contattare l'amministratore";
//    }
	}
	
	@RequestMapping(method=RequestMethod.POST, value ="/updateConsegna")
	public String updateConsegna(@RequestBody RichiestaConsegna riCon) 
			throws RequiredFieldError, MaxLengthError, UniqueFieldError, AfterDateError {
//		try {
			this.richiestaConsegnaValidate.validate(riCon);
			this.richiestaConsegnaService.update(riCon);
			return "aggiornamento effettuato con successo";
//		} 
//		catch (Exception e) {
//        return "errore, contattare l'amministratore";
//    }
	}
	
	@RequestMapping(method=RequestMethod.GET, value ="/saveConsegna/{id}")
	public String saveConsegnaMocked(@PathVariable String id) 
			throws UniqueFieldError, RequiredFieldError, MaxLengthError, AfterDateError {
		RichiestaConsegna riCon = new RichiestaConsegna(id, 10D, 10D);
//		try {
			this.richiestaConsegnaValidate.validate(riCon, true);
			this.richiestaConsegnaService.save(riCon);
			return "inserimento effettuato con successo";
//		} 
//		catch (Exception e) {
//	        return "errore, contattare l'amministratore";
//	    }
	}
	
	@RequestMapping(method=RequestMethod.GET, value ="/updateConsegna/{id}")
	public String updateConsegnaMocked(@PathVariable String id) 
			throws RequiredFieldError, MaxLengthError, UniqueFieldError, AfterDateError {
		RichiestaConsegna riCon = new RichiestaConsegna(id, 15D, 15D);
//		try {
			this.richiestaConsegnaValidate.validate(riCon);
			this.richiestaConsegnaService.update(riCon);
			return "aggiornamento effettuato con successo";
//		} 
//		catch (Exception e) {
//	        return "errore, contattare l'amministratore";
//	    }
	}
	
	@RequestMapping(method=RequestMethod.GET, value ="/get/{id}")
	public String getConsegnaMocked(@PathVariable String id) {
		if(this.richiestaConsegnaService.findById(id) != null)
			return "consegna con id "+id+" : "+this.richiestaConsegnaService.findById(id);
		else return "consegna non trovata"; //cambia con response entity
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value ="/delete/{id}")
	public String deleteTopic(@PathVariable String id) 
			throws RequiredFieldError, MaxLengthError, UniqueFieldError {
		try {
			this.richiestaConsegnaValidate.validateDelete(id);
			this.richiestaConsegnaService.delete(id);
			return "eliminato con successo";
		} 
//		catch (Exception e) {
//        return "errore, contattare l'amministratore";
//    }
		finally {
			
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value ="/delete/{id}")
	public String deleteTopicMocked(@PathVariable String id) 
			throws RequiredFieldError, MaxLengthError, UniqueFieldError {
		try {
			this.richiestaConsegnaValidate.validateDelete(id);
			this.richiestaConsegnaService.delete(id);
			return "eliminato con successo";
		} 
//		catch (Exception e) {
//        return "errore, contattare l'amministratore";
//    }
		finally {
			
		}
	}
	
}

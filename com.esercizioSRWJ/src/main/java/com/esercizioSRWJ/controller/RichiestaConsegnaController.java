package com.esercizioSRWJ.controller;


import javax.jms.JMSException;
import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
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
		return "hello";
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

//		AbstractApplicationContext context = new AnnotationConfigApplicationContext(JMSConfiguration.class);
//		MessageSender messageSender = context.getBean(MessageSender.class);
//		messageSender.sendMessage(riCon);
//		((AbstractApplicationContext) context).close();
		
		jmsTemplate.convertAndSend(queue, riCon);
		return "messaggio aggiunto in coda";
	}
	
//	@RequestMapping(method=RequestMethod.GET, value ="/getCoda")
//	public String prendiCodaMocked() {
//		
//		AbstractApplicationContext context = new AnnotationConfigApplicationContext(JMSConfiguration.class);
//		MessageReceiver messageReceiver = (MessageReceiver) context.getBean("messageReceiver");
//		RichiestaConsegna riCon= new RichiestaConsegna();
//		try {
//			riCon = messageReceiver.receiveMessage();
//		} catch (MessageConversionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JMSException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		((AbstractApplicationContext) context).close();
//		try {
//			this.richiestaConsegnaValidate.validate(riCon, true);
//			this.richiestaConsegnaService.save(riCon);
//			return "salvato \""+riCon+"\" nel database";
//		}  catch (RequiredFieldError e) {
//			return e.getDescription(e);
//		} catch (MaxLengthError e) {
//			return e.getDescription(e);
//		} catch (UniqueFieldError e) {
//			return e.getDescription(e);
//		} catch (AfterDateError e) {
//			return e.getDescription(e);
//		} 
//	}
	
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
	public String saveConsegna(@RequestBody RichiestaConsegna riCon) {
		try {
			this.richiestaConsegnaValidate.validate(riCon, true);
			this.richiestaConsegnaService.save(riCon);
			return "inserimento effettuato con successo";
		} catch (RequiredFieldError e) {
			return e.getDescription(e);
		} catch (MaxLengthError e) {
			return e.getDescription(e);
		} catch (UniqueFieldError e) {
			return e.getDescription(e);
		} catch (AfterDateError e) {
			return e.getDescription(e);
		} 
//		catch (Exception e) {
//	        return "errore, contattare l'amministratore";
//	    }
	}
	
	@RequestMapping(method=RequestMethod.POST, value ="/updateConsegna")
	public String updateConsegna(@RequestBody RichiestaConsegna riCon) {
		try {
			this.richiestaConsegnaValidate.validate(riCon);
			this.richiestaConsegnaService.update(riCon);
			return "aggiornamento effettuato con successo";
		} catch (RequiredFieldError e) {
			return e.getDescription(e);
		} catch (MaxLengthError e) {
			return e.getDescription(e);
		} catch (UniqueFieldError e) {
			return e.getDescription(e);
		} catch (AfterDateError e) {
			return e.getDescription(e);
		} 
//		catch (Exception e) {
//	        return "errore, contattare l'amministratore";
//	    }
	}
	
	@RequestMapping(method=RequestMethod.GET, value ="/saveConsegna/{id}")
	public String saveConsegnaMocked(@PathVariable String id) {
		RichiestaConsegna riCon = new RichiestaConsegna(id, 10D, 10D);
		try {
			this.richiestaConsegnaValidate.validate(riCon, true);
			this.richiestaConsegnaService.save(riCon);
			return "inserimento effettuato con successo";
		} catch (RequiredFieldError e) {
			return e.getDescription(e);
		} catch (MaxLengthError e) {
			return e.getDescription(e);
		} catch (UniqueFieldError e) {
			return e.getDescription(e);
		} catch (AfterDateError e) {
			return e.getDescription(e);
		} 
//		catch (Exception e) {
//	        return "errore, contattare l'amministratore";
//	    }
	}
	
	@RequestMapping(method=RequestMethod.GET, value ="/updateConsegna/{id}")
	public String updateConsegnaMocked(@PathVariable String id) {
		RichiestaConsegna riCon = new RichiestaConsegna(id, 10D, 10D);
		try {
			this.richiestaConsegnaValidate.validate(riCon);
			this.richiestaConsegnaService.update(riCon);
			return "aggiornamento effettuato con successo";
		} catch (RequiredFieldError e) {
			return e.getDescription(e);
		} catch (MaxLengthError e) {
			return e.getDescription(e);
		} catch (UniqueFieldError e) {
			return e.getDescription(e);
		} catch (AfterDateError e) {
			return e.getDescription(e);
		} 
//		catch (Exception e) {
//	        return "errore, contattare l'amministratore";
//	    }
	}
	
	@RequestMapping(method=RequestMethod.GET, value ="/get/{id}")
	public String getConsegnaMocked(@PathVariable String id) {
		if(this.richiestaConsegnaService.findById(id) != null)
			return "consegna con id "+id+" : "+this.richiestaConsegnaService.findById(id);
		else return "consegna non trovata";
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value ="/delete/{id}")
	public String deleteTopic(@PathVariable String id) {
		try {
			this.richiestaConsegnaValidate.validateDelete(id);
			this.richiestaConsegnaService.delete(id);
			return "eliminato con successo";
		} catch (RequiredFieldError e) {
			return e.getDescription(e);
		} catch (MaxLengthError e) {
			return e.getDescription(e);
		} catch (UniqueFieldError e) {
			return e.getDescription(e);
		}
//		catch (Exception e) {
//        return "errore, contattare l'amministratore";
//    }
	}
	
	@RequestMapping(method=RequestMethod.GET, value ="/delete/{id}")
	public String deleteTopicMocked(@PathVariable String id) {
		try {
			this.richiestaConsegnaValidate.validateDelete(id);
			this.richiestaConsegnaService.delete(id);
			return "eliminato con successo";
		} catch (RequiredFieldError e) {
			return e.getDescription(e);
		} catch (MaxLengthError e) {
			return e.getDescription(e);
		} catch (UniqueFieldError e) {
			return e.getDescription(e);
		}
//		catch (Exception e) {
//        return "errore, contattare l'amministratore";
//    }
	}
	
}

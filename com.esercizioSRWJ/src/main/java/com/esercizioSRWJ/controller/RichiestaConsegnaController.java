package com.esercizioSRWJ.controller;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import com.esercizioSRWJ.service.CSVService;
import com.esercizioSRWJ.service.DbService;
import com.esercizioSRWJ.service.JmsPublisher;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;


@RestController
@RequestMapping(value = "/api")
public class RichiestaConsegnaController {
	
	//SETTAGGIO ELEMETI CLASSSE
	
	private DbService dbService;
	private JmsPublisher jmsPublisher;
	private CSVService csvService;
	
	@Value("${my.greeting: hello}")
	private String greetingMessage;
	
	@Value("${my.list.value: Francesco}")
	private List<String> listGreetings;
	
	@Value("#{${my.esMapValues}}")
	private Map<String, String> esMap;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private Settings settings;
	
	@Autowired
	public RichiestaConsegnaController(DbService dbService, 
									JmsPublisher jmsPublisher,
									CSVService csvService) {
		super();
		this.dbService = dbService;
		this.jmsPublisher = jmsPublisher;
		this.csvService = csvService;
}
	
	
	
	
	// MAPPATURA REST
	
	
	//INIZIO RICHIESTE TRACCIA
	
	@RequestMapping("/goHome")
	public ModelAndView goHome() {
		ModelAndView ret= new ModelAndView("home");
		return ret;
	}
	
	@RequestMapping(method=RequestMethod.PUT, value ="/resoconto")
	public String mettiInCoda(@RequestBody RichiestaConsegna riCon) {
		this.jmsPublisher.mettiInCoda(riCon);
		return "messaggio aggiunto in coda";
	}
	
	@RequestMapping(method=RequestMethod.GET, value ="/resoconto/{id}")
	public String mettiInCodaMocked(@PathVariable String id) {
		RichiestaConsegna riCon = new RichiestaConsegna(id, 10D, 10D);

		this.jmsPublisher.mettiInCoda(riCon);
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
		ResocontoConsegneDTO resocontoDTO = new ResocontoConsegneDTO(this.dbService.findAll());
		return "resoconto consegne : "+resocontoDTO;
	}
	
	@RequestMapping("/consegne/resocontoReEn")
	public ResponseEntity<ResocontoConsegneDTO> findAllConsegneResponseEntity() {
		return ResponseEntity.ok().headers(new HttpHeaders()).body(new ResocontoConsegneDTO(this.dbService.findAll()));
	}
	
	@RequestMapping(method=RequestMethod.POST, value ="/saveConsegna")
	public String saveConsegna(@RequestBody RichiestaConsegna riCon) 
		throws RequiredFieldError, MaxLengthError, UniqueFieldError, AfterDateError {
			this.dbService.save(riCon);
			return "inserimento effettuato con successo";
	}
	
	@RequestMapping(method=RequestMethod.POST, value ="/updateConsegna")
	public String updateConsegna(@RequestBody RichiestaConsegna riCon) 
		throws RequiredFieldError, MaxLengthError, UniqueFieldError, AfterDateError {
			this.dbService.update(riCon);
			return "aggiornamento effettuato con successo";
	}
	
	@RequestMapping(method=RequestMethod.GET, value ="/saveConsegna/{id}")
	public String saveConsegnaMocked(@PathVariable String id) 
		throws UniqueFieldError, RequiredFieldError, MaxLengthError, AfterDateError {
			RichiestaConsegna riCon = new RichiestaConsegna(id, 10D, 10D);

			this.dbService.save(riCon);
			return "inserimento effettuato con successo";
	}
	
	@RequestMapping(method=RequestMethod.GET, value ="/updateConsegna/{id}")
	public String updateConsegnaMocked(@PathVariable String id) 
		throws RequiredFieldError, MaxLengthError, UniqueFieldError, AfterDateError {
			RichiestaConsegna riCon = new RichiestaConsegna(id, 15D, 15D);

			this.dbService.update(riCon);
			return "aggiornamento effettuato con successo";
	}
	
	@RequestMapping(method=RequestMethod.GET, value ="/get/{id}")
	public String getConsegnaMocked(@PathVariable String id) 
		throws RequiredFieldError, MaxLengthError, UniqueFieldError {
			if(this.dbService.findById(id) != null)
				return "consegna con id "+id+" : "+this.dbService.findById(id);
			else return "consegna non trovata"; //cambia con response entity
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value ="/delete/{id}")
	public String deleteTopic(@PathVariable String id) 
		throws RequiredFieldError, MaxLengthError, UniqueFieldError {
			this.dbService.delete(id);
			return "eliminato con successo";
	}
	
	@RequestMapping(method=RequestMethod.GET, value ="/delete/{id}")
	public String deleteTopicMocked(@PathVariable String id) 
		throws RequiredFieldError, MaxLengthError, UniqueFieldError {
			this.dbService.delete(id);
			return "eliminato con successo";
	}
	
	//SOLO PER ESERCIZIO PERSONALE START

	@RequestMapping("/hello")
	public String hello() {
		return this.greetingMessage+" "+this.listGreetings+this.esMap;
	}

	@RequestMapping("/settings")
	public String setting() {
		return this.settings.getConnection();
	}
	
	@RequestMapping("env")
	public String envDetails() {
		return env.toString();
	}
	
	//ESEMPI QUERY BUILDER
	
		//esempio CriteriaBuilder.In
	
	@RequestMapping(method=RequestMethod.GET, value ="/queryBuilder/{peso}")
	public String getConsegnaQB(@PathVariable String peso) {
			if(this.dbService.findByPeso(peso).size()>0)
				return "consegna con peso "+peso+" : "+this.dbService.findByPeso(peso);
			else return "consegna non trovata";
	}
	
		//esempio CriteriaBuilder.In con più condizioni where
	
	@RequestMapping(method=RequestMethod.GET, value="/queryBuilder/{peso}/{prezzo}")
	public String getConsegnaQBMultyWhere(@PathVariable String peso, @PathVariable String prezzo) {
		if(this.dbService.findByPesoAndPrezzoConsegna(peso, prezzo).size()>0)
			return "consegna con peso "+peso+" e prezzo consegna "+prezzo+" : "+this.dbService.findByPeso(peso);
		else return "consegna non trovata";
	}
	
		//esempio Expression.In
	
	@RequestMapping(method=RequestMethod.GET, value="/queryBuilderExp/{prezzo}")
	public String getConsegnaQBExpression(@PathVariable String prezzo) {
		if(this.dbService.findByPrezzoConsegna(prezzo).size()>0)
			return "consegna con prezzo consegna "+prezzo+" : "+this.dbService.findByPrezzoConsegna(prezzo);
		else return "consegna non trovata";
	}
	
		//esempio named query
	
	@RequestMapping(method=RequestMethod.GET, value="/queryBuilderMQ/{codice}")
	public String getByCodiceColloNamedQuery(@PathVariable String codice) {
		if(this.dbService.findByCodiceCollo(codice).size()>0)
			return "consegna con codice collo "+codice+" : "+this.dbService.findByCodiceCollo(codice);
		else return "consegna non trovata";
	}
	
		//esempio native query
	
	@RequestMapping(method=RequestMethod.GET, value="/queryBuilderNQ/{peso}/{prezzo}/{orderBy}")
	public String getByPesoAndPrezzoNativeQuery(@PathVariable String peso,
												@PathVariable String prezzo,
												@PathVariable String orderBy) {
		if(this.dbService.findByPesoAndPrezzoOrderAScelta(peso, prezzo, orderBy).size()>0)
			return "lista consegne con peso "+peso+" , prezzo consegna "+prezzo
					+" e ordinata per "+orderBy+" : "+this.dbService.findByPesoAndPrezzoOrderAScelta(peso, prezzo, orderBy);
		else return "consegna non trovata";
	}
	
	//ESEMPI CREAZIONE FILE CSV
	
		//esempio CsvBindByPosition
	
	@RequestMapping(method=RequestMethod.GET, value="/csvPosition")
	public String writeCSVBindByPosition() {
//		List<RichiestaConsegna> list = this.dbService.findAllWithoutDate();
		List<RichiestaConsegna> list = this.dbService.findAll();
		for(RichiestaConsegna rc : list)
			rc.setDtCreation(null);
		try {
			this.csvService.writeCSV("C:\\lavoro\\dgs\\materiale esempi\\csv","esercizio csv resoconto", list);
			list=null;
		} catch (CsvDataTypeMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvRequiredFieldEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return String.valueOf(this.csvService.readCSV("C:\\lavoro\\dgs\\materiale esempi\\csv", "esercizio csv resoconto"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "errore";
		}

	}
}

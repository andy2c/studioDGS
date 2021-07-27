package com.esercizioSRWJ.controller;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esercizioSRWJ.model.RichiestaConsegna;

@RestController
@RequestMapping("/rest/publish")
public class JmsController {
	
	@Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Queue queue;

    @GetMapping("/{id}")
    public String publish(@PathVariable("id") final String id) {
    	
    	RichiestaConsegna riCon = new RichiestaConsegna(id);
        jmsTemplate.convertAndSend(queue, riCon);

        return "Published Successfully";
    }
}
package com.esercizioSRWJ.service;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.esercizioSRWJ.model.RichiestaConsegna;

@Service
public class JmsPublisher {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private Queue queue;
	
	public void mettiInCoda(RichiestaConsegna riCon) {
		jmsTemplate.convertAndSend(queue, riCon);
	}
	
	
}

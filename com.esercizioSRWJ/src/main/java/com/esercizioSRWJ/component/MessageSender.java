package com.esercizioSRWJ.component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esercizioSRWJ.model.RichiestaConsegna;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

@Component
public class MessageSender {
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	public void sendMessage(final RichiestaConsegna richiestaConsegna) {
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException{
				ObjectMessage objectMessage = session.createObjectMessage(richiestaConsegna);
				return objectMessage;
			}
		});
	}
}

package com.esercizioSRWJ.component;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import com.esercizioSRWJ.model.RichiestaConsegna;

@Component
public class MessageReceiver {
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	MessageConverter messageConverter;
	
	public RichiestaConsegna receiveMessage() throws MessageConversionException, JMSException {
		Message message = jmsTemplate.receive();
		return (RichiestaConsegna) messageConverter.fromMessage(message);
	}
}

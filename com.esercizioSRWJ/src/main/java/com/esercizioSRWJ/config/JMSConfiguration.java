package com.esercizioSRWJ.config;


import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import com.esercizioSRWJ.component.MessageReceiver;


@Configuration
@ComponentScan("com.esercizioSRWJ.component")
public class JMSConfiguration {
	
	private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";
	private static final String MESSAGE_QUEUE = "message_queue";
	
	@Autowired
	MessageReceiver messageReceiver;
	
//	@Autowired
//	public JMSConfiguration(MessageReceiver messageReceiver) {
//		this.messageReceiver = messageReceiver;
//	}

	@Bean
	public ConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
//		connectionFactory.setTrustedPackages(Arrays.asList("com.esercizioSRWJ.model"));
		connectionFactory.setTrustAllPackages(true);
		return connectionFactory;
	}
	
	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setDefaultDestinationName(MESSAGE_QUEUE);
		return template;
	}
	
	@Bean
	MessageConverter converte() {
		return new SimpleMessageConverter();
	}
}

package com.esercizioSRWJ.config;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import com.esercizioSRWJ.validate.RichiestaConsegnaValidate;


@Configuration
public class JMSConfiguration {
	
	private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";
	private static final String MESSAGE_QUEUE = "message_queue";
	
	@Autowired
	MessageReceiver messageReceiver;
	
	@Bean
	public ConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
//		connectionFactory.setTrustedPackages(null);
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
	
//	@Value("tcp://localhost:61616")
//	private String brokerUrl;
//	
//	@Bean
//	public Queue queue() {
//		return new ActiveMQQueue ("stanalone.queue");
//	}
//	
//	@Bean
//	public ActiveMQConnectionFactory activeMQConnectionFactory() {
//		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
//		factory.setBrokerURL(brokerUrl);
//		return factory;
//	}
//	
//	@Bean
//	public JmsTemplate jmsTemplate() {
//		return new JmsTemplate(activeMQConnectionFactory());
//	}

}

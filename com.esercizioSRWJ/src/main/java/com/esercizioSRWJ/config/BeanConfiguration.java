package com.esercizioSRWJ.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.esercizioSRWJ.validate.RichiestaConsegnaValidate;

@Configuration
public class BeanConfiguration {
	
	@Bean
	public RichiestaConsegnaValidate autowiredFieldDependency() {
		RichiestaConsegnaValidate autowiredFieldDependency =
					new RichiestaConsegnaValidate();
		return autowiredFieldDependency;
	}

}

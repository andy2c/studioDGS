package com.esercizioSRWJ.validate;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.esercizioSRWJ.exception.AfterDateError;
import com.esercizioSRWJ.exception.MaxLengthError;
import com.esercizioSRWJ.exception.RequiredFieldError;
import com.esercizioSRWJ.exception.UniqueFieldError;
import com.esercizioSRWJ.model.RichiestaConsegna;
import com.esercizioSRWJ.service.RichiestaConsegnaService;
import com.esercizioSRWJ.utilities.GeneralUtilities;



public class RichiestaConsegnaValidate {
	
	//LUNGHEZZE CAMPI
	private Integer lunghezzaCodiceCollo=10;
	private Integer lunghezzaPeso=10;
	private Integer lunghezzaPrezzoConsegna=6;
	private Integer lunghezzaDtCreation=25;
	
	@Autowired
	private RichiestaConsegnaService richiestaConsegnaService;
	
	public RichiestaConsegnaValidate() {
		
	}

	public void validate(RichiestaConsegna model) 
			throws RequiredFieldError, MaxLengthError, UniqueFieldError, AfterDateError {
		this.validate(model, false);
	}
	
	public void validate(RichiestaConsegna model, Boolean checkDuplicate ) 
			throws RequiredFieldError, MaxLengthError, UniqueFieldError, AfterDateError {
		
//			if(GeneralUtilities.checkIfNull(model.getCodiceCollo()))
//				throw new RequiredFieldError("codice collo");
//			else {
//				if(GeneralUtilities.checkIfLonger(model.getCodiceCollo(), lunghezzaCodiceCollo))
//					throw new MaxLengthError("codice collo", lunghezzaCodiceCollo);
//				if(checkDuplicate) {
//					RichiestaConsegnaService rs = new RichiestaConsegnaService();
//					if(rs.find(model.getCodiceCollo()) != null)
//						throw new UniqueFieldError("codice collo");
//				}
//			}
		
		this.validateAttributePK(model.getCodiceCollo(), "codice collo", 
									checkDuplicate);
		this.validateAttribute(model.getPeso(), "peso",this.lunghezzaPeso);
		this.validateAttribute(model.getPrezzoConsegna(), "prezzo consegna", 
											this.lunghezzaPrezzoConsegna);
		this.validateAttribute(model.getDtCreation(), "dt creation", 
											this.lunghezzaDtCreation);
	}
	
	public void validateAttributePK(String attributeContent, Boolean checkDuplicate) 
					throws RequiredFieldError, MaxLengthError, UniqueFieldError {
		this.validateAttributePK(attributeContent, "codice collo", checkDuplicate);
	}
	
	public void validateAttributePK(String attributeContent, 
									String attributeName,
									Boolean checkDuplicate) 
				throws RequiredFieldError, MaxLengthError, UniqueFieldError {
		
		if(GeneralUtilities.checkIfNull(attributeContent))
			throw new RequiredFieldError(attributeName);
		else {
			if(GeneralUtilities.checkIfLonger(attributeContent, lunghezzaCodiceCollo))
				throw new MaxLengthError(attributeName, lunghezzaCodiceCollo);
			if(checkDuplicate) {
				if(richiestaConsegnaService.findById(attributeContent) != null)
					throw new UniqueFieldError(attributeName);
			}
		}
	}
	
	public void validateAttribute(Double attributeContent, 
									String attributeName,
									Integer attributeLength) 
			throws RequiredFieldError, MaxLengthError {
		
		if(GeneralUtilities.checkIfNull(attributeContent))
			throw new RequiredFieldError(attributeName);
		else 
			if(GeneralUtilities.checkIfLonger(attributeContent, attributeLength))
				throw new MaxLengthError(attributeName, attributeLength);			
	}
	
	public void validateAttribute(LocalDateTime attributeContent, 
									String attributeName,
									Integer attributeLength) 
			throws RequiredFieldError, MaxLengthError, AfterDateError {
		
		if(!GeneralUtilities.checkIfNull(attributeContent)) {
			if(GeneralUtilities.checkIfLonger(attributeContent, attributeLength))
				throw new MaxLengthError(attributeName, attributeLength);
			if(GeneralUtilities.afterDate(attributeContent))
				throw new AfterDateError(attributeName);
		}
	}
	
	public void validateDelete(String pk) 
			throws RequiredFieldError, MaxLengthError, UniqueFieldError {
		
		this.validateAttributePK(pk, "codice collo", null);
	}

}

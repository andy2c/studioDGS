package com.esercizioSRWJ.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.esercizioSRWJ.dto.Prova;
import com.esercizioSRWJ.model.RichiestaConsegna;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@Component
public class SmartCSVWriter {
	
	public void writeCSVFile(String path, String nomeFile, List<RichiestaConsegna> lista) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		
		List<Prova> toWrite = new ArrayList<Prova>();
		for(RichiestaConsegna l : lista)
			toWrite.add(new Prova(l));
		
		Writer writer = new FileWriter(path+"\\"+nomeFile+".csv");
		StatefulBeanToCsv<Prova> beanToCSV = new StatefulBeanToCsvBuilder<Prova>(writer)
				.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
				.build();
		beanToCSV.write(toWrite);
		writer.close();
	}

}

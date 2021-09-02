package com.esercizioSRWJ.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esercizioSRWJ.csv.SmartCSVReader;
import com.esercizioSRWJ.csv.SmartCSVWriter;
import com.esercizioSRWJ.model.RichiestaConsegna;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@Service
public class CSVService {
	
	@Autowired
	private SmartCSVWriter sw;
	@Autowired
	private SmartCSVReader sr;
	
	 public void writeCSV(String path, String nomeFile, List<RichiestaConsegna> listToWrite) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		 
		 sw.writeCSVFile(path, nomeFile, listToWrite);
		 
//		 File reportFile = new File(reportOutputDir + "/" + reportFileName);
//		 Writer writer = new PrintWriter(reportFile);
//		 StatefulBeanToCsv<CSVNamedColumnBean> beanToCsv = new 
//		                               StatefulBeanToCsvBuilder(writer).build();
//		 beanToCsv.write( );
//		 writer.close();
	}
	 
	 public List<RichiestaConsegna> readCSV(String path, String nomeFile) throws FileNotFoundException{
		 return sr.readCSVFile(path, nomeFile);
	 }

}

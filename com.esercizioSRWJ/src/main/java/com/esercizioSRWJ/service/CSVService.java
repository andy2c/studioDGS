package com.esercizioSRWJ.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esercizioSRWJ.csv.SmartCSVReader;
import com.esercizioSRWJ.csv.SmartCSVWriter;
import com.esercizioSRWJ.dto.Prova;
import com.esercizioSRWJ.model.RichiestaConsegna;
import com.esercizioSRWJ.utilities.Write2;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@Service
public class CSVService {
	
	@Autowired
	private SmartCSVWriter sw;
//	@Autowired
//	private Write2 sw2;
	@Autowired
	private SmartCSVReader sr;
	
	protected String extension="csv";
	
	 public void writeCSV(String path, String nomeFile, List<RichiestaConsegna> listToWrite) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		 
		 sw.writeCSVFile(path, nomeFile, listToWrite);
		 
//		 File reportFile = new File(reportOutputDir + "/" + reportFileName);
//		 Writer writer = new PrintWriter(reportFile);
//		 StatefulBeanToCsv<CSVNamedColumnBean> beanToCsv = new 
//		                               StatefulBeanToCsvBuilder(writer).build();
//		 beanToCsv.write( );
//		 writer.close();
	}
	
	 public void writeCSV2(String path, String nomeFile, List<RichiestaConsegna> listToWrite) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, IllegalAccessException, InstantiationException, NoSuchFieldException, SecurityException, IllegalArgumentException {
		 
		 List<Prova> toWrite = new ArrayList<Prova>();
			for(RichiestaConsegna l : listToWrite)
				toWrite.add(new Prova(l));
		 
		 Write2<Prova> sw2 = new Write2<Prova> ();	
//		 sw.writeCSVFile2(path, nomeFile, "csv", listToWrite);		
		 sw2.writeCSVFile1(path, nomeFile, "csv", toWrite);
//		 for(RichiestaConsegna r : listToWrite)
//			 sw2.writeCSVFile(path, nomeFile, "csv", r);
		 
//		 try {
//			sw.writeCSVFile2(path, nomeFile, extension, listToWrite);
//		} catch (CsvDataTypeMismatchException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (CsvRequiredFieldEmptyException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		 
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

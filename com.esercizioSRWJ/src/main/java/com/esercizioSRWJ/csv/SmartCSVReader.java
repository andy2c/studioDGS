package com.esercizioSRWJ.csv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.springframework.stereotype.Component;

import com.esercizioSRWJ.model.RichiestaConsegna;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Component
public class SmartCSVReader {
	
	public List<RichiestaConsegna> readCSVFile(String path, String nomeFile) throws FileNotFoundException{
		
		FileReader reader = new FileReader(path+"\\"+nomeFile+".csv");
		CsvToBean<RichiestaConsegna> csvToBean = new CsvToBeanBuilder<RichiestaConsegna>(reader)
				.withType(RichiestaConsegna.class)
				.build();
		
		return csvToBean.parse();
	}

}

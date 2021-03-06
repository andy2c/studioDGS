package com.esercizioSRWJ.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.esercizioSRWJ.customAnnotaions.FieldPropertiesForCsv;
import com.esercizioSRWJ.dto.Prova;
import com.esercizioSRWJ.dto.Prova2;
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
	
	public void writeCSVFile2(String path, String nomeFile, String extension, List<RichiestaConsegna> lista) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InstantiationException {
		
		List<Prova2> toWrite = new ArrayList<Prova2>();
		for(RichiestaConsegna l : lista) {
			Prova2 p2 = new Prova2(l);
			buffInstance(Prova2.class, p2);
			toWrite.add(p2);
		}

		
		FileWriter writer = new FileWriter(path+"\\"+nomeFile+"."+extension);
		for(Prova2 p: toWrite) {
//			writer.write(mapWriter(p, p)+"\r\n");
			String stringToWrite = mapWriter(p, p)+"\r\n";
			Files.write(Paths.get(path+"\\"+nomeFile+"."+extension), stringToWrite.getBytes(), StandardOpenOption.APPEND);
		}
//		Writer writer = new FileWriter(path+"\\"+nomeFile+".csv");
//		StatefulBeanToCsv<Prova2> beanToCSV = new StatefulBeanToCsvBuilder<Prova2>(writer)
//				.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
//				.build();
//		beanToCSV.write(toWrite);
		writer.close();
	}
	

	

	
	public void buffInstance(Class clazz, Object p) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		Field[] allFields = clazz.getDeclaredFields();
		for(Field f: allFields) {
			Annotation an = f.getAnnotation(FieldPropertiesForCsv.class);
			FieldPropertiesForCsv fp =(FieldPropertiesForCsv)an;
			if(fp!= null) {
				f.setAccessible(true);
				int lenghtBufferToAdd = fp.totalFieldLenght() - String.valueOf(f.get(p)).length();
				if(lenghtBufferToAdd > 0)
					for(int i=0; i<lenghtBufferToAdd; i++)
						f.set(p, String.valueOf(f.get(p)) + fp.buffer());
			}
		}
	}
	
	public String buffString(Class clazz, Object p) throws IllegalArgumentException, IllegalAccessException {
		String ret = "";
		Field[] allFields = clazz.getDeclaredFields();
		for(Field f: allFields) {
			Annotation an = f.getAnnotation(FieldPropertiesForCsv.class);
			FieldPropertiesForCsv fp = (FieldPropertiesForCsv)an;
			if(fp!= null) {
				f.setAccessible(true);
				int lenghtBufferToAdd = fp.totalFieldLenght() - String.valueOf(f.get(p)).length();
				if(lenghtBufferToAdd > 0)
					for(int i=0; i<lenghtBufferToAdd; i++)
						ret= ret+f.get(p)+fp.buffer();
			}
		}
		/*
		Field[] allFields = clazz.getDeclaredFields();
		for(Field f: allFields) {
			Annotation an = f.getAnnotation(FieldPropertiesForCsv.class);
			FieldPropertiesForCsv fp =(FieldPropertiesForCsv)an;
			if(fp.bufferRequired()) {
				f.setAccessible(true);
				int lenghtBufferToAdd = fp.totalFieldLenght() - String.valueOf(f.get(p)).length();
				if(lenghtBufferToAdd > 0)
					for(int i=0; i<lenghtBufferToAdd; i++)
						ret= ret+f.get(p)+fp.buffer();
			}	
		}
		*/

		return ret;
	}
	
	public String mapWriter(Object... toWrite) throws IllegalArgumentException, IllegalAccessException {
		String ret="";
		Iterator<Object> iter = Stream.of(toWrite).iterator();
		
		while(iter.hasNext()) {
			Object obj = iter.next();
			Field[] allFields = obj.getClass().getDeclaredFields();
			
			for(int i=0;i<allFields.length; i++) {
				allFields[i].setAccessible(true);
				ret = ret+String.valueOf(allFields[i].get(obj));
				if(i<allFields.length-1)
					ret = ret+" ";
			}
			
			if(iter.hasNext())
				ret=ret+",";
		}
		
		return ret;
	}

}

package com.esercizioSRWJ.utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.esercizioSRWJ.customAnnotaions.FieldPropertiesForCsv;
import com.esercizioSRWJ.model.RichiestaConsegna;

@Component
public class Write2<T> {

    public void writeCSVFile1(String path, String nomeFile, String extension, List<T> list) throws IOException, IllegalAccessException, InstantiationException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        FileWriter writer = new FileWriter(path+"\\"+nomeFile+"."+extension);
//        for(int i=0; i<list.size(); i++) {
//        	ArrayList<Object> listSubObj = (ArrayList<Object>) list.get(i);
//            for(Object subObj : listSubObj )
//                writer.write(buffString(subObj.getClass(), subObj)+"\r\n");
//        }
//        for(Object obj : list)
//            for(Object subObj : (ArrayList<Object>) obj) {
//                System.out.println(subObj);
//            	writer.write(buffString(subObj.getClass(), subObj)+"\r\n");
//                }
        for(Object obj : list){
        	writer.write(buffString(obj.getClass(), obj)+"\r\n");
        }
        writer.write("fine");
        writer.close();
    }



//    public String buffString(Class clazz, T p) throws IllegalArgumentException, IllegalAccessException {
//        String ret = "";
//        Field[] allFields = clazz.getDeclaredFields();
//        for(Field f: allFields) {
//            Annotation an = f.getAnnotation(FieldPropertiesForCsv.class);
//            FieldPropertiesForCsv fp =(FieldPropertiesForCsv)an;
//            if(fp != null) {
//                f.setAccessible(true);
//                int lenghtBufferToAdd = fp.totalFieldLenght() - String.valueOf(f.get(p)).length();
//                if(lenghtBufferToAdd > 0)
//                    for(int i=0; i<lenghtBufferToAdd; i++)
//                        ret= ret+f.get(p)+fp.buffer();
//            }
//        }
//        return ret;
//    }
    
    public String buffString(Class clazz, Object p) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException {
    	String ret = "";
    	Field[] allFields = clazz.getDeclaredFields();
//    	Field[] allFields = p.getClass().getDeclaredFields();
    	
		for(Field f: allFields) {
			Annotation an = f.getAnnotation(FieldPropertiesForCsv.class);
			FieldPropertiesForCsv fp =(FieldPropertiesForCsv)an;
			if(fp!= null) {
				f.setAccessible(true);
				ret = f.get(p) != null? ret+f.get(p) : "";
//				int fieldLength = f.get(p) != null? String.valueOf(f.get(p)).length() : 0;
//				int lenghtBufferToAdd = fp.totalFieldLenght() - fieldLength;
				int lenghtBufferToAdd = fp.totalFieldLenght() - ret.length();
				if(lenghtBufferToAdd > 0)
					for(int i=0; i<lenghtBufferToAdd; i++)
						ret= ret+fp.buffer();
			}
		}
		return ret;
	}
	
    public void writeCSVFile (String path, String nomeFile, String extension, Object objToWrite) throws IOException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InstantiationException {
        FileWriter writer = new FileWriter(path+"\\"+nomeFile+"."+extension, true);
        writer.write(buffString2(objToWrite.getClass(), objToWrite)+"\r\n");
        writer.close();
    }



    public String buffString2(Class clazz, Object p) throws IllegalArgumentException, IllegalAccessException {
        
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
    	String ret = "";
        Field[] allFields = clazz.getDeclaredFields();
        for(Field f: allFields) {
            Annotation an = f.getAnnotation(FieldPropertiesForCsv.class);
            FieldPropertiesForCsv fp =(FieldPropertiesForCsv)an;
            if(fp != null) {
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

}

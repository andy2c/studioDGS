package com.esercizioSRWJ.customAnnotaions;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldPropertiesForCsv {

	boolean bufferRequired() default true;
	int totalFieldLenght() default 1;
	String buffer() default " ";
	
}

package com.dere.rest.restspring.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


import org.springframework.core.convert.converter.Converter;

public class StringtoDateConverter implements Converter<String, LocalDate> {

	@Override
	public LocalDate convert(String source) {
		
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(source, formatter);
			return localDate;
		} catch (DateTimeParseException e) {
			throw e;
		}
	}

}

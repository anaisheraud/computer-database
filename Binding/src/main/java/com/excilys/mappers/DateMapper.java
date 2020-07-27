package com.excilys.mappers;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateMapper {
	
/**
 * Convertir les dates	
 */
	
	public static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
	
	public static LocalDate sqlDateToLocalDate(Date date) {
		LocalDate result;
		if(date == null) {
			result = null;
		}else {
			result = LocalDate.parse(date.toString(),format);
		}
		return result;
		
	}
	
	public static LocalDate stringToLocalDate(String date) {
		LocalDate result;
		if(date == null) {
			result = null;
		}else if(date.equals("null")) {
			result = null;
		}else {
			result = LocalDate.parse(date,format);
		}
		return result;
	}

	public static Date localDateTosqlDate(LocalDate date) {
		Date result;
		if(date == null) {
			result = null;
		}else{
			result = Date.valueOf(date.toString());
		}
		
		return result;
	}

}

package com.morse.commons.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Utils {
	
	public static List<Date> getDaysBetweenDates(Date startDate, Date endDate)
	{
	    List<Date> dates = new ArrayList<Date>();
		Calendar calendarBeginDate = Calendar.getInstance();
		calendarBeginDate.setTime(startDate);
		calendarBeginDate.set(calendarBeginDate.get(Calendar.YEAR), calendarBeginDate.get(Calendar.MONTH), calendarBeginDate.get(Calendar.DATE), 0, 0);
		
		Calendar calendarEndDate = Calendar.getInstance();
		calendarEndDate.setTime(endDate);
		calendarEndDate.set(calendarEndDate.get(Calendar.YEAR), calendarEndDate.get(Calendar.MONTH), calendarEndDate.get(Calendar.DATE), 0, 0, 0);
		calendarEndDate.add(Calendar.DATE, 1);
		calendarEndDate.add(Calendar.SECOND, -1);

	    while (calendarBeginDate.getTime().before(calendarEndDate.getTime()))
	    {
	        dates.add(calendarBeginDate.getTime());
	        calendarBeginDate.add(Calendar.DATE, 1);
	    }
	    
	    return dates;
	}
	
	public static String generateRandomAlphanumeric() {
		char[] characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		char[] result = new char[10];
		
		Random random = new SecureRandom();
	    
	    for (int i = 0; i < result.length; i++) {
	        int randomCharIndex = random.nextInt(characterSet.length);
	        result[i] = characterSet[randomCharIndex];
	    }

	    return new String(result);
	}
	
	public static String convertDoubleToString(Double number, String separadorDecimales) {
		return number.toString().replace(",", separadorDecimales).replace(".", separadorDecimales);
	}
	
	public static Double convertStringToDouble(String number) {
		return Double.parseDouble(number.replace(",", "."));
	}
	
	public static String convertInputStreamToString(InputStream inputStream) throws IOException {
	    StringBuilder resultStringBuilder = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            resultStringBuilder.append(line).append("\n");
	        }
	    }
	  return resultStringBuilder.toString();
	}
	
	public static String convertIntegerDateToString(Integer date, String formatInput, String formatOutPut) {
		Calendar calendarDate = Calendar.getInstance();
		
		if (formatInput == "yyyyMMdd")
			calendarDate.set(Integer.parseInt(date.toString().substring(0, 3)), Integer.parseInt(date.toString().substring(4, 5)), 
					Integer.parseInt(date.toString().substring(6)));
		
		return convertDateToString(calendarDate.getTime(), formatOutPut);
	}
	
	public static String convertDateToString(Date date, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);

		return dateFormat.format(date);
	}
	
	public static Date convertStringToDate(String date, String format) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(format);
		
		return dateFormat.parse(date);
	}
	
	public static String getOnlyNumber(String caracteres) {
		String onlyNumber = "";
		
		for (char caracter : caracteres.toCharArray())
			if (Character.isDigit(caracter))
				onlyNumber = onlyNumber + caracter; 
		
		return onlyNumber;
	}
	
	public static Double Rounded(Double value, Integer quantityDecimals) {
		BigDecimal valorDecimal = new BigDecimal(value + "");
		return Double.valueOf(valorDecimal.setScale(quantityDecimals, RoundingMode.HALF_UP)	+ "");
	}
}

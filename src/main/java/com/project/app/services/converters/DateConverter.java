package com.project.app.services.converters;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Calendar> {

	static String unix_pattern = new String(
			"(\\d{10})");

	@Override
	public Calendar convert(String unixTimeString) {
		Pattern pattern = Pattern.compile(unix_pattern);
		Matcher matcher = pattern.matcher(unixTimeString);
		String unixTimeClean = null;
		while (matcher.find()) {
			String ar = matcher.group();
			unixTimeClean = ar;
		}
  
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(Long.valueOf(unixTimeClean));

		return calendar;
	}

}

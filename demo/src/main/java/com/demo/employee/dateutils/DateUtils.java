package com.demo.employee.dateutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String format(Date d, String format) {
		SimpleDateFormat sd = new SimpleDateFormat(format);
		String stringOutPutFormat = sd.format(d);
		return stringOutPutFormat;
	}

	public static Date format(String s, String format) {
		SimpleDateFormat sd = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sd.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static boolean isValidFormat(String format, String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(dateStr);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
}

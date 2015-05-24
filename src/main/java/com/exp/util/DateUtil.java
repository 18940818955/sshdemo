package com.exp.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static Date addDate(Date date, int amout) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, +3);
		Date result = cal.getTime();
		return result;
	}
}

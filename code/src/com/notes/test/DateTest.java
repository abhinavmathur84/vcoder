package com.notes.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTest {

	
	public static void main(String[] args) {
		long td = Long.parseLong("1428445322000");
		Date d = new Date(td);
		DateFormat df  = DateFormat.getDateTimeInstance();
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		String s = df.format(d);
		System.out.println(s);
		
	}
}

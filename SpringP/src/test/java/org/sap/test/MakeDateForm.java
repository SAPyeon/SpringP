package org.sap.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MakeDateForm {
	public static void main(String[] args) throws ParseException {
		//String specifiedDay = "2022-10-19";
		Date nowDate = new Date();
		System.out.println(nowDate);
		System.out.println(getSpecifiedDayBefore(nowDate));
	}
	//전날구하기
	public static String getSpecifiedDayBefore(Date nowDate) {

		// SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date date = null;
//		try {
//			//date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		c.setTime(nowDate);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayBefore;
	}
	//다음날 구하기
/*	public static String getSpecifiedDayAfter(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		java.util.Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayAfter;
	}*/
}

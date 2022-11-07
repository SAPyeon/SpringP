package org.sap.component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Component;

@Component
public class DateFormatCom {
	// date 포맷만들기
			public String makeDateFormate() {

				Calendar calendar = new GregorianCalendar();
				System.out.println(calendar);
				SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
				calendar.add(Calendar.DATE, -1); // 하루전 입력
				if (calendar.get(Calendar.DAY_OF_WEEK) == 1) { // 일요일인 경우
					calendar.add(Calendar.DATE, -2); // 이틀전 입력
				} else if (calendar.get(Calendar.DAY_OF_WEEK) == 7) { // 토요일 인경우
					calendar.add(Calendar.DATE, -1); // 하루전
				}
				String chkDate = SDF.format(calendar.getTime());
				System.out.println("기준날짜=" + chkDate);
				return chkDate;
			}

			// 전날구하기
			public static String getSpecifiedDayBefore(String specifiedDay) {

				// SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c = Calendar.getInstance();
				Date date = null;
				try {
					date = new SimpleDateFormat("yyyyMMdd").parse(specifiedDay);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				c.setTime(date);
				int day = c.get(Calendar.DATE);
				c.set(Calendar.DATE, day - 1);

				String dayBefore = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
				return dayBefore;
			}

			// 다음날 구하기
			public static String getSpecifiedDayAfter(String specifiedDay) {
				Calendar c = Calendar.getInstance();
				Date date = null;
				try {
					date = new SimpleDateFormat("yyyyMMdd").parse(specifiedDay);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				c.setTime(date);
				int day = c.get(Calendar.DATE);
				c.set(Calendar.DATE, day + 1);

				String dayAfter = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
				return dayAfter;
			}
}

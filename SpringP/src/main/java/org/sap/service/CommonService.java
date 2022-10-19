package org.sap.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //의존성주입
public class CommonService {
	public String makeDateFormate() {
		
		Calendar calendar = new GregorianCalendar();
		SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
		calendar.add(Calendar.DATE, -1); //하루전 입력
		if(calendar.get(Calendar.DAY_OF_WEEK)==1) { //일요일인 경우
			calendar.add(Calendar.DATE, -2); //이틀전 입력
		}else if(calendar.get(Calendar.DAY_OF_WEEK)==7) { //토요일 인경우
			calendar.add(Calendar.DATE, -1); //하루전
		}
		String chkDate = SDF.format(calendar.getTime());
		System.out.println("기준날짜="+chkDate);
		return chkDate;
	}
}

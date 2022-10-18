package org.sap.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.sap.service.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final StockService stockService;
	
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public void boardList() {
		
	}
	
	@RequestMapping(value = "/board/detail", method = RequestMethod.GET)
	public void boardDetail(HttpServletRequest request,Model model) {
		String codeName = request.getParameter("itmsNm");
		model.addAttribute("codeName", codeName);
		System.out.println("코드네임모델="+codeName);
	}
	
	@RequestMapping(value = "/board/searchList", method = RequestMethod.GET)
	public void searchList(HttpServletRequest request,Model model) throws IOException {
		String likeItmsNm = request.getParameter("likeItmsNm");
		//날짜구하기 = api는 전날기준 업데이트
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
		
		String[] arg = new String[3];
		arg[0] = "";
		arg[1] = likeItmsNm;
		arg[2] = chkDate;
		model.addAttribute("stock",stockService.getApiExplorerList(arg));
		
	}
	
}

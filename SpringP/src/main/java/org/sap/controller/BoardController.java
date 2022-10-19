package org.sap.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.sap.service.CommonService;
import org.sap.service.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final StockService stockService;
	
	private final CommonService commonService;
	//종목리스트 - 네이버증권 크롤링
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public void boardList() {
		
	}
	//종목 상세페이지 -  공공데이터 api
	@RequestMapping(value = "/board/detail", method = RequestMethod.GET)
	public void boardDetail(HttpServletRequest request,Model model) {
		String codeName = request.getParameter("itmsNm");
		model.addAttribute("codeName", codeName);
		System.out.println("코드네임모델="+codeName);
	}
	//종목찾기 - 공공데이터 api
	@RequestMapping(value = "/board/searchList", method = RequestMethod.GET)
	public void searchList(HttpServletRequest request,Model model) throws IOException {
		String likeItmsNm = request.getParameter("likeItmsNm");
		
		String[] arg = new String[5];
		arg[0] = "";
		arg[1] = "KOSPI";
		arg[2] = "";
		arg[3] = likeItmsNm;
		arg[4] = commonService.makeDateFormate();//날짜구하기 = api는 전날기준 업데이트
		model.addAttribute("stock",stockService.getApiExplorerList(arg));
		
	}
	
}

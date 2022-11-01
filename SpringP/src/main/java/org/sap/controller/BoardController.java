package org.sap.controller;

import javax.servlet.http.HttpServletRequest;

import org.sap.model.CompanyInfoDto;
import org.sap.model.CriteriaVO;
import org.sap.model.PageVO;
import org.sap.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class BoardController {
	
	public final BoardService boardservice;
	
	//종목리스트 - 네이버증권 크롤링
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public void boardList() {
		
	}
	
	//종목 상세페이지 -  공공데이터 api
	@RequestMapping(value = "/board/detail", method = RequestMethod.GET)
	public void boardDetail(HttpServletRequest request,Model model, CompanyInfoDto cid) {
		String codeName = request.getParameter("itmsNm");
		model.addAttribute("codeName", codeName);
		System.out.println("코드네임모델="+codeName);
		
		model.addAttribute("Info", boardservice.companyInfo(codeName));
		System.out.println("종목정보 = "+boardservice.companyInfo(codeName));
	}
	
	//종목찾기 - DB에서 찾기
	@RequestMapping(value = "/board/searchList", method = RequestMethod.GET)
	public void searchList(CriteriaVO cri,Model model, @RequestParam String search) {
		System.out.println(cri);
		
		System.out.println("찾기 = "+search);
		cri.setSearch(search);
		
		System.out.println("찾기목록 = "+boardservice.findSearhList(cri));
		model.addAttribute("stock", boardservice.findSearhList(cri));
		
		int total = boardservice.total(cri);
		System.out.println("리스트 총 수 = " + total);
		model.addAttribute("paging", new PageVO(cri,total));
	}
	
	// 상품글쓰기
	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public void boardWrite() {
		
	}
	
}

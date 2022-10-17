package org.sap.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class BoardController {
	
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public void boardList() {
		
	}
	
	@RequestMapping(value = "/board/detail", method = RequestMethod.GET)
	public void boardDetail(HttpServletRequest request,Model model) {
		String codeName = request.getParameter("itmsNm");
		model.addAttribute("codeName", codeName);
		System.out.println("코드네임모델="+codeName);
	}
	
	@RequestMapping(value = "/board/searchlist", method = RequestMethod.GET)
	public void searchList() {
		
	}
	
}

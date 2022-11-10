package org.sap.controller;

import org.sap.model.BoardDto;
import org.sap.service.CommunityServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommunityController {
	
	public final CommunityServiceImpl csi;

	// 커뮤니티글쓰기 - CKEDITOR4 사용
	@RequestMapping(value = "/community/write", method = RequestMethod.GET)
	public void commWrite() {

	}

	// 커뮤니티글쓰기 - CKEDITOR4 사용
	@RequestMapping(value = "/community/write", method = RequestMethod.POST)
	public String commWritePost(BoardDto bdto) {
		csi.write(bdto);
		System.out.println(bdto);
		return "redirect:/community/list";
	}
	
	//커뮤니티 글 불러오기
	@RequestMapping(value="/community/list", method = RequestMethod.GET)
	public void commlist(BoardDto bdto, Model model) {
		model.addAttribute("commlist", csi.list(bdto));
		
	}
	
	
	
}

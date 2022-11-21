package org.sap.controller;

import javax.servlet.http.HttpSession;

import org.sap.model.BoardDto;
import org.sap.service.CommunityServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String commWritePost(BoardDto bdto, HttpSession session) {
		bdto.setName((String)session.getAttribute("loginName"));
		bdto.setId((String)session.getAttribute("loginId"));
		csi.write(bdto);
		System.out.println(bdto);
		return "redirect:/community/list";
	}
	
	//커뮤니티 글 불러오기
	@RequestMapping(value="/community/list", method = RequestMethod.GET)
	public void commlist(BoardDto bdto, Model model) {
		model.addAttribute("commlist", csi.list(bdto));
		
	}
	// 커뮤니티 글 상세페이지
	@RequestMapping(value="/community/detail", method = RequestMethod.GET)
	public String commdetail(BoardDto bdto, Model model,@RequestParam String bno) {
		bdto.setBno(bno);
		//System.out.println(csi.detail(bdto)); // detail()함수 실행으로 조회수 두번찍힘
		model.addAttribute("detail",csi.detail(bdto));
		return "/community/comm_detail";
	}
	// 커뮤니티 글 수정
	@RequestMapping(value="/community/modify", method = RequestMethod.GET)
	public String commModify(BoardDto bdto, Model model,@RequestParam String bno) {
		bdto.setBno(bno);
		model.addAttribute("detail",csi.ModiDetail(bdto));
		return "/community/comm_modify";
	}
	@RequestMapping(value="/community/modify", method = RequestMethod.POST)
	public String commModifyPost(BoardDto bdto,@RequestParam String bno,RedirectAttributes rttr) {
		bdto.setBno(bno);
		csi.modify(bdto);
		rttr.addAttribute("bno", bdto.getBno());
		return "redirect:/community/detail";
	}
	
	// 커뮤니티 글 삭제
	@RequestMapping(value="/community/delete", method = RequestMethod.POST)
	public String commDeletePost(BoardDto bdto,@RequestParam String bno) {
		bdto.setBno(bno);
		csi.delete(bdto);
		return "redirect:/community/list";
	}
	
	
	
	
}

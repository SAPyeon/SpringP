package org.sap.controller;

import javax.servlet.http.HttpSession;

import org.sap.model.BoardDto;
import org.sap.model.DeclareDto;
import org.sap.service.AdminService;
import org.sap.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminController {
	
	public final AdminService adminService;
	private final MemberService memberService;
	
	//회원목록불러오기
	@RequestMapping(value="/admin/memberList", method = RequestMethod.GET)
	public void memberList(Model model) {
		model.addAttribute("memberList", adminService.memberList());
	}
	
	//각 회원들의 활동내역불러오기
	@RequestMapping(value="/admin/memberDetail", method = RequestMethod.GET)
	public void memberDetail(Model model, @RequestParam String id) {
		
		model.addAttribute("memCommList", memberService.memCommList(id));

		model.addAttribute("memCommReplyList", memberService.memCommReplyList(id));
	}
	
	// 신고 댓글 조회
	@RequestMapping(value="/admin/declareList",method=RequestMethod.GET)
	public void declareList(Model model) {
		model.addAttribute("declareReplList", adminService.declareReplList());
	}
	
	// 신고댓글 삭제 혹은 유지시 신고테이블에서도 삭제
	@RequestMapping(value="/admin/declareReplDelete",method=RequestMethod.DELETE)
	public ResponseEntity<Integer> declareReplDelete(@RequestBody DeclareDto ddto) {
		int result = adminService.declareReplDelete(ddto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	// 공지사항 등록
	@RequestMapping(value="/admin/notice", method=RequestMethod.POST)
	public String notice(BoardDto bdto, HttpSession session) {
		bdto.setId((String) session.getAttribute("loginId"));
		bdto.setName("관리자");
		bdto.setNotice(true);
		adminService.noticeWrite(bdto);
		System.out.println(bdto);
		return "redirect:/community/list";
	}
}

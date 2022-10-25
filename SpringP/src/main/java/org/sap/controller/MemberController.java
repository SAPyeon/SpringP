package org.sap.controller;

import javax.servlet.http.HttpSession;

import org.sap.model.MemberVO;
import org.sap.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private final MemberService memberService;
	//회원가입
	@RequestMapping(value = "/member/signup", method = RequestMethod.GET)
	public void SignUp() {
		
	}
	@RequestMapping(value = "/member/signup", method = RequestMethod.POST)
	public String SignUpPost(MemberVO mvo) {
		memberService.signup(mvo);
		return "redirect:/";
	}
	//로그인
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public void login() {
		
	}
	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	public String loginPost(MemberVO mvo,HttpSession session) {
		session.setAttribute("loginName", memberService.login(mvo).getName());
		memberService.login(mvo);
		return "redirect:/";
	}
	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		logger.info("로그아웃되었습니다.");
		return "redirect:/";
	}
}

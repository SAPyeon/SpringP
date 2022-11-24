package org.sap.controller;

import org.sap.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminController {
	
	public final AdminService adminService;
	
	@RequestMapping(value="/admin/memberList", method = RequestMethod.GET)
	public void memberList(Model model) {
		model.addAttribute("memberList", adminService.memberList());
	}
	
	@RequestMapping(value="/admin/memberDetail", method = RequestMethod.GET)
	public void memberDetail() {
		
	}
	
	
}

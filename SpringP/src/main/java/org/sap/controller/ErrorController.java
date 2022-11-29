package org.sap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ErrorController {
	
	@GetMapping("/divide")
	public String problem(Model model) {
		int result = 1/0;
		model.addAttribute("serverTime",result);
		return "/";
		
	}
	
	@PostMapping("/method")
	public String method(Model model) {
		return"/";
	}
	
	@GetMapping("/error")
	public String error() {
		return "/error/error";
	}
}

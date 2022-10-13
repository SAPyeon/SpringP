package org.sap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class BoardController {
	
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public void boardList() {
		
	}
	@RequestMapping(value = "/board/detail", method = RequestMethod.GET)
	public void boardDetail() {
		
	}
	
	
}

package org.sap.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.sap.model.CriteriaVO;
import org.sap.service.CommunityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequiredArgsConstructor
public class HomeController {

	private final CommunityService cs;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session, CriteriaVO cri) {
				
		logger.info("Welcome home! The client locale is {}.", locale);
		System.out.println(session);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		//System.out.println(cs.list(cri));
		model.addAttribute("commList",cs.list(cri));
		model.addAttribute("notice",cs.noticeList());
		model.addAttribute("serverTime", formattedDate);
	    
		return "/main/home";
	}
	
	@RequestMapping(value = "/dbUpdate", method = RequestMethod.GET)
	public void update() {
		
		
	}
	
}

package org.sap.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sap.model.MemberDto;
import org.sap.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor{
	
	@Autowired
	MemberService memberSerivce;
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        	
			System.out.println("인터셉터 : 로그인필요");
			
		 	String id = (String)request.getSession().getAttribute("loginId");
		 			 	 	
		 	if(id == null) {
		 		response.setContentType("text/html; charset=UTF-8");

		 		response.sendRedirect(request.getContextPath()+"/error");
		 		
		 		return false;
		 	}else {
		 		return true;
		 	}
    }
	
}

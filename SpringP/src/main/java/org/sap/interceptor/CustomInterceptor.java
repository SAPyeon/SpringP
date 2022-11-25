package org.sap.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sap.model.MemberDto;
import org.sap.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

public class CustomInterceptor implements HandlerInterceptor {
	
	@Autowired
	MemberService memberSerivce;
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        	
			MemberDto mdto = new MemberDto();
									
		 	String id = (String)request.getSession().getAttribute("loginId");
		 	
		 	mdto = memberSerivce.findById(id);
		 	
		 	System.out.println(mdto.isAuthority()); // boolean타입은 get()이 아니라 is()임
		 	
		 	if(mdto.isAuthority() == true) {
		 		return true;
		 	}else {
		 		response.sendRedirect(request.getContextPath()+"/error");
		 		return false;
		 	}
    }
       
}

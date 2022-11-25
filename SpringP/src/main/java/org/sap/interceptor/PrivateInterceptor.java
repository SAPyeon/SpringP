package org.sap.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sap.model.BoardDto;
import org.sap.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

public class PrivateInterceptor implements HandlerInterceptor {
	
	@Autowired
	CommunityService commSerivce;
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        	
			System.out.println("인터셉터 : 해당회원만 접근가능");
			
			// 게시판 아이디 = 로그인 아이디
			String bno = request.getParameter("bno");
			
			BoardDto bdto = new BoardDto();
			
			bdto.setBno(bno);
			
			String boardId = commSerivce.detail(bdto).getId();
			
			
			
			
			
		 	String id = (String)request.getSession().getAttribute("loginId");
		 			 	 	
		 	if(id == boardId) {
		 		return true;
		 	}else {
		 		response.sendRedirect(request.getContextPath()+"/error");
		 		return false;
		 	}
    }		
			
}

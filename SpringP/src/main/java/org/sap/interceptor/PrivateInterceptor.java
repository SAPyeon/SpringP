package org.sap.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sap.model.BoardDto;
import org.sap.model.ReplyDto;
import org.sap.service.CommunityService;
import org.sap.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.HandlerInterceptor;

public class PrivateInterceptor implements HandlerInterceptor {
	
	@Autowired
	CommunityService commSerivce;
	
	@Autowired
	ReplyService replySerivce;
	
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        	
			System.out.println("인터셉터 : 해당회원만 접근가능");
			
			// 게시판 아이디 = 로그인 아이디
			String bno = request.getParameter("bno");
			
			String boardId = null;
			
			String replyId = null;
			
			if(bno!=null) {
				BoardDto bdto = new BoardDto();
				
				bdto.setBno(bno);
				
				boardId = commSerivce.detail(bdto).getId();
				
			}
			
			// 댓글 아이디 = 로그인 아이디
			String rno = request.getParameter("rno");
			
			System.out.println("rno = "+rno);
			
			if(rno!=null) {
				ReplyDto reply = new ReplyDto();
				
				reply.setRno(rno);
				
				replyId = replySerivce.replSelect(rno).getId();
				
				System.out.println("리뷰아이디"+replyId);
				
			}
			
			// 세션아이디(로그인)
		 	String id = (String)request.getSession().getAttribute("loginId");
		 	
		 	System.out.println("게시판 아이디 = "+ boardId + "댓글아이디 = " + replyId);
		 	
			if(id == boardId || id == replyId) {
		 		return true;
		 	}else {
		 		response.sendRedirect(request.getContextPath()+"/error");
		 		return false;
		 	}
		 	
		 	
    }		
			
}

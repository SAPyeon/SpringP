package org.sap.controller;

import javax.servlet.http.HttpSession;

import org.sap.model.BoardDto;
import org.sap.model.CriteriaVO;
import org.sap.model.PageVO;
import org.sap.model.ReplyDto;
import org.sap.service.AdminService;
import org.sap.service.BoardService;
import org.sap.service.CommunityService;
import org.sap.service.CommunityServiceImpl;
import org.sap.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommunityController {

	private final CommunityService cs;
	
	private final ReplyService replyservice;	
	
	// 커뮤니티글쓰기 - CKEDITOR4 사용
	@RequestMapping(value = "/community/write", method = RequestMethod.GET)
	public void commWrite() {

	}

	// 커뮤니티글쓰기 - CKEDITOR4 사용
	@RequestMapping(value = "/community/write", method = RequestMethod.POST)
	public String commWritePost(BoardDto bdto, HttpSession session) {
		bdto.setName((String) session.getAttribute("loginName"));
		bdto.setId((String) session.getAttribute("loginId"));
		cs.write(bdto);
		System.out.println(bdto);
		return "redirect:/community/list";
	}

	// 커뮤니티 글 불러오기
	@RequestMapping(value = "/community/list", method = RequestMethod.GET)
	public void commlist(BoardDto bdto, Model model, CriteriaVO cri) {
		model.addAttribute("commlist", cs.list(cri));
		int total = cs.total();
		model.addAttribute("paging", new PageVO(cri, total));
		model.addAttribute("notice",cs.noticeList());
	}
	
	// 커뮤니티 글 상세페이지
	@RequestMapping(value = "/community/detail", method = RequestMethod.GET)
	public String commdetail(BoardDto bdto, Model model, @RequestParam String bno) {
			bdto.setBno(bno);
			// System.out.println(csi.detail(bdto)); // detail()함수 실행으로 조회수 두번찍힘
			model.addAttribute("detail", cs.detail(bdto));
		return "/community/comm_detail";
	}

	// 커뮤니티 글 수정
	@RequestMapping(value = "/community/modify", method = RequestMethod.GET)
	public String commModify(BoardDto bdto, Model model, @RequestParam String bno) {
			bdto.setBno(bno);
			model.addAttribute("detail", cs.ModiDetail(bdto));
		return "/community/comm_modify";
	}

	@RequestMapping(value = "/community/modify", method = RequestMethod.POST)
	public String commModifyPost(BoardDto bdto, @RequestParam String bno, RedirectAttributes rttr) {
			bdto.setBno(bno);
			cs.modify(bdto);
			rttr.addAttribute("bno", bdto.getBno());
			rttr.addAttribute("nno", "notice");
		return "redirect:/community/detail";
	}

	// 커뮤니티 글 삭제
	@RequestMapping(value = "/community/delete", method = RequestMethod.POST)
	public String commDeletePost(BoardDto bdto, @RequestParam String bno) {
		System.out.println("게시글 삭제");
		bdto.setBno(bno);
		cs.delete(bdto);
		return "redirect:/community/list";
	}

	// 댓글 신고하기
	@RequestMapping(value = "/community/replDeclaration", method = RequestMethod.GET)
	public void commdeclar(ReplyDto reply, @RequestParam String rno, HttpSession session, Model model) {
		String id = (String) session.getAttribute("loginId");
		reply.setRno(rno);
		reply.setId(id);
		model.addAttribute("declUser", reply);
	}

	// 댓글 신고하기
	@RequestMapping(value = "/community/replDeclaration", method = RequestMethod.POST)
	public ResponseEntity<String> commdeclarPost(ReplyDto reply) {
		System.out.println(reply);
		int result = replyservice.declaration(reply);
		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

package org.sap.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.sap.model.ReplyDto;
import org.sap.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReplyController {
	
	public final ReplyService replyservice;
	// 댓글쓰기
	@RequestMapping(value = "/replies/new", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public ResponseEntity<String> replywrite(@RequestBody ReplyDto reply) {
		System.out.println(reply);
		int result = replyservice.reWrite(reply);
		return result == 1?new ResponseEntity<>("success", HttpStatus.OK)
				:new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//댓글목록리스트
	@RequestMapping(value = "/replies/{bno}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<ReplyDto>> getlist(@PathVariable String bno) {
		System.out.println(bno);
		return new ResponseEntity<>(replyservice.relist(bno), HttpStatus.OK);
							 
	}
	// 댓글 삭제 리스트
	@RequestMapping(value="/replies/delete", method = RequestMethod.DELETE)
	public void replDelete(@RequestBody ReplyDto reply) {
		System.out.println("댓글 삭제 컨트롤러 이동");
		String rno = reply.getRno();
		replyservice.replDelete(rno);
	}
	// 댓글 수정 리스트
	@RequestMapping(value="/replies/modify", method = RequestMethod.POST)
	public void replModify(@RequestBody ReplyDto reply) {
		System.out.println("댓글 수정");
		//System.out.println("rno = "+rno);
		replyservice.replModify(reply);
	}
	//해당 댓글 불러오기
	@RequestMapping(value="/replies/select", method = RequestMethod.GET)
	public ReplyDto replSelect(@RequestParam String rno) {
		System.out.println("rno = "+rno);
		return replyservice.replSelect(rno);
	}
}

package org.sap.controller;

import java.util.ArrayList;

import org.sap.model.ReplyDto;
import org.sap.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReplyController {
	
	public final ReplyService replyservice;
	
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
}

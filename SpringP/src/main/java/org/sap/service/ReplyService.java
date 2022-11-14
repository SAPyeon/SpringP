package org.sap.service;

import java.util.ArrayList;

import org.sap.model.ReplyDto;

public interface ReplyService {
	//댓글쓰기
	public int reWrite(ReplyDto reply);
	//댓글리스트
	public ArrayList<ReplyDto> relist(String bno);
}

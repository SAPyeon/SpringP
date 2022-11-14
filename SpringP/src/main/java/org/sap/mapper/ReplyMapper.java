package org.sap.mapper;

import java.util.ArrayList;

import org.sap.model.ReplyDto;

public interface ReplyMapper {
	// 댓글쓰기
	public int reWrite(ReplyDto reply);
	// 댓글리스트
	public ArrayList<ReplyDto> relist(String bno);
	
	
}

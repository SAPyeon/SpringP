package org.sap.service;

import java.util.ArrayList;

import org.sap.model.BoardDto;

public interface CommunityService {
	// 커뮤니티글쓰기
	public void write(BoardDto bdto);
	// 커뮤니티 글목록
	public ArrayList<BoardDto> list(BoardDto bdto); 
}

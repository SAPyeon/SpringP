package org.sap.mapper;

import java.util.ArrayList;

import org.sap.model.BoardDto;

public interface CommMapper {
	// 글쓰기
	public void write(BoardDto bdto);
	// 글목록
	public ArrayList<BoardDto> list(BoardDto bdto);
	// 글상세페이지
	public BoardDto detail(BoardDto bdto);
}

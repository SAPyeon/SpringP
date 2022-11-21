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
	// 상세페이지 조회시 조회수 올리기
	public void cntUp(BoardDto bdto);
	// 글 수정
	public void modify(BoardDto bdto);
	// 글 삭제
	public void delete(BoardDto bdto);
}

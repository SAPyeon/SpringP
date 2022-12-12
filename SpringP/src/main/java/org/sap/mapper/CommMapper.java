package org.sap.mapper;

import java.util.ArrayList;

import org.sap.model.BoardDto;
import org.sap.model.CriteriaVO;
import org.sap.model.ReplyDto;

public interface CommMapper {
	// 글쓰기 db저장
	public void write(BoardDto bdto);
	// 글목록 불러오기
	public ArrayList<BoardDto> list(CriteriaVO cri);
	// 글 총수 불러오기
	public int total();
	// 글상세페이지
	public BoardDto detail(BoardDto bdto);
	// 상세페이지 조회시 조회수 올리기
	public void cntUp(BoardDto bdto);
	// 글 수정
	public void modify(BoardDto bdto);
	// 글 삭제
	public void delete(BoardDto bdto);
	// 게시판 아이디(작성자) 찾기
	public String findId(String bno);
	// 게시판 공지사항 리스트
	public ArrayList<BoardDto> noticeList();
	
}

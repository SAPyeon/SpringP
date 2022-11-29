package org.sap.service;

import java.util.ArrayList;

import org.sap.model.BoardDto;
import org.sap.model.CriteriaVO;
import org.sap.model.ReplyDto;

public interface CommunityService {
	// 커뮤니티글쓰기
	public void write(BoardDto bdto);
	// 커뮤니티 글목록
	public ArrayList<BoardDto> list(CriteriaVO cri);
	// 커뮤니티 글 총 갯수
	public int total();
	//커뮤니티 상세페이지
	public BoardDto detail(BoardDto bdto);
	//커뮤니티 글 수정시 상세페이지 불러오기
	public BoardDto ModiDetail(BoardDto bdto);
	//커뮤니티 글 수정
	public void modify(BoardDto bdto);
	//커뮤니티 글 삭제
	public void delete(BoardDto bdto);
	//커뮤니티 게시판 아이디 찾기
	public String findId(String bno);
	
}

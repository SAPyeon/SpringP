package org.sap.mapper;

import java.util.ArrayList;

import org.sap.model.ReplyDto;

public interface ReplyMapper {
	// 댓글쓰기
	public int reWrite(ReplyDto reply);
	// 댓글리스트
	public ArrayList<ReplyDto> relist(String bno);
	// 댓글 테이블 삭제
	public void replDelete(String rno);
	// 댓글 테이블 수정
	public void replModify(ReplyDto reply);
	// 테이블에서 해당 댓글 불러오기
	public ReplyDto replSelect(String rno);
	// 댓글신고
	public int declaration(ReplyDto reply);
	// 댓글 신고시 게시판 댓글 테이블에 신고(declaration) +1
	public void insertPlusDeclaration(String rno);
	
}

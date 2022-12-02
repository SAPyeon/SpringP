package org.sap.mapper;

import java.util.ArrayList;

import org.sap.model.BoardDto;
import org.sap.model.ChangeAuthDto;
import org.sap.model.DeclareDto;
import org.sap.model.MemberDto;

public interface AdminMapper {
	// 멤버 데이터 불러오기
	public ArrayList<MemberDto> memberList();
	// 신고 댓글 데이터 불러오기
	public ArrayList<DeclareDto> declareReplList();
	// 해당 댓글 신고리스트 데이터 모두 삭제
	public int declareReplDelete(DeclareDto ddto);
	// 공지사항 테이블에 insert
	public void noticeWrite(BoardDto bdto);
	//관리자 신청 리스트 불러오기
	public ArrayList<ChangeAuthDto> changeAuthList();
	// 관리자 신청 여부 데이터 불러오기
	public ChangeAuthDto findAuthChange(String id);
	// 관리자 신청 승인으로 회원테이블 데이터 변경
	public int changeAuthUp(MemberDto mdto);
	// changeAuth테이블에 승인을 true로 변경
	public int changeAllowAuth(ChangeAuthDto cadto);
		
}

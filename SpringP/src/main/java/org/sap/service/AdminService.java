package org.sap.service;

import java.util.ArrayList;

import org.sap.model.BoardDto;
import org.sap.model.ChangeAuthDto;
import org.sap.model.DeclareDto;
import org.sap.model.MemberDto;
import org.sap.model.ReplyDto;

public interface AdminService {

	// 멤버리스트
	public ArrayList<MemberDto> memberList();

	// 신고리스트
	public ArrayList<DeclareDto> declareReplList();

	// 해당댓글 신고리스트 삭제
	public int declareReplDelete(DeclareDto ddto);

	// 공지사항등록
	public void noticeWrite(BoardDto bdto);

	// 관리자 신청 리스트
	public ArrayList<ChangeAuthDto> changeAuthList();

	// 관리자 신청 여부 확인
	public ChangeAuthDto findAuthChange(String id);

	// 관리자 신청 승인(회원->관리자)
	public int changeAuthUp(MemberDto mdto);

	// 관리자 신청서에 승인 확인
	public int changeAllowAuth(ChangeAuthDto cadto);

	// 해당 아이디 커뮤니티 게시판 리스트
	public ArrayList<BoardDto> memCommList(String id);

	// 해당 아이디 커뮤니티 게시판 댓글 리스트
	public ArrayList<ReplyDto> memCommReplyList(String id);
}

package org.sap.mapper;

import java.util.ArrayList;

import org.sap.model.BoardDto;
import org.sap.model.ChangeAuthDto;
import org.sap.model.LikeDto;
import org.sap.model.MemberDto;
import org.sap.model.ReplyDto;
import org.sap.model.WithdrawalDto;

public interface MemberMapper {
	//회원가입
	public void signup(MemberDto mdto);
	//로그인
	public MemberDto login(MemberDto mdto);
	//아이디 찾기
	public MemberDto findById(String id);
	//회원정보수정
	public void updateInfo(MemberDto mdto);
	//회원탈퇴로 멤버테이블에 데이터 삭제
	public void deleteMember(MemberDto mdto);
	//탈퇴 테이블에 저장
	public void withdrawalInsert(WithdrawalDto wdto);
	//회원 아이디 찾기
	public ArrayList<MemberDto> findLoginId(MemberDto mdto);
	//즐겨찾기 목록 불러오기
	public boolean findlike(LikeDto likedto);
	//즐겨찾기 삭제
	public int likeDelete(LikeDto likedto);
	//즐겨찾기 추가
	public int likeInsert(LikeDto likedto);
	//주식종목 즐겨찾기 리스트
	public ArrayList<LikeDto> likeList(String id);
	// 해당아이디 커뮤니티 글 불러오기
	public ArrayList<BoardDto> memCommList(String id);
	// 해당 아이디 커뮤니티 게시판 댓글 불러오기
	public ArrayList<ReplyDto> memCommReplyList(String id);
	// 해당 아이디 커뮤니티 게시판 글 삭제하기
	public int boardDetail(String bno);
	// 관리자 신청 테이블에 insert하기
	public void changeAuth(ChangeAuthDto cadto);
	
	
	
	
	
}

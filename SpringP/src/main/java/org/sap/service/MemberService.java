package org.sap.service;

import java.util.ArrayList;

import org.sap.model.BoardDto;
import org.sap.model.ChangeAuthDto;
import org.sap.model.LikeDto;
import org.sap.model.MemberDto;
import org.sap.model.ReplyDto;
import org.sap.model.WithdrawalDto;

public interface MemberService {
	
	//회원가입
	public void signup(MemberDto mdto);
	//로그인
	public MemberDto login(MemberDto mdto);
	//아이디찾기
	public MemberDto findById(String id);
	//회원정보수정
	public void updateInfo(MemberDto mdto);
	//회원탈퇴
	public void deleteMember(MemberDto mdto);
	// 탈퇴이유저장
	public void withdrawalInsert(WithdrawalDto wdto);
	// 아이디찾기
	public MemberDto findLoginId(MemberDto mdto);
	//즐겨찾기
	public boolean findlike(LikeDto likedto);
	//즐겨찾기 삭제
	public int likeDelete(LikeDto likedto);
	//즐겨찾기 추가
	public int likeInsert(LikeDto likedto);
	//즐겨찾기 리스트
	public ArrayList<LikeDto> likeList(String id);
	//해당 아이디 커뮤니티 게시판 리스트
	public ArrayList<BoardDto> memCommList(String id);
	//해당 아이디 커뮤니티 게시판 댓글 리스트
	public ArrayList<ReplyDto> memCommReplyList(String id);
	// 해당 아이디 커뮤니티 게시판 삭제
	public int boardDelete(String bno);
	//관리자신청 등록하기
	public void changeAuth(ChangeAuthDto cadto);
	
	
	
	
}

package org.sap.service;

import org.sap.model.LikeDto;
import org.sap.model.MemberDto;
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
	public void deleteMember(WithdrawalDto wdto);
	//즐겨찾기
	public boolean findlike(LikeDto likedto);
	//즐겨찾기 삭제
	public int likeDelete(LikeDto likedto);
	//즐겨찾기 추가
	public int likeInsert(LikeDto likedto);
	
}

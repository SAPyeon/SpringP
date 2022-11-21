package org.sap.service;

import org.sap.model.LikeDto;
import org.sap.model.MemberDto;

public interface MemberService {
	//회원가입
	public void signup(MemberDto mdto);
	//로그인
	public MemberDto login(MemberDto mdto);
	//아이디찾기
	public String findById(String id);
	//즐겨찾기
	public boolean findlike(LikeDto likedto);
	//즐겨찾기 삭제
	public int likeDelete(LikeDto likedto);
	//즐겨찾기 추가
	public int likeInsert(LikeDto likedto);
	
}

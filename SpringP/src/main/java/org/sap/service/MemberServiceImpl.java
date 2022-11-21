package org.sap.service;


import org.sap.mapper.MemberMapper;
import org.sap.model.LikeDto;
import org.sap.model.MemberDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	private final MemberMapper memberMapper;
	
	@Transactional
	@Override
	public void signup(MemberDto mdto){
		mdto.setPoint(1000000);
		System.out.println(mdto);
		memberMapper.signup(mdto);
	}

	@Override
	public MemberDto login(MemberDto mdto) {
		return memberMapper.login(mdto);
	}

	@Override
	public MemberDto findById(String id) {
		return memberMapper.findById(id);
	}

	@Override
	public boolean findlike(LikeDto likedto) {
		return memberMapper.findlike(likedto);
	}

	@Override
	public int likeDelete(LikeDto likedto) {
		return memberMapper.likeDelete(likedto);
	}

	@Override
	public int likeInsert(LikeDto likedto) {
		return memberMapper.likeInsert(likedto);
	}

		
	
}

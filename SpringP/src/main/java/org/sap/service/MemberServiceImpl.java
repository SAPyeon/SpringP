package org.sap.service;

import javax.servlet.http.HttpSession;

import org.sap.mapper.MemberMapper;
import org.sap.model.MemberVO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	private final MemberMapper memberMapper;
	
	@Override
	public void signup(MemberVO mvo) {
		mvo.setPoint(1000000);
		System.out.println(mvo);
		memberMapper.signup(mvo);
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		return memberMapper.login(mvo);
	}

	
	
}

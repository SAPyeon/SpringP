package org.sap.service;


import org.sap.mapper.MemberMapper;
import org.sap.model.MemberDto;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	private final MemberMapper memberMapper;
	
	@Override
	public void signup(MemberDto mdto) {
		mdto.setPoint(1000000);
		System.out.println(mdto);
		memberMapper.signup(mdto);
	}

	@Override
	public MemberDto login(MemberDto mdto) {
		return memberMapper.login(mdto);
	}

	@Override
	public String findById(String id) {
		return memberMapper.findById(id);
	}

		
	
}

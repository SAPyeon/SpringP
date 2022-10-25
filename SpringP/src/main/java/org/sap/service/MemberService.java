package org.sap.service;


import org.sap.model.MemberVO;

public interface MemberService {
	public void signup(MemberVO mvo);

	public MemberVO login(MemberVO mvo);
}

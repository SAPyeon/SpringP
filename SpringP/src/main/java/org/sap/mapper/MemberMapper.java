package org.sap.mapper;

import org.sap.model.MemberVO;

public interface MemberMapper {
	public void signup(MemberVO mvo);

	public MemberVO login(MemberVO mvo);

	public String findById(String id);
}

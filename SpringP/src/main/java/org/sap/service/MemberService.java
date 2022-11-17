package org.sap.service;

import org.sap.model.MemberDto;

public interface MemberService {
	public void signup(MemberDto mdto);

	public MemberDto login(MemberDto mdto);

	public String findById(String id);

	
}

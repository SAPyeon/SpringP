package org.sap.mapper;

import org.sap.model.MemberDto;

public interface MemberMapper {
	public void signup(MemberDto mdto);

	public MemberDto login(MemberDto mdto);

	public String findById(String id);
}

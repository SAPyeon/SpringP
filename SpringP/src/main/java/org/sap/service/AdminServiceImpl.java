package org.sap.service;

import java.util.ArrayList;

import org.sap.mapper.AdminMapper;
import org.sap.mapper.MemberMapper;
import org.sap.model.MemberDto;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
	
	private final AdminMapper adminMapper;
	
	public ArrayList<MemberDto> memberList() {
		return adminMapper.memberList();
	}
}

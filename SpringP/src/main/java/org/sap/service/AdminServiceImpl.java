package org.sap.service;

import java.util.ArrayList;

import org.sap.mapper.AdminMapper;
import org.sap.mapper.MemberMapper;
import org.sap.model.BoardDto;
import org.sap.model.ChangeAuthDto;
import org.sap.model.DeclareDto;
import org.sap.model.MemberDto;
import org.sap.model.ReplyDto;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
	
	private final AdminMapper adminMapper;
	
	public ArrayList<MemberDto> memberList() {
		return adminMapper.memberList();
	}

	@Override
	public ArrayList<DeclareDto> declareReplList() {
		return adminMapper.declareReplList();
	}

	@Override
	public int declareReplDelete(DeclareDto ddto) {
		return adminMapper.declareReplDelete(ddto);
	}

	@Override
	public void noticeWrite(BoardDto bdto) {
		adminMapper.noticeWrite(bdto);
	}

	@Override
	public ArrayList<ChangeAuthDto> changeAuthList() {
		return adminMapper.changeAuthList();
	}

	@Override
	public ChangeAuthDto findAuthChange(String id) {
		return adminMapper.findAuthChange(id);
	}

	@Override
	public int changeAuthUp(MemberDto mdto) {
		return adminMapper.changeAuthUp(mdto);
	}

	@Override
	public int changeAllowAuth(ChangeAuthDto cadto) {
		return adminMapper.changeAllowAuth(cadto);
	}

	@Override
	public ArrayList<BoardDto> memCommList(String id) {
		return adminMapper.memCommList(id);
	}

	@Override
	public ArrayList<ReplyDto> memCommReplyList(String id) {
		return adminMapper.memCommReplyList(id);
	}

	

	
}

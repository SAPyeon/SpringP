package org.sap.service;

import java.util.ArrayList;

import org.sap.model.DeclareDto;
import org.sap.model.MemberDto;

public interface AdminService {

	//멤버리스트
	public ArrayList<MemberDto> memberList();
	// 신고리스트
	public ArrayList<DeclareDto> declareReplList();
	// 해당댓글 신고리스트 삭제
	public int declareReplDelete(DeclareDto ddto);
	
}

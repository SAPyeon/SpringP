package org.sap.mapper;

import java.util.ArrayList;

import org.sap.model.DeclareDto;
import org.sap.model.MemberDto;

public interface AdminMapper {
	// 멤버 데이터 불러오기
	public ArrayList<MemberDto> memberList();
	// 신고 댓글 데이터 불러오기
	public ArrayList<DeclareDto> declareReplList();
	// 해당 댓글 신고리스트 데이터 모두 삭제
	public int declareReplDelete(DeclareDto ddto);
		
}

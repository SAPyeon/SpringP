package org.sap.service;


import java.util.ArrayList;

import org.sap.mapper.MemberMapper;
import org.sap.model.BoardDto;
import org.sap.model.ChangeAuthDto;
import org.sap.model.LikeDto;
import org.sap.model.MemberDto;
import org.sap.model.ReplyDto;
import org.sap.model.WithdrawalDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	private final MemberMapper memberMapper;
	
	@Transactional
	@Override
	public void signup(MemberDto mdto){
		mdto.setPoint(1000000);
		System.out.println(mdto);
		memberMapper.signup(mdto);
	}

	@Override
	public MemberDto login(MemberDto mdto) {
		return memberMapper.login(mdto);
	}

	@Override
	public MemberDto findById(String id) {
		return memberMapper.findById(id);
	}
	
	@Override
	public void updateInfo(MemberDto mdto) {
		memberMapper.updateInfo(mdto);
	}
	
	@Override
	public void deleteMember(MemberDto mdto) {
		memberMapper.deleteMember(mdto);
	}
	
	@Override
	public void withdrawalInsert(WithdrawalDto wdto) {
		memberMapper.withdrawalInsert(wdto);
	}
	
	@Override
	public MemberDto findLoginId(MemberDto mdto) {
		
		//네이버 카카오 회원제외
		ArrayList<MemberDto> member = memberMapper.findLoginId(mdto);
		
		MemberDto mdto2 = new MemberDto();
		
		for(int i =0; i<member.size(); i++) {
			String id = member.get(i).getId();
			if(!id.substring(0, 2).equals("K+")) {
				mdto2.setId(id);
				mdto2.setName(member.get(i).getName());
			}
		}
								
		return mdto2;
	}

	
	@Override
	public boolean findlike(LikeDto likedto) {
		return memberMapper.findlike(likedto);
	}

	@Override
	public int likeDelete(LikeDto likedto) {
		return memberMapper.likeDelete(likedto);
	}

	@Override
	public int likeInsert(LikeDto likedto) {
		return memberMapper.likeInsert(likedto);
	}

	@Override
	public ArrayList<LikeDto> likeList(String id) {
		return memberMapper.likeList(id);
	}

	@Override
	public ArrayList<BoardDto> memCommList(String id) {
		return memberMapper.memCommList(id);
	}

	@Override
	public ArrayList<ReplyDto> memCommReplyList(String id) {
		return memberMapper.memCommReplyList(id);
	}

	@Override
	public int boardDelete(String bno) {
		return memberMapper.boardDetail(bno);
	}

	@Override
	public void changeAuth(ChangeAuthDto cadto) {
		memberMapper.changeAuth(cadto);
	}

	

	

	

	
	

		
	
}

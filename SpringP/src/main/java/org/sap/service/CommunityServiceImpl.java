package org.sap.service;

import java.util.ArrayList;

import org.sap.mapper.CommMapper;
import org.sap.mapper.ReplyMapper;
import org.sap.model.BoardDto;
import org.sap.model.CriteriaVO;
import org.sap.model.ReplyDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //의존성주입
public class CommunityServiceImpl implements CommunityService{
	public final CommMapper commMapper;
	public final ReplyMapper replmapper;
	// 글쓰기
	@Override
	public void write(BoardDto bdto) {
		commMapper.write(bdto);
	}
	// 리스트 작성
	@Override
	public ArrayList<BoardDto> list(CriteriaVO cri) {
		return commMapper.list(cri);
	}
	// 글 총 갯수
	@Override
	public int total() {
		return commMapper.total();
	}
	//조회수 올리기
	@Transactional
	@Override
	public BoardDto detail(BoardDto bdto) {
		bdto = commMapper.detail(bdto);
		String id = bdto.getId();
		if(id.length() >5) {
			id = id.substring(0, 5);
			bdto.setId(id);
		}
		commMapper.cntUp(bdto);
		return bdto;
	}
	
	@Override
	public BoardDto ModiDetail(BoardDto bdto) {
		return commMapper.detail(bdto);
	}
	
	@Override
	public void modify(BoardDto bdto) {
		commMapper.modify(bdto);
	}
	@Override
	public void delete(BoardDto bdto) {
		commMapper.delete(bdto);
	}
	
	@Override
	public String findId(String bno) {
		return commMapper.findId(bno);
	}
	
	
}

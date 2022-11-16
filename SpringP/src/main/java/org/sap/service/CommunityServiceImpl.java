package org.sap.service;

import java.util.ArrayList;

import org.sap.mapper.CommMapper;
import org.sap.model.BoardDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //의존성주입
public class CommunityServiceImpl implements CommunityService{
	public final CommMapper commMapper;
	
	@Override
	public void write(BoardDto bdto) {
		commMapper.write(bdto);
	}
	@Override
	public ArrayList<BoardDto> list(BoardDto bdto) {
		return commMapper.list(bdto);
	}
	//조회수 올리기
	@Transactional
	public BoardDto detail(BoardDto bdto) {
		commMapper.cntUp(bdto);
		return commMapper.detail(bdto);
	}
	
	@Override
	public void modify(BoardDto bdto) {
		commMapper.modify(bdto);
	}
}

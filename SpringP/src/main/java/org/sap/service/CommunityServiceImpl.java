package org.sap.service;

import java.util.ArrayList;

import org.sap.mapper.CommMapper;
import org.sap.model.BoardDto;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //의존성주입
public class CommunityServiceImpl implements CommunityService{
	public final CommMapper commMapper;
	
	@Override
	public void write(BoardDto bdto) {
		commMapper.write(bdto);
	}

	public ArrayList<BoardDto> list(BoardDto bdto) {
		return commMapper.list(bdto);
	}

	public BoardDto detail(BoardDto bdto) {
		return commMapper.detail(bdto);
	}
}

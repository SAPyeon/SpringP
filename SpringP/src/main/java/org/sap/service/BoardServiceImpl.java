package org.sap.service;

import java.util.ArrayList;

import org.sap.mapper.StockMapper;
import org.sap.model.CriteriaVO;
import org.sap.model.StockDto;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor //의존성주입
public class BoardServiceImpl implements BoardService {
	
	public final StockMapper stockMapper;
	
	@Override
	public ArrayList<StockDto> findSearhList(CriteriaVO cri) {
		System.out.println("매퍼리스트 = "+stockMapper.findList(cri));
		return stockMapper.findList(cri);
	}

	@Override
	public int total(CriteriaVO cri) {
		System.out.println("매퍼 리스트 총 수 = " + stockMapper.total(cri));
		return stockMapper.total(cri);
	}
	
}

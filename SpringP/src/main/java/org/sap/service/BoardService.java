package org.sap.service;

import java.util.ArrayList;

import org.sap.model.BoardVO;
import org.sap.model.CompanyInfoDto;
import org.sap.model.CriteriaVO;
import org.sap.model.StockDto;

public interface BoardService {
	//찾기 리스트 불러오기
	public ArrayList<StockDto> findSearhList(CriteriaVO cri);
	//페이징할 전체 수 불러오기
	public int total(CriteriaVO cri);
	//종목정보리스트 불러오기
	public CompanyInfoDto companyInfo(String codeName);
	// 종목추천글쓰기
	public void write(BoardVO bvo);
}

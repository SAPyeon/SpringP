package org.sap.service;

import java.util.ArrayList;
import java.util.List;

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
	//csv주식 데이터베이스에 저장
	public void insertStockDto(List<StockDto> stockUpdateList);
	//주식종목 기준일자별 시세 저장
	public void insertCompanyInfo(String path);
	
}

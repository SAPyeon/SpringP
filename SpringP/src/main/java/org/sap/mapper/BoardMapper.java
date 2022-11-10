package org.sap.mapper;

import java.util.ArrayList;
import java.util.List;

import org.sap.model.CompanyInfoDto;
import org.sap.model.CriteriaVO;
import org.sap.model.StockDto;

public interface BoardMapper {

	// 주식 db넣기
	public void insertStockInfo(List<StockDto> stockUpdateList);

	// 주식 종목 찾기
	public ArrayList<StockDto> findList(CriteriaVO cri);

	// 찾은 총 갯수
	public int total(CriteriaVO cri);

	// 종목 기본정보 db넣기
	public void insertCompanyInfo(List<CompanyInfoDto> list);

	// 종목 기본정보 불러오기
	public CompanyInfoDto companyInfo(String codeName);

}

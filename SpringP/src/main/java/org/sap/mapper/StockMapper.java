package org.sap.mapper;


import java.util.ArrayList;

import org.sap.model.CompanyInfoDto;
import org.sap.model.CriteriaVO;
import org.sap.model.StockDto;

public interface StockMapper {

	public void insertStockInfo(StockDto stockDto);

	public ArrayList<StockDto> findList(CriteriaVO cri);

	public int total(CriteriaVO cri);

	public void insertCompanyInfo(CompanyInfoDto comInfoDto);
	

}

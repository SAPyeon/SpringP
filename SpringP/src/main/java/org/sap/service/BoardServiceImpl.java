package org.sap.service;

import java.util.ArrayList;
import java.util.List;

import org.sap.component.CSVReader;
import org.sap.mapper.BoardMapper;
import org.sap.model.CompanyInfoDto;
import org.sap.model.CriteriaVO;
import org.sap.model.StockDto;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor //의존성주입
public class BoardServiceImpl implements BoardService {
		
	public final BoardMapper boardmapper;
	
	@Override
	public ArrayList<StockDto> findSearhList(CriteriaVO cri) {
		System.out.println("매퍼리스트 = "+boardmapper.findList(cri));
		return boardmapper.findList(cri);
	}

	@Override
	public int total(CriteriaVO cri) {
		System.out.println("매퍼 리스트 총 수 = " + boardmapper.total(cri));
		return boardmapper.total(cri);
	}

	@Override
	public CompanyInfoDto companyInfo(String codeName) {
		return boardmapper.companyInfo(codeName);
	}

	

	//csv주식 데이터베이스에 저장
	@Override
	public void insertStockDto(List<StockDto> stockUpdateList) {
		System.out.println(stockUpdateList.get(0));
		//list를 db에 입력
		boardmapper.insertStockInfo(stockUpdateList);
		
//		for(int i=0;i<stockUpdateList.size();i++) {
//	    	stockMapper.insertStockInfo(stockUpdateList.get(i));	
//	    }
	}
	
	//주식종목 기준일자별 시세 저장(공공데이터api)
	@Override
	public void insertCompanyInfo(String path) {
		CSVReader csvReader = new CSVReader();
		System.out.println("리스트  = "+csvReader.readCSV(path));
		boardmapper.insertCompanyInfo(csvReader.readCSV(path));	
/*	   for(int i=0;i<csvReader.readCSV(path).size();i++) {
	    	stockMapper.insertCompanyInfo(csvReader.readCSV(path).get(i));	
	   }
*/
	}
	
	
	
}

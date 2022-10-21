package org.sap.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.sap.component.ApiExplorer;
import org.sap.component.CSVReader;
import org.sap.component.JsoupComponent;
import org.sap.mapper.StockMapper;
import org.sap.model.CompanyInfoDto;
import org.sap.model.KospiStockDto;
import org.sap.model.StockDto;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //의존성주입
public class StockService {
	
	private final JsoupComponent jsoupComponent;
	
	private final ApiExplorer apiExplorer;
	
	private final StockMapper stockMapper;
	
	
	//주가리스트 (네이버 크롤링)
	public List<KospiStockDto> getKospiStockList(int p) {
		return jsoupComponent.getKospiStockList(p);
	}
	
	//주식종목 상세페이지(공공데이터 api)
	public List<StockDto> getApiExplorerList(String ...arg) throws IOException, ParseException{
		System.out.println("서비스인자="+arg[0]);
		
		return apiExplorer.getStock(arg);
	}
	
	//csv주식 데이터베이스에 저장
	public void insertStockDto(List<StockDto> stockUpdateList) {
		System.out.println(stockUpdateList.get(0));
		for(int i=0;i<stockUpdateList.size();i++) {
	    	stockMapper.insertStockInfo(stockUpdateList.get(i));	
	    }
	}
	
	//주식종목 기준일자별 시세 저장(공공데이터api)
	public void insertCompanyInfo(String path) {
		CSVReader csvReader = new CSVReader();
		System.out.println("리스트  = "+csvReader.readCSV(path));
		stockMapper.insertCompanyInfo(csvReader.readCSV(path));	
/*	   for(int i=0;i<csvReader.readCSV(path).size();i++) {
	    	stockMapper.insertCompanyInfo(csvReader.readCSV(path).get(i));	
	   }
*/
	   
	}
}

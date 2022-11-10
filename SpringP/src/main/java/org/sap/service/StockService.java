package org.sap.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.sap.component.ApiExplorer;
import org.sap.component.JsoupComponent;
import org.sap.model.KospiStockDto;
import org.sap.model.StockDto;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //의존성주입
public class StockService {
	
	private final JsoupComponent jsoupComponent;
	
	private final ApiExplorer apiExplorer;
	
	//주가리스트 (네이버 크롤링)
	public List<KospiStockDto> getKospiStockList(int p) {
		return jsoupComponent.getKospiStockList(p);
	}
	
	//주식종목 상세페이지(공공데이터 api)
	public List<StockDto> getApiExplorerList(String ...arg) throws IOException, ParseException{
		System.out.println("서비스인자="+arg[0]);
		
		return apiExplorer.getStock(arg);
	}
	

}

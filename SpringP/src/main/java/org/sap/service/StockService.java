package org.sap.service;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.sap.component.ApiExplorer;
import org.sap.component.JsoupComponent;
import org.sap.model.KospiStockDto;
import org.sap.model.StockDto;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockService {
	
	private final JsoupComponent jsoupComponent;
	
	private final ApiExplorer apiExplorer;
	
	public List<KospiStockDto> getKospiStockList(int p) {
		return jsoupComponent.getKospiStockList(p);
	}
	public List<StockDto> getApiExplorerList() throws IOException{
		return apiExplorer.getStock();
	}
}

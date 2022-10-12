package org.sap.service;

import java.util.List;
import org.sap.component.JsoupComponent;
import org.sap.model.KospiStockDto;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockService {
	
	private final JsoupComponent jsoupComponent;
	
	public List<KospiStockDto> getKospiStockList() {
		return jsoupComponent.getKospiStockList();
	}
	
}

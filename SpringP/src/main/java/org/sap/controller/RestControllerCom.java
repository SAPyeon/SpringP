package org.sap.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.sap.model.KospiStockDto;
import org.sap.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RestControllerCom {
	
	private final StockService stockService;
	
	@GetMapping("/StockList")
	public List<KospiStockDto> getKosPiStockList(HttpServletRequest request) {
		
		return stockService.getKospiStockList();
		
	}
}

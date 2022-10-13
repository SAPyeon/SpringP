package org.sap.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.sap.model.KospiStockDto;
import org.sap.model.StockDto;
import org.sap.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RestControllerCom {
	
	private final StockService stockService;
	
	@GetMapping("/StockList")
	public List<KospiStockDto> getKosPiStockList(HttpServletRequest request,@RequestParam int p) {
		return stockService.getKospiStockList(p);
		
	}
	
	@GetMapping("/Datadetail")
	public List<StockDto> getDataDetail() throws IOException{
		
        return stockService.getApiExplorerList();
	}
}

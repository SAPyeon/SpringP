package org.sap.controller;

import java.io.IOException;
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
		//System.out.println("주식서비스"+stockService.getKospiStockList(p));
		return stockService.getKospiStockList(p);
		
	}
	
	@GetMapping("/Datadetail")
	public List<StockDto> getDataDetail(HttpServletRequest request) throws IOException{
		//System.out.println("서비스="+stockService.getApiExplorerList());
		String codeName = request.getParameter("itmsNm");
		System.out.println("코드네임="+codeName);
		String[] arg = {codeName};
		System.out.println(arg[0]);
		return stockService.getApiExplorerList(arg);
	}
}

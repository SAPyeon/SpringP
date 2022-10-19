package org.sap.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.sap.model.KospiStockDto;
import org.sap.model.StockDto;
import org.sap.service.CommonService;
import org.sap.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RestControllerCom {
	
	private final CommonService commonService;
	
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
		String numOfRows = request.getParameter("numOfRows");
		String mrktCls = request.getParameter("mrktCls");
		String basDt = "";
		if(request.getParameter("basDt") != null) {
			basDt = request.getParameter("basDt");
			System.out.println("기준날짜="+basDt);
		}
		System.out.println("코드네임="+codeName);
		System.out.println("갯수="+numOfRows);
		System.out.println("시장구분="+mrktCls);
		String[] arg = {numOfRows,mrktCls,codeName,"",basDt};
		System.out.println(arg[0]);
		return stockService.getApiExplorerList(arg);
	}
	
	@GetMapping(value = "/DBUpdate")
	public void StockUpdate(HttpServletRequest request) throws IOException {
		String numOfRows = request.getParameter("numOfRows");
		System.out.println("업데이트수="+numOfRows);
		String[] arg = new String[5];
		arg[0] = numOfRows;
		arg[1] = "KOSPI";
		arg[2] = "";
		arg[3] = "";
		arg[4] = commonService.makeDateFormate();//날짜구하기 = api는 전날기준 업데이트
		List<StockDto>	stockUpdateList = stockService.getApiExplorerList(arg);
		stockService.insertStockDto(stockUpdateList);
	}
}

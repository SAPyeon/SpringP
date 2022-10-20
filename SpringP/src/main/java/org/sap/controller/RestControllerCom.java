package org.sap.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.sap.model.KospiStockDto;
import org.sap.model.StockDto;
import org.sap.service.CommonService;
import org.sap.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public List<StockDto> getDataDetail(HttpServletRequest request) throws IOException, ParseException{
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
		String[] arg = {numOfRows,mrktCls,codeName,basDt};
		return stockService.getApiExplorerList(arg);
	}
	
	@PostMapping(value = "/DBUpdate_Stock")
	public void StockUpdate(HttpServletRequest request,@RequestParam("numOfRows") String numOfRows) throws IOException, ParseException {
		//String numOfRows = request.getParameter("numOfRows");
		System.out.println("업데이트수="+numOfRows);
		String[] arg = new String[4];
		arg[0] = numOfRows;
		arg[1] = "KOSPI";
		arg[2] = "";
		arg[3] = commonService.makeDateFormate();//날짜구하기 = api는 전날기준 업데이트
		List<StockDto>	stockUpdateList = stockService.getApiExplorerList(arg);
		stockService.insertStockDto(stockUpdateList);
	}
	@PostMapping(value = "/DBUpdate_Com")
	public void ComUpdate() {
		
		Date now = new Date();
		SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
        String nowTime = SDF.format(now);
		System.out.println(nowTime);
		String path = "D:\\01-STUDY\\csvDownload\\data_4303_20221020.csv";
	    stockService.insertCompanyInfo(path);
	}
}

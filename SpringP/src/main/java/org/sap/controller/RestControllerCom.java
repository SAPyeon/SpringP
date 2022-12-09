package org.sap.controller;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.sap.component.DateFormatCom;
import org.sap.model.KospiStockDto;
import org.sap.model.StockDto;
import org.sap.service.BoardServiceImpl;
import org.sap.service.MemberService;
import org.sap.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	private final DateFormatCom dateFormatCom;

	private final StockService stockService;

	private final BoardServiceImpl boardService;
	
	
	// 주가리스트 데이터 크롤링
	@GetMapping("/StockList")
	public List<KospiStockDto> getKosPiStockList(HttpServletRequest request, @RequestParam int p) {
		// System.out.println("주식서비스"+stockService.getKospiStockList(p));
		return stockService.getKospiStockList(p);
	}
	
	// 상세페이지 데이터 크롤링
	@GetMapping("/StockDetail")
	public KospiStockDto getStockInfoInDetail( @RequestParam String code,  @RequestParam String codeName) {
		return stockService.getStockInfoInDetail(code, codeName);
	}
	
	// 공공데이터 api
	@GetMapping("/Datadetail")
	public List<StockDto> getDataDetail(HttpServletRequest request) throws IOException, ParseException {
		// System.out.println("서비스="+stockService.getApiExplorerList());
		String codeName = request.getParameter("itmsNm");
		String numOfRows = request.getParameter("numOfRows");
		String mrktCls = request.getParameter("mrktCls");
		String basDt = "";
		if (request.getParameter("basDt") != null) {
			basDt = request.getParameter("basDt");
			System.out.println("기준날짜=" + basDt);
		}
		System.out.println("코드네임=" + codeName);
		System.out.println("갯수=" + numOfRows);
		System.out.println("시장구분=" + mrktCls);
		String[] arg = { numOfRows, mrktCls, codeName, basDt };
		return stockService.getApiExplorerList(arg);
	}
	
	
	// 공공데이터 db저장
	@PostMapping(value = "/DBUpdate_Stock")
	public void StockUpdate(HttpServletRequest request, @RequestParam("numOfRows") String numOfRows)
			throws IOException, ParseException {
		// String numOfRows = request.getParameter("numOfRows");
		System.out.println("업데이트수=" + numOfRows);
		String[] arg = new String[4];
		arg[0] = numOfRows;
		arg[1] = "KOSPI";
		arg[2] = "";
		arg[3] = dateFormatCom.makeDateFormate();// 날짜구하기 = api는 전날기준 업데이트
		List<StockDto> stockUpdateList = stockService.getApiExplorerList(arg);
		boardService.insertStockDto(stockUpdateList);
	}

	// csv파일 db저장
	@PostMapping(value = "/DBUpdate_Com")
	public void ComUpdate() {
		Date now = new Date();
		SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
		String nowTime = SDF.format(now);
		File dir = new File("D:\\01-STUDY\\csvDownload");
		// 해당 경로에 파일이 많을 경우 이름으로 파일을 불러옴 
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File f, String name) {
				return name.contains(nowTime); // nowTime이라는 이름만 가진 파일만 가져옴
			}
		};
		File files[] = dir.listFiles(filter); // 해당 파일을 리스트화(배열)
		String path = files[0].getAbsolutePath(); // 첫번째 파일의 절대경로를 불러옴
		System.out.println("경로 = " + path);
		boardService.insertCompanyInfo(path); // 서비스에 해당경로 이동
	}

}

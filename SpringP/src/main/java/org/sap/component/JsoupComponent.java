package org.sap.component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.sap.model.KospiStockDto;
import org.springframework.stereotype.Component;

@Component
public class JsoupComponent {

	public List<KospiStockDto> getKospiStockList(int p) {
		// 크롤링 할 주소
		final String stockList = "https://finance.naver.com/sise/sise_market_sum.nhn?&page=" + p;
		Connection conn = Jsoup.connect(stockList); // Jsoup에 주소를 연결
		try {
			Document document = conn.get();// 연결한 주소의 데이터를 문서화(document)
			return getKospiStockList(document); // 데이터문서를 리스트화
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	// 데이터를 리스트로 만듬
	public List<KospiStockDto> getKospiStockList(Document document) {
		Elements kospiTable = document.select("table.type_2 tbody tr");
		List<KospiStockDto> list = new ArrayList<>();
		for (Element element : kospiTable) {
			if (element.attr("onmouseover").isEmpty()) { // 리스트 table의 종목이 있는 tr 이 비어있으면
				continue;
			}
			list.add(createKospiStockDto(element.select("td"))); // table.type_2 tbody tr td부분을 Dto에 넣어 리스트에 붙임
		}
		return list;
	}

	// 데이터를 Dto에 넣는 함수
	public KospiStockDto createKospiStockDto(Elements td) {
		KospiStockDto kospiStockDto = new KospiStockDto();
		Class<?> clazz = kospiStockDto.getClass();

		Field[] fields = clazz.getDeclaredFields();

		for (int i = 0; i < td.size(); i++) {
			String text;
			if (td.get(i).select(".center a").attr("href").isEmpty()) {
				text = td.get(i).text();
			} else {
				text = "https://finance.naver.com" + td.get(i).select(".center a").attr("href"); // href="/item/board.naver?code=005930"
			}
			fields[i].setAccessible(true); // private 변수에 대한 접근 허용
			try {
				fields[i].set(kospiStockDto, text);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return kospiStockDto;
	}

	// 상세페이지에서 불러올 데이터
	public KospiStockDto getStockInfoInDetail(String code, String codeName) {
		String URL = "https://finance.naver.com/item/sise.naver?code="+code;
		Connection conn = Jsoup.connect(URL); // Jsoup에 주소를 연결
		Document document;
		
		try {
			document = conn.get();//연결한 주소의 데이터를 문서화(document)
			return createStockDtoInDetail(document, codeName);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return null;
		
	}
	
	// 상세페이지에서 불러올 데이터 Dto에 입력
	public KospiStockDto createStockDtoInDetail(Document document, String codeName) {
		
		Elements kospiTable = document.select("table.type2 tbody tr"); // 기본 현재가 테이블
		
		Elements kospiTable2 = document.select("table.lwidth tbody tr"); // 외국인비율 테이블
		
		KospiStockDto kospiStockDto =  KospiStockDto.builder()
				.stockName(codeName)
				.price(kospiTable.select("td").get(0).text())
				.diffAmount( kospiTable.select("td").get(2).text())
				.dayRange(kospiTable.select("td").get(4).text())
				.parValue(kospiTable.select("td").get(10).text())
				.marketCap(kospiTable.select("td").get(20).text())
				.numberOfListedShares(kospiTable.select("td").get(22).text())
				.foreignOwnRate(kospiTable2.select("td").get(2).text())
				.turnover(kospiTable.select("td").get(6).text())
				.per(kospiTable.select("td").get(17).text())
				.build();
		
		return kospiStockDto;
	}
	
	
	
	
	
}

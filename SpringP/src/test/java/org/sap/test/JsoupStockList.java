package org.sap.test;

import java.io.IOException;
import java.text.ParseException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.sap.model.KospiStockDto;

public class JsoupStockList {
	
	@Test
	public static void main(String[] args) throws ParseException {
		String URL = "https://finance.naver.com/item/sise.naver?code=005930";
		Connection conn = Jsoup.connect(URL); // Jsoup에 주소를 연결
		try {
			Document document = conn.get();//연결한 주소의 데이터를 문서화(document)
			Elements kospiTable = document.select("table.type2 tbody tr");
			Elements kospiTable2 = document.select("table.lwidth tbody tr"); // 외국인비율 테이블
			
			KospiStockDto kospiStockDto =  KospiStockDto.builder()
					.stockName("삼성전자")
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
			
			System.out.println(kospiStockDto);
						
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}
	
}

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

	public List<KospiStockDto> getKospiStockList() {
		final String stockList = "https://finance.naver.com/sise/sise_market_sum.nhn?&page=1";
		Connection conn = Jsoup.connect(stockList);
		try {
			Document document = conn.get();
			return getKospiStockList(document);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<KospiStockDto> getKospiStockList(Document document) {
		Elements kospiTable = document.select("table.type_2 tbody tr");
		List<KospiStockDto> list = new ArrayList<>();
		for (Element element : kospiTable) {
			if (element.attr("onmouseover").isEmpty()) { //리스트 table의 종목이 있는 tr 이 비어있으면
				continue;
			}
			list.add(createKospiStockDto(element.select("td")));
		}
		
		return list;
	}

	public KospiStockDto createKospiStockDto(Elements td) {
		KospiStockDto kospiStockDto = new KospiStockDto();
		Class<?> clazz = kospiStockDto.getClass();
		
		Field[] fields = clazz.getDeclaredFields();
		
		for (int i = 0; i < td.size(); i++) {
			String text;
			if (td.get(i).select(".center a").attr("href").isEmpty()) {
				text = td.get(i).text();
			} else {
				text = "https://finance.naver.com" + td.get(i).select(".center a").attr("href");
				
			}
			fields[i].setAccessible(true); //private 변수에 대한 접근 허용
			try {
				fields[i].set(kospiStockDto, text);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return kospiStockDto;
	}

}

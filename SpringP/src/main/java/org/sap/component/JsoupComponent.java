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
		//크롤링 할 주소
		final String stockList = "https://finance.naver.com/sise/sise_market_sum.nhn?&page="+p;
		Connection conn = Jsoup.connect(stockList); // Jsoup에 주소를 연결
		try {
			Document document = conn.get();//연결한 주소의 데이터를 문서화(document)
			return getKospiStockList(document); //데이터문서를 리스트화
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	//데이터를 리스트로 만듬
	public List<KospiStockDto> getKospiStockList(Document document) {
		Elements kospiTable = document.select("table.type_2 tbody tr");
		List<KospiStockDto> list = new ArrayList<>();
		for (Element element : kospiTable) {
			if (element.attr("onmouseover").isEmpty()) { //리스트 table의 종목이 있는 tr 이 비어있으면
				continue;
			}
			list.add(createKospiStockDto(element.select("td"))); //table.type_2 tbody tr td부분을 Dto에 넣어 리스트에 붙임
		}
		return list;
	}
	//데이터를 Dto에 넣는 함수
	public KospiStockDto createKospiStockDto(Elements td) {
		KospiStockDto kospiStockDto = new KospiStockDto();
		Class<?> clazz = kospiStockDto.getClass();
		
		Field[] fields = clazz.getDeclaredFields();
		
		for (int i = 0; i < td.size(); i++) {
			String text;
			if (td.get(i).select(".center a").attr("href").isEmpty()) {
				text = td.get(i).text();
			} else {
				text = "https://finance.naver.com" + td.get(i).select(".center a").attr("href"); //href="/item/board.naver?code=005930"
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

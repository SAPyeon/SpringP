package org.sap.test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.sap.model.KospiStockDto;

public class JsoupStockList {
	
	@Test
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		String URL = "https://finance.naver.com/item/sise.naver?code=005930";
		Connection conn = Jsoup.connect(URL); // Jsoup에 주소를 연결
		try {
			Document document = conn.get();//연결한 주소의 데이터를 문서화(document)
			Elements kospiTable = document.select("table.type2 tbody tr");
			//System.out.println(kospiTable);
			List<KospiStockDto> list = new ArrayList<>();
			
			String str = kospiTable.select("td").text();
			String[] newStr = str.split("\\s+");
	        for (int i = 0; i < newStr.length; i++) {
	            System.out.println(newStr[i]);
	        }
			for (Element element : kospiTable) {
				
				if (element.attr("onmouseover").isEmpty()) { //리스트 table의 종목이 있는 tr 이 비어있으면
					continue; // 넘기고 진행
				}
				for(int i=0;i<kospiTable.size();i++) {
					//list.add(element.select("td").text()); //table.type_2 tbody tr td부분을 Dto에 넣어 리스트에 붙임	
				}
				
				
			}
			
			System.out.println(list);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
}

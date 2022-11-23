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
			Elements kospiTable2 = document.select("table.lwidth tbody tr"); // 외국인비율 테이블
			
			//System.out.println(kospiTable);
			List<KospiStockDto> list = new ArrayList<>();
			
			for(int i =0; i<kospiTable.size();i++) {
				//System.out.println(kospiTable.select("th").get(i).text() + "("+ i+")");
				//System.out.println(kospiTable.select("td").get(i).text());
					
			}
			System.out.println(kospiTable2.select("td").get(2).text());
			
			
			
			String str = kospiTable.select("td").text();
			
			String[] newStr = str.split("\\s+");
	        for (int i = 0; i < newStr.length; i++) {
	            //System.out.println(newStr[i]);
	        }
	        
			for (Element element : kospiTable) {
				
				Elements td = element.select("td");
					KospiStockDto kospiStockDto = new KospiStockDto();
										
					for (int i = 0; i < td.size(); i++) {
						kospiStockDto.setPrice(td.get(i).text());
						
						
					//System.out.println(kospiStockDto.getPrice());
					list.add(kospiStockDto); //table.type_2 tbody tr td부분을 Dto에 넣어 리스트에 붙임	
				}
				
			}
			
			//System.out.println(list);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
}

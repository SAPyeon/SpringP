package org.sap.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.sap.model.StockDto;
import org.springframework.stereotype.Component;

@Component
public class ApiExplorer {
	//api데이터를 불러오는 클래스
	public List<StockDto> getStock(String ...arg) throws IOException, java.text.ParseException {
		
		System.out.println("가변인자="+arg[0]);
		String[] arr = {"numOfRows","mrktCls","itmsNm","basDt","pageNo","likeItmsNm"}; //페이지결과수, 시장구분, 종목명, 기준일자 ,페이지번호, 문자열포함종목
		
		try {
			StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo"); /* URL, 공공데이터는 꼭 http로써야함, https(x) */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=tJ%2Bj4p2TFTI3O7G01PTEcxMl8nnyNWUbGexsWdVz2%2FrD5jeErUK1KYKAnHqb83MKYYR%2FcBltRoHetWusqEwyXw%3D%3D"); 
																															 
		urlBuilder.append("&" + URLEncoder.encode("resultType", "UTF-8") + "="
				+ URLEncoder.encode("json", "UTF-8")); /* XML 또는 JSON 소문자로*/
//		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" +
//				 URLEncoder.encode("500", "UTF-8")); /* 페이지 결과수*/
		
		for(int i=0; i<arg.length; i++) {
			urlBuilder.append("&" + URLEncoder.encode(arr[i], "UTF-8") + "=" +
					 URLEncoder.encode(arg[i], "UTF-8")); 
		}
		System.out.println(arr[0]+"="+arg[0]);
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode()); //200이 정상
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		
		String line;
		String result = "";

		while ((line = rd.readLine()) != null) {
			result = result.concat(line);
		}

		// JSON parser 만들어 문자열 데이터를 객체화한다.
		JSONParser parser = new JSONParser();
		JSONObject obj;
		
		obj = (JSONObject) parser.parse(result);
		//System.out.println(obj);
		//{"response":
		//			{"header":{"resultCode":"00","resultMsg":"NORMAL SERVICE."},
		//			 "body":{"pageNo":1,"totalCount":1753323,"numOfRows":10,
		//					"items":{"item":[{"srtnCd":"900110",...형식
			
		JSONObject response = (JSONObject) obj.get("response");
		JSONObject body = (JSONObject) response.get("body");
		JSONObject items = (JSONObject) body.get("items");
			
		JSONArray parse_listArr = (JSONArray)items.get("item");
		//System.out.println(parse_listArr);
		if(parse_listArr.size() == 0) { // 만약 데이터값이 없을경우(기준날짜가 휴장인경우)
			arg[3] = DateFormatCom.getSpecifiedDayBefore(arg[3]);
			//arg[3] = CommonService.getSpecifiedDayBefore(arg[3]);
			System.out.println("변경된기준날짜="+arg[3]);
			getStock(arg);  //error 주의(날짜 변경안될 시 반복실행될 수 있음)
		}
		
		rd.close();
		conn.disconnect();
		
		return DTOSetList(parse_listArr); //return을 해줘야 데이터가 반환됨
			
	} catch (ParseException e) {
			e.printStackTrace();
	}
		return null;
	}
	
	
	//데이터를 DTO에 set하는 클래스
	public List<StockDto> DTOSetList(JSONArray parse_listArr){
		List<StockDto> list = new ArrayList<>();
		
		for (int i = 0; i < parse_listArr.size(); i++) {
				JSONObject stock = (JSONObject) parse_listArr.get(i);
				
				String itmsNm = (String) stock.get("itmsNm"); // 종목명
				String mrktCtg = (String) stock.get("mrktCtg"); // 시장구분
				String clpr = (String) stock.get("clpr"); // 종가
				String fltRt = (String) stock.get("fltRt"); // 등락률
				String mkp = (String) stock.get("mkp"); // 시가
				String basDt = (String) stock.get("basDt"); // 기준일자
				String hipr = (String) stock.get("hipr"); // 고가
				String lopr = (String) stock.get("lopr"); // 저가
				String trqu = (String) stock.get("trqu"); // 거래량
				String mrktTotAmt = (String) stock.get("mrktTotAmt"); // 시가총액
				String srtnCd = (String) stock.get("srtnCd"); // 종목코드
				
				//lombok builder사용하여 dto에 담기
				StockDto std = StockDto.builder()
				.itmsNm(itmsNm)
				.mrktCtg(mrktCtg)
				.clpr(clpr)
				.fltRt(fltRt)
				.mkp(mkp)
				.basDt(basDt)
				.hipr(hipr)
				.lopr(lopr)
				.trqu(trqu)
				.mrktTotAmt(mrktTotAmt)
				.srtnCd(srtnCd)
				.build();//마지막에 써주기
				
				list.add(std);
		}System.out.println("StockDto 리스트 = "+list);
		return list;// 마지막에 리스트를 return 필수	
	}
	
}
package org.sap.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.sap.model.StockDto;
import org.springframework.stereotype.Component;

@Component
public class ApiExplorer {

	public List<StockDto> getStock() throws IOException {
		// TODO Auto-generated method stub
		try {
			StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=tJ%2Bj4p2TFTI3O7G01PTEcxMl8nnyNWUbGexsWdVz2%2FrD5jeErUK1KYKAnHqb83MKYYR%2FcBltRoHetWusqEwyXw%3D%3D"); /*
																															 * Service
																															 * Key
																															 */
		urlBuilder.append("&" + URLEncoder.encode("resultType", "UTF-8") + "="
				+ URLEncoder.encode("json", "UTF-8")); /* XML 또는 JSON */
		// urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" +
		// URLEncoder.encode("5", "UTF-8")); /* page 출력 수 */
		// urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" +
		// URLEncoder.encode("1", "UTF-8")); /* 페이지 번호*/
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		String result = "";

		while ((line = rd.readLine()) != null) {
			result = result.concat(line);
			//System.out.println("result="+result);
		}

		// JSON parser 만들어 문자열 데이터를 객체화한다.
		JSONParser parser = new JSONParser();
		JSONObject obj;
		
			obj = (JSONObject) parser.parse(result);
			System.out.println(obj); //이상없음
			// list 아래가 배열형태로
			// {"list" : [ {"returnType":"json","clearDate":"--",.......} ]
			JSONArray parse_listArr = (JSONArray) obj.get("items");
			System.out.println(parse_listArr); //문제 = null???
//			for (int i = 0; i < parse_listArr.size(); i++) {
//				JSONObject stock = (JSONObject) parse_listArr.get(i);
//				String itmsNm = (String) stock.get("itmsNm"); // 종목명
//				String mrktCtg = (String) stock.get("mrktCtg"); // 시장구분
//				String clpr = (String) stock.get("clpr"); // 종가
//				String fltRt = (String) stock.get("fltRt"); // 등락률
//				String mkp = (String) stock.get("mkp"); // 시가
//				String basDt = (String) stock.get("basDt"); // 기준일자
//				String hipr = (String) stock.get("hipr"); // 고가
//				String lopr = (String) stock.get("lopr"); // 저가
//				String trqu = (String) stock.get("trqu"); // 거래량
//				String mrktTotAmt = (String) stock.get("mrktTotAmt"); // 시가총액
//				String srtnCd = (String) stock.get("srtnCd"); // 종목코드
//			}

			rd.close();
			conn.disconnect();

			System.out.println(sb.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
package org.sap.component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.sap.model.StockDto;
import org.springframework.stereotype.Component;

@Component
public class CSVReader {
	public List<StockDto> readCSV(String path) {
        List<List<String>> csvList = new ArrayList<List<String>>();
        File csv = new File(path);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csv));
            Charset.forName("UTF-8"); //한글깨짐 방지 : 파일을 메모장으로 연 뒤 다른이름으로 저장 -> 인코딩UTF-8로 저장
            String line = "";
            while ((line = br.readLine()) != null) { // readLine()은 파일에서 개행된 한 줄의 데이터를 읽어온다.
               List<String> aLine = new ArrayList<String>();
               String[] lineArr = line.split(","); // 파일의 한 줄을 ,로 나누어 배열에 저장 후 리스트로 변환한다.
               aLine = Arrays.asList(lineArr);
               csvList.add(aLine);
               
            } 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) { 
                    br.close(); // 사용 후 BufferedReader를 닫아준다.
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        //System.out.println(DTOSetList(csvList));
        return DTOSetList(csvList);
    }
	
	//DTO에 담기
	public List<StockDto> DTOSetList(List<List<String>> csvList){
		List<StockDto> list = new ArrayList<>();
		
		SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
		Date now = new Date();
        String nowTime = SDF.format(now);
		
		for(int i =1; i<csvList.size();i++) {
			List<String> stock = csvList.get(i);
			//System.out.println(stock);
			
			String srtnCd = (String) stock.get(0); // 종목코드
			String itmsNm = (String) stock.get(1); // 종목명
			String mrktCtg = (String) stock.get(2); // 시장구분
			String clpr = (String) stock.get(4); // 종가
			String fltRt = (String) stock.get(6); // 등락률
			String mkp = (String) stock.get(7); // 시가
			String basDt = nowTime; // 기준일자
			String hipr = (String) stock.get(8); // 고가
			String lopr = (String) stock.get(9); // 저가
			String trqu = (String) stock.get(10); // 거래량
			String mrktTotAmt = (String) stock.get(12); // 시가총액
			
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
		}
		//System.out.println(list);
		
		return list;
	}
}

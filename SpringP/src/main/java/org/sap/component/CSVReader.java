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

import org.sap.model.CompanyInfoDto;
import org.springframework.stereotype.Component;

@Component
public class CSVReader {
	public List<CompanyInfoDto> readCSV(String path) {
		List<List<String>> csvList = new ArrayList<List<String>>();
        File csv = new File(path);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csv));
            Charset.forName("UTF-8"); //한글깨짐 방지 : 파일을 메모장으로 연 뒤 다른이름으로 저장 -> 인코딩UTF-8로 저장
            String line = "";
            //System.out.println(br.readLine());
            int num = 0;
            while ((line = br.readLine()) != null) { // readLine()은 파일에서 개행된 한 줄의 데이터를 읽어온다.
               List<String> aLine = new ArrayList<String>();
               String[] lineArr = line.replace(" ", "").split("\""); // 파일의 한 줄을 ,로 나누어 배열에 저장 후 리스트로 변환한다.               
               if (num != 0) {
   				for (int i = 0; i < lineArr.length; i++) {
   					if (lineArr[i].compareTo(",") == 0) {
   						lineArr[i] = ""; // , 단위로 배열에 저장
   					}
   				}
   				String temp_string = "";
   				for (int i = 0; i < lineArr.length; i++) {

   					if (i != lineArr.length - 1) {
   						temp_string += lineArr[i] + ";"; //마지막을 제외한 배열 뒤에 ;을 붙임 
   					} else {
   						temp_string += lineArr[i]; //마지막은 그냥 배열 출력
   					}
   				}
   				 //System.out.println(temp_string);

   				String Second_Cut_String[] = temp_string.replace(";;", ";").split(";"); // ;;은 ;로바꾸고 ;단위로 잘라 배열에 저장
   				
   				String[] arrList = new String[13]; // 길이가 13개인 배열(CompanyInfoDTO 변수갯수)
   				for(int i = 0; i<12;i++) {
   					arrList[i] = Second_Cut_String[i+1]; // 처음에 ;로 시작했으므로 두번째부터 순차적으로 배열에 저장
   				}
   				aLine = Arrays.asList(arrList); //배열을 리스트로 저장
                csvList.add(aLine); //리스트에 추가
                //System.out.println(aLine);
   			}
   			num++;
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
        //System.out.println(csvList.get(1).get(1));
        //System.out.println("리스트 = "+DTOSetList(csvList));
        //System.out.println(DTOSetList(csvList).get(0).getNameKor());
        return DTOSetList(csvList);
	}
	
	//DTO에 담기
	public List<CompanyInfoDto> DTOSetList(List<List<String>> csvList){
		List<CompanyInfoDto> list = new ArrayList<>();
		
		SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
		Date now = new Date();
        String nowTime = SDF.format(now);
		
		for(int i =1; i<csvList.size();i++) {
			List<String> stock = csvList.get(i);
			//System.out.println("stock = "+stock);
			
			String StaticCode  = (String) stock.get(0); //표준코드 
			String code  = (String) stock.get(1); // 단축코드
			String nameKor  = (String) stock.get(2); // 한글종목명
			String itmsNm  = (String) stock.get(3);// 한글 종목약명
			String nameEng  = (String) stock.get(4); //영문종목명
			String date  = (String) stock.get(5); // 상장일
			String mrktCtg  = (String) stock.get(6);; // 시장구분
			String stockPart = (String) stock.get(7); // 증권구분
			String emp = "";  // 소속부
			String stockCat = (String) stock.get(9); // 주식종류
			String price = (String)stock.get(10); // 액면가
			String mrktTotAmt = (String)stock.get(11); //상장주식수
			String regdate = nowTime; // 기준일자(DB등록일자)
			
			//lombok builder사용하여 dto에 담기
			CompanyInfoDto cid = CompanyInfoDto.builder()
			.StaticCode(StaticCode)
			.code(code)
			.nameKor(nameKor)
			.itmsNm(itmsNm)
			.nameEng(nameEng)
			.date(date)
			.mrktCtg(mrktCtg)
			.stockPart(stockPart)
			.emp(emp)
			.stockCat(stockCat)
			.price(price)
			.mrktTotAmt(mrktTotAmt)
			.regdate(regdate)
			.build();//마지막에 써주기
			
			list.add(cid);
		}
		//System.out.println("CompanyInfoDto리스트 = "+list);
		
		return list;
	}
}

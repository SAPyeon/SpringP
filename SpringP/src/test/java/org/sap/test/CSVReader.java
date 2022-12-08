package org.sap.test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.sap.model.CompanyInfoDto;

//한글인코딩x 강제로해야하나??

public class CSVReader {
	@Test
	public void main() throws UnsupportedEncodingException, FileNotFoundException {
		//CSVReader csvReader = new CSVReader();
		String path = "D:\\01-STUDY\\csvDownload\\data_3604_20221121.csv";
		//System.out.println(CSVReader.readCSV(path));
		CSVReader.readCSV(path);
		//readCSV(path);
	}
	public static List<CompanyInfoDto> readCSV(String path) throws UnsupportedEncodingException, FileNotFoundException {
        List<List<String>> csvList = new ArrayList<List<String>>();
        FileInputStream csv=new FileInputStream(path);
        InputStreamReader reader=new InputStreamReader(csv,"UTF-8");
        //File csv = new File(path);
        BufferedReader br = null;
        try {
            br = new BufferedReader(reader);
            Charset.forName("UTF-8"); //한글깨짐 방지 : 파일을 메모장으로 연 뒤 다른이름으로 저장 -> 인코딩UTF-8로 저장
            String line = "";
            //System.out.println(br.readLine());
            int num = 0;
            while ((line = br.readLine()) != null) { // readLine()은 파일에서 개행된 한 줄의 데이터를 읽어온다.
               List<String> aLine = new ArrayList<String>();
               String[] lineArr = line.replace(" ", "").split("\""); // 파일의 한 줄을 ,로 나누어 배열에 저장 후 리스트로 변환한다.               
               if (num != 0) {
   				for (int i = 0; i < lineArr.length; i++) {
   					if (lineArr[i].compareTo(",") == 0) { // , 가 존재하면 (.compareTo() 는 있으면 0 없으면 문자열을 서로 비교)
   						lineArr[i] = ""; // 지워라
   					}
   				}
   				String temp_string = "";
   				for (int i = 0; i < lineArr.length; i++) {
   					if (i != lineArr.length - 1) { // 마지막 글자가 아니면 
   						temp_string += lineArr[i] + ";"; // ; 를 붙여 구분표시
   					} else {
   						temp_string += lineArr[i];
   					}
   				}
   				System.out.println("temp_string = "+temp_string);
   				// temp_string = ;KR7000640003;;000640;;동아쏘시오홀딩스보통주;;동아쏘시오홀딩스;;Dong-ASocioHoldings;;1970/02/10;;KOSPI;;주권;,,;보통주;;5000;;6348913
   				// 데이터가 한칸씩 미뤄지는 현상이 발생
    			//	따라서 없는데이터로 인한 문자 ;; 를 ;로 바꾸고 잘라서 다시 데이터에 넣음
   				String Second_Cut_String[] = temp_string.replace(";;", ";").split(";");
   				
   				String[] arrList = new String[13];
   				for(int i = 0; i<12;i++) {
   					arrList[i] = Second_Cut_String[i+1];
   				}
   				aLine = Arrays.asList(arrList);
                csvList.add(aLine);
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
       // System.out.println(DTOSetList(csvList).get(0).getNameKor());
        
        
        
        
        return DTOSetList(csvList);
    }
	
	//DTO에 담기
	public static List<CompanyInfoDto> DTOSetList(List<List<String>> csvList){
		List<CompanyInfoDto> list = new ArrayList<>();
		
		SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
		Date now = new Date();
        String nowTime = SDF.format(now);
		
		for(int i =0; i<csvList.size();i++) {
			List<String> stock = csvList.get(i);
			//System.out.println("stock 표준코드 = "+(String) stock.get(0));
			
			String StaticCode  = (String) stock.get(0); //표준코드 
			
			String code  = (String) stock.get(1); // 단축코드
			String nameKor  = (String) stock.get(2); // 한글종목명
			String itmsNm  = (String) stock.get(3);// 한글 종목약명
			String nameEng  = (String) stock.get(4); //영문종목명
			String date  = (String) stock.get(5); // 상장일
			String mrktCtg  = (String) stock.get(6);; // 시장구분
			String stockPart = (String) stock.get(7); // 증권구분
			String emp = (String) stock.get(8);  // 소속부
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
			//System.out.println(cid.getDate());
			//System.out.println("cid = "+ cid);
		}
		//System.out.println("CompanyInfoDto리스트 = "+list);
		return list;
	}
}

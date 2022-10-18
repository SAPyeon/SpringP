package org.sap.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "D:\\01-STUDY\\csvDownload\\data_2145_20221018.csv";
		CSVReader csvReader = new CSVReader();
	    csvReader.readCSV(path);
	}
	public List<List<String>> readCSV(String path) {
        List<List<String>> csvList = new ArrayList<List<String>>();
        File csv = new File(path);
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(csv));
            Charset.forName("UTF-8"); //한글깨짐 방지 : 파일을 메모장으로 연 뒤 다른이름으로 저장 -> 인코딩UTF-8로 저장
            String line = "";
            //System.out.println(br.readLine());
            while ((line = br.readLine()) != null) { // readLine()은 파일에서 개행된 한 줄의 데이터를 읽어온다.
                List<String> aLine = new ArrayList<String>();
                
                String[] lineArr = line.split(","); // 파일의 한 줄을 ,로 나누어 배열에 저장 후 리스트로 변환한다.
                aLine = Arrays.asList(lineArr);
                csvList.add(aLine);
                System.out.println(aLine);
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
        return csvList;
    }
}

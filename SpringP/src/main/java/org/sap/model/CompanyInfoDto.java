package org.sap.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data //getter setter만들어줌
@NoArgsConstructor //필드에 있는 부분 모든 생성자를 만들어줌
@AllArgsConstructor //기본생성자를 만들어줌
@Builder //builder를 쓰려면 위에 두@가 필수로 만들어져야한다.
@ToString
public class CompanyInfoDto {
	
	private String StaticCode; //표준코드 
	private String code; // 단축코드
	private String nameKor; // 한글종목명
	private String itmsNm;// 한글 종목약명
	private String nameEng; //영문종목명
	private String date; // 상장일
	private String mrktCtg; // 시장구분
	private String stockPart; // 증권구분
	private String emp; // 소속부
	private String stockCat; // 주식종류
	private String price; // 액면가
	private String mrktTotAmt; //상장주식수
	private String regdate; //기준일자
	
	
	
	
	
}

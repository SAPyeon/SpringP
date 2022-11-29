package org.sap.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class LikeDto {
	private String srtnCd; 		// 아이디
	private String id; 			// 종목코드
	private String itmsNm;		// 종목이름
	private String diffAmount;	// 전일비
	private String dayRange;	// 등락률
	private String parValue;	// 액면가
	private String marketCap;	//시가총액
	private String numberOfListedShares; //상장주식수
	private String foreignOwnRate;	//외국인비율
	private String turnover;	//거래량
	private String per;			// per
	
}

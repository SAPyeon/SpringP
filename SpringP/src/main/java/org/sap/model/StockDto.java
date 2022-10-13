package org.sap.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockDto {
	private String itmsNm; 		//종목명
	private String mrktCtg; 	//시장구분
	private String clpr; 		//종가
	private String fltRt; 		//등락률
	private String mkp;			//시가
	private String basDt;		//기준일자
	private String hipr; 		//고가
	private String lopr;		//저가
	private String trqu;	 	//거래량
	private String mrktTotAmt;	//시가총액
	private String srtnCd; 		//종목코드
}

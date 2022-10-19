package org.sap.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter setter만들어줌
@NoArgsConstructor //필드에 있는 부분 모든 생성자를 만들어줌
@AllArgsConstructor //기본생성자를 만들어줌
@Builder //builder를 쓰려면 위에 두@가 필수로 만들어져야한다.
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

package org.sap.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class LikeDto {
	private String srtnCd;
	private String id;
	private String itmsNm;
	private String diffAmount;
	private String dayRange;
	private String parValue;
	private String marketCap;
	private String numberOfListedShares;
	private String foreignOwnRate;
	private String turnover;
	private String per;
	
}

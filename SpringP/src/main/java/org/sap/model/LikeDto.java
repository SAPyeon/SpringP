package org.sap.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class LikeDto {
	private String srtnCd;
	private String id;
	private String itmsNm;
	private StockDto stockdto;
}

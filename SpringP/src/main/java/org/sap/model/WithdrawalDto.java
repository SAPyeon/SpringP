package org.sap.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class WithdrawalDto {
	private int wno;
	private String id;
	private String reason;
	private String regdate;
}

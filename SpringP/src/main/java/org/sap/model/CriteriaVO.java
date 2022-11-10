package org.sap.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CriteriaVO {
	private int pageNum;
	private int amount;
	private String search;
	
	public CriteriaVO() {
		pageNum = 1;
		amount = 10;
		search = "";
	}
	
	public CriteriaVO(String search) {
		this();
		this.search = search;
	}
	
	public CriteriaVO(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	
}

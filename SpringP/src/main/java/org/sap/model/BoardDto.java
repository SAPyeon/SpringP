package org.sap.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class BoardDto {
	private String bno;
	private String title;
	private String content;
	private String name;
	private String id;
	private int cnt;
	private String regdate;
	private boolean notice;
}

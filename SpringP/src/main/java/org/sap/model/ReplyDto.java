package org.sap.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReplyDto {
	private String rno;
	private String reply;
	private String name;
	private int declaration;
	private String regdate;
	private String bno;
	private String id;
}

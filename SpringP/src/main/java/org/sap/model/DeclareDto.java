package org.sap.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class DeclareDto {
	String id;
	String rno;
	String bno;
	String reason;
	String regdate;
	ReplyDto reply;
}

package org.sap.model;

import lombok.Data;

@Data
public class MemberVO {
	private String id;
	private String password;
	private String name;
	private int point;
}

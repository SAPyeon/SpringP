package org.sap.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MemberDto {
	private String id;
	private String password;
	private String name;
	private String phone;
	private int point;
	private boolean authority;
	private String authno;
}

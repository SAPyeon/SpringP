package org.sap.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ChangeAuthDto {
	private String id; 
	private String beforeAuthno;
	private String afterAuthno;
	private String reasonChange;
	private String regdate; 
	private boolean allow;
}

package org.sap.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ImageDto {
	private String uuid;
	private String uploadPath;
	private String fileName;
	private String fullPath;
	private boolean image;
	private int pno;
}

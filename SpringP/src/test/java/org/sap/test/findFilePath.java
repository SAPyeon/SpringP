package org.sap.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class findFilePath {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		Date now = new Date();
		SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
		String nowTime = SDF.format(now);
		
		File dir = new File("D:\\01-STUDY\\csvDownload");
		FilenameFilter filter = new FilenameFilter() {
		    public boolean accept(File f, String name) {
		        return name.contains(nowTime);
		    }
		};
		File files[] = dir.listFiles(filter);
		String path = files[0].getAbsolutePath();
		System.out.println("경로 = "+path);
		for (int i = 0; i < files.length; i++) {
		    System.out.println("file: " + files[i]);
		}
		FileInputStream input=new FileInputStream(path);
        InputStreamReader reader=new InputStreamReader(input,"UTF-8");
        BufferedReader in=new BufferedReader(reader);
        System.out.println(in);
	
	}
}

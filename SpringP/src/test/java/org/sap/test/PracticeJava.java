package org.sap.test;

public class PracticeJava {
	public static void main(String[] args) {
//		List<String> inList = new ArrayList<String>();
//		inList.add("moon");
//		inList.add("sun");
//		inList.add("water");
//		inList.add("hoho");
//		System.out.println(inList);
		String a="";
		String id = "K+alsiefjjsafs";
		System.out.println(id.substring(0, 2));
		System.out.println(id.substring(0, 2).equals("K+"));
		
		if(id.substring(0, 2).equals("K+")) {
			a = "This is KaKaoLogin";
		}
		
		System.out.println(a);
	}
}

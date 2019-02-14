package com.gzz;

public class Test {

	public static void main(String[] args) {
		String path="\\//<1212><12121>";
		
		path = path.replaceAll("<", "").replaceAll(">", "").replaceAll("/", "-").replaceAll("\\\\", "-");
		System.out.println(path);

	}

}

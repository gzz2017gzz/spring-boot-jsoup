package com.gzz;

public class T {

	public static void main(String[] args) {
		String str="[YouMi] 2019.12.24 Vol.392 朱可_儿Flower";
 
		System.out.println( str.split(" ")[2].replaceAll("\\D", "") );

	}

}

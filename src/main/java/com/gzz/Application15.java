package com.gzz;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

//https://www.kaka1234.com/HTM/YouMi/2020/0211/48216.html
public class Application15 {

	private static String rootpath = "e:/youme/";
	private static String host = "http://www.quantuwang.co/";
	private static int num=1;
	private static DecimalFormat df = new DecimalFormat("00");

	public static void main(String[] args) throws Exception {
		String base = "http://d.quantuwang.co/2019/12/17/18971/";
		for (int i = 1; i < 50; i++) {
			String path = rootpath + "Vol.388 果儿Victoria/" + df.format(num) + ".jpg";
			TimeUnit.SECONDS.sleep(5);			Utils.downPic( base+i+".jpg", path, host);
			num++;
		}

	}
}
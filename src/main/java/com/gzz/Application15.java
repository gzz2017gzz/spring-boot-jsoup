package com.gzz;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

//https://www.kaka1234.com/HTM/YouMi/2020/0211/48216.html
public class Application15 {

	private static String rootpath = "e:/bbb/";
	private static String host = "http://www.quantuwang.co/m/df007ac28b73b1ec.html";
	private static int num = 1;
	private static DecimalFormat df = new DecimalFormat("00");

	public static void main(String[] args) throws Exception {
		String base = "http://d.quantuwang.co/2014/11/12/603/";
		for (int i = 1; i < 68; i++) {
			String path = rootpath + "[美媛馆MYGIRL] 2014.11.12 Vol.076 刘飞儿/" + df.format(num) + ".jpg";
			TimeUnit.SECONDS.sleep(5);
			Utils.downPic(base + i + ".jpg", path, host);
			num++;
		}

	}
}
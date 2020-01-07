package com.gzz;
import java.text.DecimalFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
//https://www.lsmpx.com
public class Application7 {
	private static Log logger = LogFactory.getLog(Application7.class);

	private static String rootpath = "D:/meiyuan/";
	private static String host = "https://www.lsmpx.com/";
	private static int num;
	private static DecimalFormat df = new DecimalFormat("00");

	public static void main(String[] args) throws Exception {
		for (int j = 1; j < 2; j++) {
			Elements elements = Jsoup.connect("https://www.lsmpx.com/forum-55-" + j + ".html").get().select(".group .photo a");
			for (Element element : elements) {
				String page1 = host + element.attr("href");
				num = 0;
				for (int i = 1; i < 6; i++) {
					String replace = page1.replace("1-1.html", i + "-1.html");
					logger.info(replace);
					Elements eles = Jsoup.connect(replace).get().select("#thread-pic img");
					for (Element ele : eles) {
						String[] split = ele.attr("alt").split(" - ");
						String path = rootpath+split[0].replace("...", "")+"/"+df.format(num)+".jpg";
						Utils.downPic(ele.attr("src"), path);
						num++;
					}
				}
			}
		}
	}
}
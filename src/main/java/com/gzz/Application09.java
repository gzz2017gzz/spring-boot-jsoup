package com.gzz;

import java.text.DecimalFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//https://www.lesmao.co/
public class Application09 {
	private static Log logger = LogFactory.getLog(Application09.class);
	private static String rootpath = "e:/YALAYI/";
	private static String host = "https://www.lesmao.co/";
	private static int num;
	private static DecimalFormat df = new DecimalFormat("00");

	public static void main(String[] args) throws Exception {
		for (int j = 1; j <19; j++) {
			Document document = Jsoup.connect("https://www.lesmao.co/forum-136-" + j + ".html").get();
//			logger.info(document);
			Elements select = document.select(".photo a");
			for (Element element : select) {
//				logger.info(element.attr("href"));
				String setName = element.childNode(1).attr("alt").replace(" ", "");
				logger.info(setName);
				num = 1;
				for (int i = 1; i < 6; i++) {
					Elements select2 = Jsoup.connect(host + element.attr("href").replace("1.html", i + ".html")).get().select(".adw img");
					for (Element element2 : select2) {
//						logger.info(element2.attr("src"));
						String path = rootpath + setName + "/" + df.format(num) + ".jpg";
						Utils.downPic(element2.attr("src"), path, host);
						num++;
					}
				}
			}
		}
	}
}
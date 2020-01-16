package com.gzz;

import java.text.DecimalFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//https://www.lesmao.co/
public class Application9 {
	private static Log logger = LogFactory.getLog(Application9.class);
	private static String rootpath = "D:/222/";
	private static String host = "https://www.lesmao.co/";
	private static int num;
	private static DecimalFormat df = new DecimalFormat("00");

	public static void main(String[] args) throws Exception {
		for (int j = 1; j < 62; j++) {
			Document document = Jsoup.connect("https://www.lesmao.co/forum-99-" + j + ".html").get();
//			logger.info(document);
			Elements select = document.select(".photo a");
			for (Element element : select) {
//				logger.info(element.attr("href"));
				logger.info(element.childNode(1).attr("alt"));
				num = 1;
				for (int i = 1; i < 6; i++) {
					Elements select2 = Jsoup.connect(host + element.attr("href").replace("1.html", i + ".html")).get().select(".adw img");
					for (Element element2 : select2) {
//						logger.info(element2.attr("src"));
						String path = rootpath + element.childNode(1).attr("alt") + "/" + df.format(num) + ".jpg";
						Utils.downPic(element2.attr("src"), path, host);
						num++;
					}
				}
			}
		}
	}
}
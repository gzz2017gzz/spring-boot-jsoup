package com.gzz;

import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Application24 {
	private static Log logger = LogFactory.getLog(Application24.class);
	private static String root = "E:/bbb/";
	private static DecimalFormat df = new DecimalFormat("00");

	public static void main(String[] args) throws IOException {
		String base = "http://www.tuyim.net/thread-1119-1-16.html";
		Document document = Jsoup.connect(base).get();
		Elements elements = document.select(".mbn.savephotop img");
//		logger.info(elements);
		int num = 1;
		for (Element element : elements) {
			logger.info(element.attr("data-original"));
			String path = root + "123/" + df.format(num) + ".jpg";
			logger.info(path);
			Utils.downPic(element.attr("data-original"), path, "base");
			num++;
		}
	}
}

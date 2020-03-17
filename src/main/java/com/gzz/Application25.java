package com.gzz;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Application25 {
	private static Log logger = LogFactory.getLog(Application25.class);
	private static String root = "E:/aaaa/";
	private static DecimalFormat df = new DecimalFormat("00");

	public static void main(String[] args) throws IOException {
		for (int i = 1; i < 26; i++) {
			Document document = Jsoup.connect("http://www.tuyim.net/forum-35-" + i + ".html").get();
			Elements elements = document.select("div.bus_vtem a.preview");
			for (Element element : elements) {
				if (count(element.attr("title")) < 369) {
					String base = element.attr("href");
					logger.info(element.attr("title"));
					Elements eles = Jsoup.connect(base).get().select(".mbn.savephotop img");
//				logger.info(eles);
					int num = 1;
					for (Element ele : eles) {
						String path = root + element.attr("title") + "/" + df.format(num) + ".jpg";
						Utils.downPic(ele.attr("data-original"), path, base);
						num++;
					}
				}
			}
		}
	}

	public static int count(String title) {
		Matcher matcher = Pattern.compile("(.*(?i)OL.)(\\d{3})(\\s+.*)").matcher(title);
		// 因此可以改写为
		if (matcher.matches()) {
//			logger.info("[num=" + matcher.group(2));
			return new Integer(matcher.group(2));
		}
		return -1;
	}

}

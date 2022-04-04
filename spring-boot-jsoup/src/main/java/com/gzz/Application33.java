package com.gzz;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application33 {
	private static String root = "E:/UXING优星馆/";
	private static DecimalFormat df = new DecimalFormat("00");

	public static void main(String[] args) throws Exception {
		String index = "http://www.tuyi8.vip/forum-40-";
		for (int i = 1; i <= 3; i++) {
			 Jsoup.connect(index + i+".html").get();
//			log.info("{}", document);
			Elements elements = Jsoup.connect(index + i+".html").get().select("#waterfall .bus_vtem a[class=MMPic]");
//			log.info("{}", elements);
			for (Element element : elements) {
 				TimeUnit.MILLISECONDS.sleep(600);
				String title = element.attr("title").replace("...", "").replace(" ", "");
				String base = element.attr("href");
				 count(element.attr("title"));
				log.info("i={},title={}",i, title,i);
//				if (count < 31) {
					Elements eles = Jsoup.connect(base).get().select(".mbn.savephotop img");
//					log.info("{}", eles);
					int num = 1;
					for (Element ele : eles) {
						String path = root + title + "/" + df.format(num) + ".jpg";
						Utils.downPic(ele.attr("data-original"), path, base);
						num++;
					}
				}
			}
		}
//	}

	public static int count(String title) {
		Matcher matcher = Pattern.compile("(.*(?i)OL.)(\\d{3})(\\s+.*)").matcher(title);
		if (matcher.matches()) {
			log.info("{}", matcher.group(2));
			return Integer.parseInt(matcher.group(2));
		}
		return 164;
	}
}

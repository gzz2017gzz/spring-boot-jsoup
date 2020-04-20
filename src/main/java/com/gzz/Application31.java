package com.gzz;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application31 {
	private static String root = "E:/tns/";
	private static DecimalFormat df = new DecimalFormat("00");

	public static void main(String[] args) throws Exception {
		String index = "http://www.tuyi8.vip/forum-16-";
		for (int i = 2; i <= 14; i++) {
//			Document document = Jsoup.connect(index + i+".html").get();
//			log.info("{}", elements);
			Elements elements = Jsoup.connect(index + i+".html").get().select("#waterfall .bus_vtem a[class=preview]");
			for (Element element : elements) {
				String title = element.attr("title").replace("...", "").replace(" ", "");
				String base = element.attr("href");
				int count = count(element.attr("title"));
				log.info("i={},title={}",i, title,i);
//				if (count < 107) {
					Elements eles = Jsoup.connect(base).get().select(".mbn.savephotop img");
//					log.info("{}", eles);
					int num = 1;
					for (Element ele : eles) {
						String path = root + title + "/" + df.format(num) + ".jpg";
						Utils.downPic(ele.attr("data-original"), path, base);
						num++;
					}
//				}
			}
		}
	}

	public static int count(String title) {
		Matcher matcher = Pattern.compile("(.*(?i)OL.)(\\d{3})(\\s+.*)").matcher(title);
		if (matcher.matches()) {
//			log.info("{}", matcher.group(2));
			return new Integer(matcher.group(2));
		}
		return 164;
	}
}

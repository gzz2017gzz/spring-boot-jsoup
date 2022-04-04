package com.gzz;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lombok.extern.slf4j.Slf4j;

//https://www.kaka1234.com/HTM/YouMi/2020/0211/48216.html
@Slf4j
public class Application14 {

	private static String rootpath = "e:/youme/";
	private static String host = "https://www.xgmn.org";
	private static int num;
	private static DecimalFormat df = new DecimalFormat("00");

	public static void main(String[] args) throws Exception {
		String base = "https://www.xgmn.org/YouMi/page_";
		List<String> pages = new ArrayList<>();
		for (int i = 3; i <= 8; i++) {
			pages.add(base + i + ".html");
		}
		for (String page : pages) {

			Elements elements = Jsoup.connect(page).get().select(".td6 a");
//			log.info(elements.toString());
			for (Element element : elements) {
				String setName = element.select("img").attr("title");
				String url = element.attr("href");
//				log.info(host + url);
				log.info(setName);
				Document doc = Jsoup.connect(host + url).get();
				Elements eles = doc.select(".page a");
//				log.info(eles.toString());
				int pageCount = Integer.parseInt(eles.get(eles.size() - 3).text());
				List<String> images = new ArrayList<>();
				images.add(host + url);
				for (int j = 1; j <= pageCount; j++) {
					images.add(host + url.replace(".html", "_" + j + ".html"));
				}

//				log.info(images.toString());

				num = 1;
				for (String string : images) {
//					log.info(string);
					Elements s = Jsoup.connect(string).get().select(".img img");
					for (Element ele : s) {
//						log.info(ele.attr("src").toString());
						String path = rootpath + setName + "/" + df.format(num) + ".jpg";
						Utils.downPic(host + ele.attr("src"), path, host);
						num++;
					}

				}

			}

		}

	}
}
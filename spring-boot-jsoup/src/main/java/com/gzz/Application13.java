package com.gzz;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lombok.extern.slf4j.Slf4j;

//https://www.kaka1234.com/HTM/YouMi/2020/0211/48216.html
@Slf4j
public class Application13 {

	private static String rootpath = "e:/youme/";
	private static String host = "https://www.kaka1234.com/";
	private static int num;
	private static DecimalFormat df = new DecimalFormat("00");

	public static void main(String[] args) throws Exception {
		String base = "https://www.uu131.net/meinv/YouMi/list_87_";
		List<String> pages = new ArrayList<>();
		for (int i = 2; i <= 26; i++) {
			pages.add(base + i + ".html");
		}
		for (String page : pages) {
			Elements elements = Jsoup.connect(page).get().select(".detail-list li div a");

			for (Element element : elements) {
				String setName = element.text();
//				log.info(element.attr("href"));
				Elements eles = Jsoup.connect(element.attr("href")).get().select(".page-show a[href]");
				eles.remove(eles.size() - 1);

				List<String> images = new ArrayList<>();
				images.add(element.attr("href"));
//
				log.info(setName);
				for (Element e : eles)
					images.add("https://www.uu131.net/meinv/YouMi/" + e.attr("href"));
//				log.info(images + "");
				num = 1;
				for (String string : images) {
					Elements s = Jsoup.connect(string).get().select(".content img");
					for (Element ele : s) {
//						log.info(ele.attr("src").toString());
						String path = rootpath + setName + "/" + df.format(num) + ".jpg";
						Utils.downPic(ele.attr("src"), path, host);
						num++;
					}

				}
//				
			}

		}

	}
}
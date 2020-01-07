package com.gzz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//https://www.meitulu.com/
public class Application1 {
	private static Log logger = LogFactory.getLog(Application1.class);
	private static String root = "D:/youmihui/";

	public static void main(String[] args) throws IOException {
		List<String> pages = new ArrayList<>();
		String url = "https://www.meitulu.com/t/youmihui/";
		pages.add(url);
		for (int i = 2; i <= 6; i++)
			pages.add(url + i + ".html");
		for (String uri : pages) {
			Elements select = Jsoup.connect(uri).get().select(".p_title a");
			for (Element item : select) {
				logger.info(item.text());
				String part = item.text();
				Elements eles = Jsoup.connect(item.attr("href")).get().select("#pages a");
				int pageCount = new Integer(eles.get(eles.size() - 2).text());
				List<String> pagesList = new ArrayList<>();
				pagesList.add(item.attr("href"));
				for (int j = 2; j < pageCount; j++) {
					pagesList.add(item.attr("href").replace(".html", "_" + j + ".html"));
				}
				for (String i : pagesList) {
					Jsoup.connect(i).get().select("center img").forEach(img -> {
						String path = root + part + "/" + img.attr("src").split("/")[6];
						Utils.downPic(img.attr("src"), path);
					});
				}
			}
		}
	}
}

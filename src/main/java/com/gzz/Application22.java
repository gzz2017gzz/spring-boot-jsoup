package com.gzz;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//https://www.meitulu.com/
public class Application22 {
	private static Log logger = LogFactory.getLog(Application22.class);
	private static String root = "E:/bbb/";
	private static DecimalFormat df = new DecimalFormat("00");

	public static void main(String[] args) throws IOException {

		String url = "https://www.meitulu.com/item/3553.html";

		Document doc = Jsoup.connect(url).get();
		String part = doc.select("h1").text();
		logger.info(part);
		Elements eles = doc.select("#pages a");
		int pageCount = new Integer(eles.get(eles.size() - 2).text());
		logger.info(pageCount);
		List<String> pagesList = new ArrayList<>();
		pagesList.add(url);
		for (int j = 2; j <=pageCount; j++) {
			pagesList.add(url.replace(".html", "_" + j + ".html"));
		}
		logger.info(pagesList);
		int num = 1;
		for (String i : pagesList) {
			Elements elements = Jsoup.connect(i).get().select("center img");

			for (Element img : elements) {
				String path = root + part + "/" + df.format(num) + ".jpg";
				logger.info(img.attr("src"));
				logger.info(path);
				Utils.downPic(img.attr("src"), path, "https://www.meitulu.com/item/3553.html");
				num++;
			}

		}

	}
}

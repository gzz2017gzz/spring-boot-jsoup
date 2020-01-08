package com.gzz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//https://www.aitaotu.com
public class Application2 {
	private static Log logger = LogFactory.getLog(Application1.class);
	private static String root = "D:/mitao/";
	private static String base = "https://www.aitaotu.com";

	public static void main(String[] args) throws IOException {
		String baseUrl = "https://www.aitaotu.com/tag/mitaoshe.html";
		List<String> pagesList = new ArrayList<>();
		pagesList.add(baseUrl);
		for (int j = 2; j < 8; j++) {
			pagesList.add(baseUrl.replace(".html", "/" + j + ".html"));
		}
		for (String string : pagesList) {

			Document document = Jsoup.connect(string).get();
			Elements select = document.select("#mainbody .Pli-litpic");
			for (Element element : select) {
				String path = element.attr("title").replace(" ", "");
				String last = path.substring(path.length() - 7) + path.substring(0, path.length() - 7);
				logger.info(last);
				List<String> pages = new ArrayList<>();
				pages.add(base + element.attr("href"));
				int pageCount = new Integer(Jsoup.connect(base + element.attr("href")).get().select(".totalpage").get(0).text());
				for (int i = 2; i < pageCount + 1; i++)
					pages.add(base + element.attr("href").replace(".html", "_" + i + ".html"));
				for (String url : pages) {
					String pic = Jsoup.connect(url).get().select("#big-pic img").attr("src");
					Utils.downPic(pic, root + last + "/" + pic.split("/")[7]);
				}
			}
		}
	}
}

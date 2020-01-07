package com.gzz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
//https://www.meituri.com/
public class Application5 {
	private static Log logger = LogFactory.getLog(Application5.class);
	private static String root = "d:/dgzx/";

	public static void main(String[] args) throws IOException {
		List<String> setList = new ArrayList<>();
		String start = "https://www.meituri.com/x/44/";
		setList.add(start);
		for (int i = 1; i <= 1; i++)
			setList.add(start + "index_" + i + ".html");
		for (String url : setList) {
			Elements elements = Jsoup.connect(url).get().select(".hezi .biaoti a");
			for (Element element : elements) {
				String firstPage = element.attr("href");
				logger.info(element.attr("href"));
				List<String> personList = new ArrayList<>();
				personList.add(firstPage);
				Elements aList = Jsoup.connect(firstPage).get().select("#pages a");
				int personPageCout = new Integer(aList.get(aList.size() - 2).text());
				for (int j = 2; j <= personPageCout; j++)
					personList.add(firstPage + j + ".html");
				for (String string : personList) {
					Elements images = Jsoup.connect(string).get().select(".content img");
					for (Element img : images) {
						String picUrl = img.attr("src");
						Utils.downPic(picUrl, root + element.text() + "/" + picUrl.split("/")[6]);
					}
				}
			}
		}
	}
}

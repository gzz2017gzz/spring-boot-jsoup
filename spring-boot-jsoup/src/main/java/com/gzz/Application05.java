package com.gzz;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//https://www.meituri.com/
public class Application05 {
	private static Log logger = LogFactory.getLog(Application05.class);
	private static String root = "e:/meiyuan/";
	private static int num;
	private static DecimalFormat df = new DecimalFormat("00");

	public static void main(String[] args) throws IOException, InterruptedException {
		List<String> setList = new ArrayList<>();
		String start = "https://www.meituri.com/x/74/";
		setList.add(start);
		for (int i = 2; i <= 11; i++)
			setList.add(start + "index_" + i + ".html");
		for (String url : setList) {
			Elements elements = Jsoup.connect(url).get().select(".hezi .biaoti a");
			for (Element element : elements) {
				String setName = element.text();
				int cu = Integer.parseInt(setName.replaceAll("\\D", ""));
				if (cu < 38) {
					String[] split = setName.split("]");
					setName = split[1] + "]"+split[0];
					String firstPage = element.attr("href");
					logger.info(setName);
					List<String> personList = new ArrayList<>();
					personList.add(firstPage);
					Elements aList = Jsoup.connect(firstPage).get().select("#pages a");
					int personPageCout = Integer.parseInt(aList.get(aList.size() - 2).text());
					for (int j = 2; j <= personPageCout; j++)
						personList.add(firstPage + j + ".html");
					num = 1;
					for (String string : personList) {
						Elements images = Jsoup.connect(string).get().select(".content img");
						for (Element img : images) {
							String picUrl = img.attr("src");
							String path = root + setName + "/" + df.format(num) + ".jpg";
							Utils.downPic(picUrl, path, "https://www.meituri.com");
							num++;
//						TimeUnit.SECONDS.sleep(1);
						}
					}
				}

			}
		}
	}
}

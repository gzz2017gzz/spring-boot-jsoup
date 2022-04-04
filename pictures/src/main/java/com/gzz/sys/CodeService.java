package com.gzz.sys;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.gzz.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public final class CodeService {

	private static int num;
	private static DecimalFormat df = new DecimalFormat("00");
	private static String root = "D:/";

	public void downPictures(DownParam param) {
		try {
			for (int i = 0; i < param.getPageCount(); i++) {
				Document document = Jsoup.connect(param.getSetUrl() + "/page/" + i).get();
				Elements selects = document.select("a.thumbnail");
				for (Element element : selects) {
					String href = element.attr("href");
					String setPath = element.select("img").first().attr("alt");
					log.info(setPath);
					Document pages = Jsoup.connect(href).get();
					Elements pics = pages.select("a.post-page-numbers");
					String pageNum = pics.last().select("span").first().text();
					num = 0;
					for (int j = 1; j <= Integer.parseInt(pageNum); j++) {
						pages = Jsoup.connect(href + "/" + j).get();
						Elements images = pages.select(".article-content img");
						for (Element image : images) {
							String path = root + param.getSetName() + "/" + setPath + "/" + df.format(num) + ".jpg";
							Utils.down(image.attr("src"), path, href);
							num++;
						}
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public int queryCount(String url) {
		Document document = null;
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		String select = document.select(".pagination li span").last().text();
		String page = Pattern.compile("[^0-9]").matcher(select).replaceAll("");
		log.info("{}", page);
		return Integer.parseInt(page);
	}

}

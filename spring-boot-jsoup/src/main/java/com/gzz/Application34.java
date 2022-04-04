package com.gzz;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application34 {
	public static void main(String[] args) throws Exception {
		String index = "https://www.ku137.net/b/2/list_2_";
		for (int i = 42; i <= 73; i++) {
			log.info("i={}",i);
//			Document document = Jsoup.connect(index + i + ".html").get();
// 			log.info("{}", document);
			Elements elements = Jsoup.connect(index + i + ".html").get().select("div[class=m-list ml1] ul[class=cl] li a");
//			log.info("{}", elements);
			for (Element element : elements) {
				String href = element.attr("href");
//				log.info("href={}", href);
				Elements doc = Jsoup.connect(href).get().select("div[class=Title111] a[target=_blank][href]");
				log.info("{}", doc.get(0).attr("href"));
			}
		}
	}
}
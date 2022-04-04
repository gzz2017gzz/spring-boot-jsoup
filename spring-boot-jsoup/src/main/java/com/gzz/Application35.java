package com.gzz;

import java.util.HashSet;
import java.util.Set;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application35 {
	static Set<String> pages = new HashSet<String>();
	static String index = "https://www.ximalaya.com/ertong/13135667/";
	static String root = "https://www.ximalaya.com";
	static String play = "https://www.ximalaya.com/revision/play/v1/audio?id=%s&ptype=1";

	public static void main(String[] args) throws Exception {
		Elements elements = Jsoup.connect(index).get().select("ul[class=pagination-page WJ_] li a");
		for (Element element : elements) {
			pages.add(element.attr("href"));
		}
		log.info("pages={}", pages);
		for (String page : pages) {
			Elements eles = Jsoup.connect(root + page).get().select("div[class=text lF_] a");
			for (Element ele : eles) {
				String data = ele.attr("title") + ele.attr("href");
				String arr[] = data.split("/");
				Response res = Jsoup.connect(String.format(play, arr[3])).ignoreContentType(true).execute();
				String body = res.body();
				JSONObject json = JSONObject.parseObject(body);
				log.info("name={},url={},", arr[0], json.getJSONObject("data").getString("src"));
			}
		}
	}
}
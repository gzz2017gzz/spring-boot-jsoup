package com.gzz;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class Application25 {
	private static String root = "E:/aaaa/";
	private static DecimalFormat df = new DecimalFormat("00");
	public static void main(String[] args) throws IOException {
		for (int i = 16; i < 26; i++) {
			Document document = Jsoup.connect("http://www.tuyim.net/forum-35-" + i + ".html").get();
			Elements elements = document.select("div.bus_vtem a.preview");
			for (Element element : elements) {
				String title = element.attr("title")
						.replace("MyGirl美媛馆 ", "").replace("[MyGirl美媛馆] ", "")
						.replace("[MyGirl美媛馆]", "").replaceAll("(?i)(vol)", "VOL");
				if (count(title) < 160) {
					String base = element.attr("href");
					log.info("i={},{}", i, title);
					Elements eles = Jsoup.connect(base).get().select(".mbn.savephotop img");
					int num = 1;
					for (Element ele : eles) {
						String path = root + title + "/" + df.format(num) + ".jpg";
						Utils.downPic(ele.attr("data-original"), path, base);
						num++;
					}
				}
			}
		}
	}
	public static int count(String title) {
		Matcher matcher = Pattern.compile("(.*(?i)OL.)(\\d{3})(\\s+.*)").matcher(title);
		if (matcher.matches()) {
			return Integer.parseInt(matcher.group(2));
		}
		return 357;
	}
}

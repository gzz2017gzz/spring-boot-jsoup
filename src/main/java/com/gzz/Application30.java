package com.gzz;

import java.text.DecimalFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application30 {
	private static String root = "E:/yhj/";
	private static DecimalFormat df = new DecimalFormat("00");

	public static void main(String[] args) throws Exception {
		
		Document document =  Jsoup.connect("http://v.xue.taobao.com/learn.htm?spm=a2174.7365753.0.0.cPUek8&courseId=106409&chapterId=8472505&sectionId=8472856" ).get() ;
		log.info("{}", document);
		
		Elements elements = new Elements();
		
//		String index = "http://i.xue.taobao.com/detail.htm?spm=a2174.7789578.4.3.BQcHJU&courseId=106409";
//		Elements elements  = Jsoup.connect(index ).get().select("a .clearfix");
//		log.info("{}", elements);
		
//		for (int i = 8; i <= 13; i++) {
////			Document document = Jsoup.connect(index + i+".html").get();
////			log.info("{}", elements);
//			Elements elements = Jsoup.connect(index + i+".html").get().select("#waterfall .bus_vtem a[class=preview]");
//			for (Element element : elements) {
//				String title = element.attr("title").replace("...", "").replace(" ", "");
//				String base = element.attr("href");
//				int count = count(element.attr("title"));
//				log.info("i={},title={}",i, title,i);
//				if (count < 107) {
//					Elements eles = Jsoup.connect(base).get().select(".mbn.savephotop img");
////					log.info("{}", eles);
//					int num = 1;
//					for (Element ele : eles) {
//						String path =  "e:/001.ts";
//						Utils.downPic("https://tao-edu.alicdn.com/IIirmK5vl0huoCavizl/5mWPeb5jjNZTPYGMbqV@@ud-00016.ts", path, "");
//						num++;
//					}
//				}
//			}
//		}
	}

//	public static int count(String title) {
//		Matcher matcher = Pattern.compile("(.*(?i)OL.)(\\d{3})(\\s+.*)").matcher(title);
//		if (matcher.matches()) {
////			log.info("{}", matcher.group(2));
//			return new Integer(matcher.group(2));
//		}
//		return 164;
//	}
}

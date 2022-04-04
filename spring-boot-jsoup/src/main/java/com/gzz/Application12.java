package com.gzz;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lombok.extern.slf4j.Slf4j;

//https://www.kaka1234.com/HTM/YouMi/2020/0211/48216.html
@Slf4j
public class Application12 {
 
	private static String rootpath = "e:/youme/";
	private static String host = "https://www.kaka1234.com/";
	private static int num;
	private static DecimalFormat df = new DecimalFormat("00");

	public static void main(String[] args) throws Exception {
		String base="https://www.kaka1234.com/HTM/YouMi/list_157_";
		List<String> pages = new ArrayList<>();
		for(int i=1;i<=26;i++) {
			pages.add(base+i+".html");
		}
		for (String page : pages) {
			Elements elements = Jsoup.connect(page).get().select(".c.s_li.zxgx_list.l a");
			for (Element element : elements) {
				Document document = Jsoup.connect(element.attr("href")).get();
				Elements select = document.select(".dede_pages a[href]");
				List<String> images = new ArrayList<>();
				images.add(element.attr("href"));
				String setName=document.select(".Title h1").text();
				log.info(setName);
				for(int j=2;j<select.size();j++)
					images.add(element.attr("href").replace(".html", "_"+j+".html"));
				num = 1;
				for (String string : images) {
					Elements eles = Jsoup.connect(string).get().select(".content img");
					for (Element ele : eles) {
//						log.info(ele.attr("src").toString());
						String path = rootpath + setName + "/" + df.format(num) + ".jpg";
						Utils.downPic(ele.attr("src"), path, host);
						num++;
					}
					
				}
				
			}
			
		}
 
	}
}
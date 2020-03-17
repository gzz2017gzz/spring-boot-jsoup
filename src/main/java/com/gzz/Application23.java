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

public class Application23 {
	private static Log logger = LogFactory.getLog(Application23.class);
	private static String root = "E:/bbb/";
	private static DecimalFormat df = new DecimalFormat("00");

	public static void main(String[] args) throws IOException {

	
 
		List<String> pagesList = new ArrayList<>();
		String url = "https://www.5ikantu.com/thread-5914-1-17.html?pp=";
		for (int j = 1; j <15; j++) {
			pagesList.add(url+j );
		}
	
		int num = 1;
		for (String i : pagesList) {
			Document document = Jsoup.connect(i).get();
//			logger.info(document);
			Elements elements = document.select("#postmessage_5914 img");
 			logger.info(i);
			for (Element img : elements) {
				String path = root  + df.format(num) + ".jpg";
				logger.info(img.attr("file"));
				logger.info(path);
				Utils.downPic(img.attr("file"), path, "https://www.5ikantu.com/");
				num++;
			}

		}

	}
}

package com.gzz;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gzz.base.Page;
import com.gzz.picture.Picture;
import com.gzz.picture.PictureCond;
import com.gzz.picture.PictureDao;

import lombok.extern.slf4j.Slf4j;

//https://www.duotu555.com
@Slf4j
@SpringBootApplication
public class Application11 {

	private static String rootpath = "e:/meiyan2/";
	private static String host = "https://www.ku137.net/";
	private static int num;
	private static DecimalFormat df = new DecimalFormat("00");

	public static void main(String[] args)   {
		SpringApplication.run(Application11.class, args);

	}
	@PostConstruct
	public void run() throws Exception {
//		https://www.duotu555.com/mm/89/list_89_10.html
		Elements elements = Jsoup.connect("https://www.duotu555.com/mm/89/index.html").get().select(".boxs li a:contains([YouMi])");
		for (Element element : elements) {
			String setName = element.text();
			log.info(setName);
			Document doc = Jsoup.connect(element.attr("href")).get();
			Integer count = new Integer(doc.select("#pages a").get(0).text().replace("共", "").replace("页:", ""));
			List<String> pages = new ArrayList<>();
			pages.add(element.attr("href"));
			num = 1;
			List<Picture> urls = new ArrayList<>();
			log.info(setName.split(" ")[2].replaceAll("\\D", "")); 
//			for (int i = 2; i < count; i++)
//				pages.add(element.attr("href").replace(".html", "_" + i + ".html"));
//			for (String url : pages) {
//				Elements eles = Jsoup.connect(url).get().select(".content img");
//				for (Element ele : eles) {
//					log.info(ele.attr("src").toString());
//					String path = rootpath + setName + "/" + df.format(num) + ".jpg";
////					Utils.downPic(ele.attr("src"), path, host);
//					urls.add(Picture.builder().url(ele.attr("src")).path(path).name(setName).build());
//					num++;
//				}
//			}
//			dao.insertBatch(urls);
//			urls.clear();
		}
	}
	
//	@PostConstruct
//	public void down() {
//		PictureCond cond = new PictureCond();
//		long count = dao.queryCount(cond);
//		for (int i = 0; i < count+10; i += 10) {
//			Page<Picture> page = dao.queryPage(cond);
//			page.getDataList().forEach(pic -> Utils.downPic(pic.getUrl(), pic.getPath(), host));
//			List<Object> ids = page.getDataList().stream().map(Picture::getId).collect(Collectors.toList());
//			dao.delete(ids.toArray());
//			
//		}
//	}

	@Autowired
	public PictureDao dao;
}
package com.gzz;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import com.gzz.base.Page;
import com.gzz.picture.Picture;
import com.gzz.picture.PictureCond;
import com.gzz.picture.PictureDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class DownPicture {
	private static String rootpath = "e:/meiyan/";
	private static String host = "https://www.ku137.net/";
	private static int num;
	private static DecimalFormat df = new DecimalFormat("00");

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DownPicture.class, args);

	}

	@PostConstruct
	public void run() throws IOException, InterruptedException {
		for (int j = 1; j < 13; j++) {
			Document document = Jsoup.connect("https://www.ku137.net/b/57/list_57_" + j + ".html").get();
//			log.info(document);
			Elements select = document.select(".l-pub").select(".cl a");
//			log.info(select);
			for (Element element : select) {
				log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>j={}", j);
//				log.info(element.attr("href"));
				List<String> set = new ArrayList<>();
				set.add(element.attr("href"));
				Document doc = Jsoup.connect(element.attr("href")).get();
				doc.select(".page a").forEach(i -> {
					if (!StringUtils.isEmpty(i.attr("href")))
						set.add("https://www.ku137.net/b/57/" + i.attr("href"));
				});
				set.remove(set.size() - 1);
//				log.info(set);
				String setName = doc.select("head title").text().replace("[MiStar美女] ", "").replace(" - 美女写真网", "");
				log.info(setName);
				num = 1;
				List<Picture> urls = new ArrayList<>();
				for (String page : set) {
//					log.info(page);
					TimeUnit.SECONDS.sleep(2);
					Elements images = Jsoup.connect(page).get().select(".content img");
					for (Element image : images) {
//						log.info(image.attr("src"));
//						System.exit(0);
						String path = rootpath + setName + "/" + df.format(num) + ".jpg";
						urls.add(Picture.builder().url(image.attr("src")).path(path).name(setName).build());
//						Utils.downPic(image.attr("src"), path, host);
						num++;
					}
				}
				dao.insertBatch(urls);
				urls.clear();
			}
		}

	}

//	@PostConstruct
	public void down() {
		PictureCond cond = new PictureCond();
		long count = dao.queryCount(cond);
		for (int i = 0; i < count+10; i += 10) {
			Page<Picture> page = dao.queryPage(cond);
			page.getDataList().forEach(pic -> Utils.downPic(pic.getUrl(), pic.getPath(), host));
			List<Object> ids = page.getDataList().stream().map(Picture::getId).collect(Collectors.toList());
			dao.delete(ids.toArray());
		}
	}

	@Autowired
	public PictureDao dao;
}

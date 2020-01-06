package com.gzz;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;

//https://www.lsmpx.com
public class Application7 {
	private static Log logger = LogFactory.getLog(Application7.class);

	private static String rootpath = "D:/yuhua/";
	private static String host = "https://www.yuleba.org";
	private static int num;
	private static DecimalFormat df = new DecimalFormat("00");

	public static void main(String[] args) throws Exception {
		for (int j = 0; j < 9; j++) {
			Jsoup.connect("https://www.yuleba.org/b/328-" + j + ".html").get().select(".b_img li h3 a").forEach(href -> {
				String page0 = href.attr("href");
				logger.info(href.text());
				try {
					int pageCount = new Integer(Jsoup.connect(host + page0).get().select(".paging a").get(0).text().replace("共", "").replace("页:", ""));
					num = 0;
					for (int i = 0; i < pageCount; i++) {
						Jsoup.connect(host + page0.replace("-0.", "-" + i + ".")).get().select(".picture img").forEach(img -> {
							String path = rootpath + href.text() + "/" + df.format(num) + ".jpg";
							downPic(img.attr("src"), path);
							num++;
						});
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}

	}

	private static void downPic(String url, String path) {
		try {
			File file = new File(path);
			if (!file.exists()) {
				URLConnection connection = new URL(url).openConnection();
				connection.setRequestProperty("Referer", "https://www.yuleba.org/");
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
				byte[] buff = new byte[1000];
				int rc = 0;
				InputStream inputStream = connection.getInputStream();
				while ((rc = inputStream.read(buff, 0, 1000)) > 0) {
					swapStream.write(buff, 0, rc);
				}

				Files.write(Paths.get(path), swapStream.toByteArray());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

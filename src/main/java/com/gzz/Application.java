package com.gzz;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	BufferedImage bi;

	public static void main(String[] args) throws IOException {

		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	private void 图片分类解析() throws IOException {
		InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("no.jpg");

		bi = ImageIO.read(resourceAsStream);

		Document 首页面 = Jsoup.connect("https://www.meitulu.com").get();
		Element 图片分类页面元素 = 首页面.getElementById("tag_ul");
		Elements 所有分类 = 图片分类页面元素.getElementsByTag("a");
		for (Element 单个分类元素 : 所有分类) {
			解析页数(单个分类元素.attr("href"));

//			System.out.println(单个分类元素.attr("href"));
//			System.out.println(单个分类元素.childNode(0));
		}

	}

	private void 解析页数(String 单类地址) throws IOException {
		Document 当前类页面 = Jsoup.connect(单类地址).get();
		Element 分页列表 = 当前类页面.getElementById("pages");
		Elements 所有链接元素 = 分页列表.getElementsByTag("a");
		Element 总页数据所在元素 = 所有链接元素.get(所有链接元素.size() - 2);
		Node 总页数元素 = 总页数据所在元素.childNode(0);
		int 总页数 = new Integer(总页数元素.toString());
		解析每页图集(单类地址);
		for (int i = 2; i <= 总页数; i++) {
			解析每页图集(单类地址 + i + ".html");
		}

	}

	private void 解析每页图集(String url) throws IOException {
		Document document = Jsoup.connect(url).get();
		Element element = document.body().getElementsByClass("img").get(0);
		Elements lis = element.getElementsByTag("li");
		for (Element element2 : lis) {
			Element child = element2.child(0);
			String 图片数 = Pattern.compile("[^0-9]").matcher(element2.child(1).toString()).replaceAll("");
			String 源站目录 = Pattern.compile("[^0-9]").matcher(child.attr("href")).replaceAll("");
			String 图集名称 = child.child(0).attr("alt");
			解析图集并下载(源站目录, new Integer(图片数), 图集名称);
			System.out.println(图集名称);
		}
	}

	private void 解析图集并下载(String 源站目录, int 图片数, String 图集名称) throws IOException {
		图集名称 = 图集名称.replaceAll("<", "").replaceAll(">", "").replaceAll("/", "-").replaceAll("\\\\", "-");
		for (int i = 1; i <= 图片数; i++) {
			String 文件路径 = "D:/" + 图集名称 + "/" + i + ".jpg";
			
			File file = new File(文件路径);
			if (!file.exists()) {
				String attr = "https://mtl.ttsqgs.com/images/img/" + 源站目录 + "/" + i + ".jpg";
				try {
					URL imgurl = new URL(attr);
					URLConnection connection = imgurl.openConnection();
					connection.setRequestProperty("Referer", "https://www.meitulu.com");
					com.google.common.io.Files.createParentDirs(file);
					Files.write(Paths.get(文件路径), 输入流转字节数组(connection.getInputStream()));
				} catch (Exception e) {
					System.out.println("文件不存在！！！" + attr);
					System.out.println("文件路径:" + 文件路径);
					ImageIO.write(bi, "png", file);
				}
			}
		}
	}

	public final byte[] 输入流转字节数组(InputStream inStream) throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = inStream.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		byte[] in2b = swapStream.toByteArray();
		return in2b;
	}
}

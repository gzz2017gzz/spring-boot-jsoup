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

public class Utils {
	public static void downPic(String url, String path) {
		try {
			File file = new File(path);
			if (!file.exists()) {
				URLConnection connection = new URL(url).openConnection();
//				connection.setRequestProperty("Referer", "https://img.aitaotu.cc/");
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

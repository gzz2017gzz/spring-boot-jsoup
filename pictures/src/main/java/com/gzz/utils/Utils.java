package com.gzz.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {
	public static void down(String url, String path, String referer) {
		try {
			File file = new File(path);
			if (!file.exists()) {
				URLConnection connection = new URL(url).openConnection();
				connection.setRequestProperty("Referer", referer);
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

				inputStream.close();
			}
		} catch (IOException e) {
			log.error("抛锚了...", path, url, e);
		}
	}
 
	public static List<File> disks() {
		return Arrays.asList(File.listRoots());
	}
}
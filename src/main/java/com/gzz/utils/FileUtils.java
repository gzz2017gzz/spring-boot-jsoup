package com.gzz.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtils {
	/**
	 * @方法说明 递规目录
	 */
	public static void listDir(File file, String src, String tar) throws IOException {
		if (file.isDirectory()) {
			for (File subFile : file.listFiles()) {
				listDir(subFile, src, tar);
			}
		}
		if (file.getName().endsWith(src)) {
			String oldPath = file.getAbsolutePath();
			String newPath = oldPath.substring(0, oldPath.length() - src.length()) + tar;
			Files.move(Paths.get(oldPath), Paths.get(newPath), StandardCopyOption.REPLACE_EXISTING);
		}
	}

	public static void main(String[] args) throws IOException {
		String path = "E:/youme/新建文件夹";
		listDir(new File(path), "webp", "jpg");
//		listDir(new File(path), "jpg", "webp");
		log.info("命名完成!");
	}

}

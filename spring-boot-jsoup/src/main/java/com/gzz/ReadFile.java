package com.gzz;
 

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReadFile {
    private final static String split = ".";

    public static void main(String[] args) {
        String path = "D:\\nodejs\\前端 NodeJS 视频教程全集（20P） 6 小时从入门到精通";
        List<String> names = new ArrayList<>();
        File file = new File(path);
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            for (File file2 : listFiles) {
                String name = file2.getName();
//              System.out.println(name);
//              System.out.println(name.indexOf(split));
                String substring = name.substring(name.indexOf(split) + 1, name.indexOf("("));
//              System.out.println(substring);
                String index = name.substring(0, name.indexOf("."));
                DecimalFormat format = new DecimalFormat("000");
                String num = format.format(Integer.parseInt(index));

//                  System.out.println(num);
                names.add(num + "-" + substring);

            }
            Collections.sort(names, (o1, o2) -> o1.compareTo(o2));
            names.forEach(i -> System.out.println(i));

        }
    }

}
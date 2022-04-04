package com.gzz;

import java.text.DecimalFormat;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application37 {
	static String albumId = "178945913";
	static String root = "d:/美国K-12小学上集音频/";

	static DecimalFormat df = new DecimalFormat("00");
	static String play = "https://www.ximalaya.com/revision/play/v1/audio?id=%S&ptype=1";
	static String tracksListUrl = "https://www.ximalaya.com/revision/album/v1/getTracksList?albumId=%S&pageNum=%S&sort=0";
	static String pageNum = "1";
	static String referer = "https://www.ximalaya.com/";

	public static void main(String[] args) throws Exception {
	 
	 
		JSONArray jsonArray = JSONArray.parseArray(JsonData.json);
		for (Object item : jsonArray) {
			JSONObject js = JSONObject.parseObject(item.toString());
			String title = df.format(js.getInteger("index")) + "-" + js.getString("title");
			Response res = Jsoup.connect(String.format(play, js.getString("trackId")))
					.header("xm-sign", "df3fce6863d483c540100665d9173308(82)1611250976751(77)1611250063165")
					.ignoreContentType(true).execute();
			js = JSONObject.parseObject(res.body());
			log.info(res.body());
			title = title.replace("|", "");
			title = title.replace("？", "");
			title = title.replace(":", "");
			title = title.replace("：", "");
			title = title.replace(" ", "");

			log.info("title={},url={},", title, js.getJSONObject("data").getString("src"));

			String path = root + title + ".m4a";
			Utils.downPic(js.getJSONObject("data").getString("src"), path, referer);
		}
 
	}
//public static void main(String[] args) {
//	String title="263-米小圈上学记番外篇：机灵小神童（下）| 米小圈脑筋急转弯";
//	title = title.replace("|", "");
//	title = title.replace("？", "");
//	title = title.replace(":", "");
//	title = title.replace("：", "");
//	log.info(title);
//}
}
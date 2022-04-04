package com.gzz;

import java.text.DecimalFormat;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application36 {
	static String albumId = "23054284";
	static String root = "d:/美国K-12小学上集音频/";

	static DecimalFormat df = new DecimalFormat("00");
	static String play = "https://www.ximalaya.com/revision/play/v1/audio?id=%S&ptype=1";
	static String tracksListUrl = "https://www.ximalaya.com/revision/album/v1/getTracksList?albumId=%S&pageNum=%S&sort=0";
	static String pageNum = "1";
	static String referer = "https://www.ximalaya.com/";

	public static void main(String[] args) throws Exception {
		String url=String.format(tracksListUrl, albumId, pageNum);
		log.info(url);
		Response res = Jsoup.connect(String.format(tracksListUrl, albumId, pageNum)).ignoreContentType(true).execute();
		JSONObject json = JSONObject.parseObject(res.body());
		Integer trackTotalCount = json.getJSONObject("data").getInteger("trackTotalCount");
		int pageCount = trackTotalCount % 30 == 0 ? trackTotalCount / 30 : trackTotalCount / 30 + 1;
		for (int pageNum = 1; pageNum <= pageCount; pageNum++) {
			res = Jsoup.connect(String.format(tracksListUrl, albumId, pageNum + "")).ignoreContentType(true).execute();
			json = JSONObject.parseObject(res.body());
			JSONArray jsonArray = json.getJSONObject("data").getJSONArray("tracks");
			for (Object item : jsonArray) {
				JSONObject js = JSONObject.parseObject(item.toString());
				String title = df.format(js.getInteger("index")) + "-" + js.getString("title");
				res = Jsoup.connect(String.format(play, js.getString("trackId"))).ignoreContentType(true).execute();
				js = JSONObject.parseObject(res.body());

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
package com.gzz.utils;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {
	private static String data = "[" + "{\"url\":\"https://www.wxytw.com/jgmntp/xiurenwang\",\"label\":\"秀人网\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/aiyouwu\",\"label\":\"爱尤物\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/yuhuajie\",\"label\":\"语画界\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/huayangshow\",\"label\":\"花漾show\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/mofanxueyuan\",\"label\":\"模范学院\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/meiyuanguan\",\"label\":\"美媛馆\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/miaotangyinghua\",\"label\":\"喵糖映画\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/yalayi\",\"label\":\"雅拉伊\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/aimishe\",\"label\":\"爱蜜社\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/dianannan\",\"label\":\"嗲囡囡\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/xingyanshe\",\"label\":\"星颜社\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/youmei\",\"label\":\"尤美\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/youmihui\",\"label\":\"尤蜜荟\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/toutiaonvshen\",\"label\":\"头条女神\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/tangguohuabao\",\"label\":\"糖果画报\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/meiyanshe\",\"label\":\"魅妍社\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/youwuguan\",\"label\":\"尤物馆\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/mitaoshe\",\"label\":\"蜜桃社\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/huayan\",\"label\":\"花の颜\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/dkyunvlang\",\"label\":\"DK御女郎\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/maomengbang\",\"label\":\"猫萌榜\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/youguowang\",\"label\":\"尤果网\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/xingleyuan\",\"label\":\"星乐园\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/lienvshen\",\"label\":\"猎女神\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/kelanvshen\",\"label\":\"克拉女神\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/xiongchuanjixin\",\"label\":\"熊川纪信\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/guotuanwang\",\"label\":\"果团网\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/hongpamao\",\"label\":\"轰趴猫\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/tujimeng\",\"label\":\"兔几盟\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/boluoshe\",\"label\":\"波萝社\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/meituibaobei\",\"label\":\"美腿宝贝\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/tuinvshen\",\"label\":\"推女神\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/motelianmeng\",\"label\":\"模特联盟\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/jimengwenhua\",\"label\":\"激萌文化\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/youxingguan\",\"label\":\"优星馆\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/boheye\",\"label\":\"薄荷叶\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/mengfu\",\"label\":\"萌缚\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/tuinvlang\",\"label\":\"推女郎\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/aisi\",\"label\":\"爱丝\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/yingsihui\",\"label\":\"影私荟\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/cunzhangdebaowu\",\"label\":\"村长的宝物\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/ddy\",\"label\":\"DDY\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/wanweishenghuo\",\"label\":\"顽味生活\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/51modo\",\"label\":\"51Modo\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/rosi\",\"label\":\"Rosi\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/disi\",\"label\":\"Disi\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/ishow\",\"label\":\"IShow\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/pans\",\"label\":\"Pans\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/zhongguotuimo\",\"label\":\"中国腿模\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/vgirl\",\"label\":\"VGirl\"},"
			+ "{\"url\":\"https://www.wxytw.com/jgmntp/ru1mm\",\"label\":\"Ru1mm\"}," + "{\"url\":\"https://www.wxytw.com/jgmntp/sityle\",\"label\":\"Sityle\"}" + "]";

	public static void main(String[] args) {

		json().forEach(i -> System.out.println(i));
	}

	public static List<Dict> json() {
		Dict[] dicts = null;
		try {
			dicts = new ObjectMapper().readValue(data, Dict[].class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Arrays.asList(dicts);// 把数组转成list
	}
}

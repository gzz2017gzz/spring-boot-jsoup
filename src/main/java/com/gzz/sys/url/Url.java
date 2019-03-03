package com.gzz.sys.url;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
//import lombok.experimental.Accessors;
/**
 * @类说明 [sys_url]实体类
 * @author 高振中
 * @date 2019-02-23 01:04:06
 **/
@Setter
@Getter
//@Accessors(chain = true)
@Builder	
@AllArgsConstructor
@NoArgsConstructor
public class Url {
    // 以下为数据库中 字段
	private Integer id; // id
	private String url; // url
	private String path; // path
    // 以下为查询显示辅助属性
}
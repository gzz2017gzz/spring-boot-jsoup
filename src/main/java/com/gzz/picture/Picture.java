package com.gzz.picture;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
//import lombok.experimental.Accessors;
/**
 * @类说明 [picture]实体类
 * @author 高振中
 * @date 2020-02-16 20:42:22
 **/
@Setter
@Getter
//@Accessors(chain = true)
@Builder	
@AllArgsConstructor
@NoArgsConstructor
public class Picture {
    // 以下为数据库中 字段
	private Integer id; // id
	private String url; // url
	private String name; // name
	private String path; // path
    // 以下为查询显示辅助属性
}
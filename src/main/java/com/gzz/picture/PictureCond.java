package com.gzz.picture;

import com.gzz.base.BaseCondition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * @类说明 [picture]查询条件实体
 * @author 高振中
 * @date 2020-02-16 20:42:22
 **/
@Setter
@Getter
//@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PictureCond extends BaseCondition {

    /**
     * @方法说明: 拼加自定义条件
     **/
    @Override
    public void addCondition() {
		add(id, "AND t.id = ?");
		add(url, "AND t.url LIKE ?", 3);
		add(name, "AND t.name LIKE ?", 3);
		add(path, "AND t.path LIKE ?", 3);
//		add(ids, "AND t.id IN ");
    }
//	以下为查询条件
	private Integer id; // id
	private String url; // url
	private String name; // name
	private String path; // path
//	private List<Object> ids;// 主键列表
//	以下为自定义查询条件
}
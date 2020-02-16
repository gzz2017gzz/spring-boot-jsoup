package com.gzz.picture;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.gzz.base.BaseDao;
import com.gzz.base.Page;
import com.gzz.base.SqlUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @类说明 [picture]数据访问层
 * @author 高振中
 * @date 2020-02-16 20:42:22
 **/
@Slf4j
@Repository
public class PictureDao extends BaseDao {

	private StringBuilder insert = new StringBuilder();
	private StringBuilder select = new StringBuilder();

	/**
	 * @方法说明 构造方法-拼加SQL
	 */
	public PictureDao() {
		select.append("SELECT t.id,t.url,t.name,t.path");
		select.append(" FROM picture t WHERE 1=1");
		insert.append("INSERT INTO picture (id,url,name,path) ");
		insert.append(" VALUES (:id,:url,:name,:path)");
	}

	/**
	 * @方法说明 新增picture记录
	 */
	public int save(Picture vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO picture (id,url,name,path)");
		sql.append(" VALUES (?,?,?,?)");
		Object[] params = { vo.getId(), vo.getUrl(), vo.getName(), vo.getPath() };
		// logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明 物理删除picture记录(多条)
	 */
	public int delete(Object ids[]) {
		String sql = "DELETE FROM picture WHERE id IN " + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql, ids);
	}

	/**
	 * @方法说明 更新picture记录
	 */
	public int update(Picture vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE picture SET url=?,name=?,path=? ");
		sql.append(" WHERE id=? ");
		Object[] params = { vo.getUrl(), vo.getName(), vo.getPath(), vo.getId() };
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明 按条件查询分页picture列表
	 */
	public Page<Picture> queryPage(PictureCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		// sb.append(cond.getOrderSql());//增加排序子句;
		// logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
		return queryPage(sb.toString(), cond, Picture.class);
	}

	/**
	 * @方法说明 按条件查询不分页picture列表
	 */
	public List<Picture> queryList(PictureCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		// sb.append(" ORDER BY operate_time DESC");
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(Picture.class));
	}

	/**
	 * @方法说明 按ID查找单个picture实体
	 */
//	public Picture findById(Integer id) {
//		StringBuilder sb = new StringBuilder(select);
//		sb.append(" AND t.id=?");
//		return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(Picture.class));
//	}

	/**
	 * @方法说明 按条件查询picture记录个数
	 */
	public long queryCount(PictureCond cond) {
		String countSql = "SELECT COUNT(1) FROM picture t WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
	}

	/**
	 * @方法说明 逻辑删除picture记录
	 */
//	public int deleteLogic(Object ids[]) {
//		String sql = "UPDATE picture SET delete_remark=1 WHERE id IN " + SqlUtil.ArrayToIn(ids);
//		return jdbcTemplate.update( sql, ids );
//	}

	/**
	 * @方法说明 新增picture记录并返回自增涨主键值
	 */
//	public long saveReturnPK(Picture vo) {
//		return saveKey(vo, insert.toString(), "id");
//	}

	/**
	 * @方法说明 批量插入picture记录
	 */
	public int[] insertBatch(List<Picture> list) {
		return batchOperate(list, insert.toString());
	}

}
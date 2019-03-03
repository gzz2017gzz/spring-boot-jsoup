package com.gzz.sys.url;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.gzz.base.BaseDao;
import com.gzz.base.Page;
import com.gzz.base.SqlUtil;

/**
 * @类说明 [sys_url]数据访问层
 * @author 高振中
 * @date 2019-02-23 01:04:06
 **/
@Repository
public class UrlDao extends BaseDao {

	private StringBuilder insert = new StringBuilder();
	private StringBuilder select = new StringBuilder();

	/**
	 * @方法说明 构造方法-拼加SQL
	 */
	public UrlDao() {
		select.append("SELECT t.id,t.url,t.path");
		select.append(" FROM sys_url t WHERE 1=1");

		insert.append("INSERT INTO sys_url (url,path) ");
		insert.append(" VALUES (:url,:path)");
	}

	/**
	 * @方法说明 新增sys_url记录
	 */
	public int save(Url vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO sys_url (id,url,path)");
		sql.append(" VALUES (?,?,?)");
		Object[] params = { vo.getId(), vo.getUrl(), vo.getPath() };
		// logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明 新增sys_url记录并返回自增涨主键值
	 */
	public long saveReturnPK(Url vo) {
		return saveKey(vo, insert.toString(), "id");
	}

	/**
	 * @方法说明 批量插入sys_url记录
	 */
	public int[] insertBatch(List<Url> list) {
		return batchOperate(list, insert.toString());
	}

	/**
	 * @方法说明 物理删除sys_url记录(多条)
	 */
	public int delete(Integer id) {
		String sql = "DELETE FROM sys_url WHERE id=?";
		return jdbcTemplate.update(sql, id);
	}

	/**
	 * @方法说明 更新sys_url记录
	 */
	public int update(Url vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE sys_url SET url=?,path=? ");
		sql.append(" WHERE id=? ");
		Object[] params = { vo.getUrl(), vo.getPath(), vo.getId() };
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明 按条件查询分页sys_url列表
	 */
	public Page<Url> queryPage(UrlCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		// sb.append(cond.getOrderSql());//增加排序子句;
		// logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
		return queryPage(sb.toString(), cond, Url.class);
	}

	/**
	 * @方法说明 按条件查询不分页sys_url列表
	 */
	public List<Url> queryList(UrlCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		// sb.append(" ORDER BY operate_time DESC");
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(Url.class));
	}

	/**
	 * @方法说明 按ID查找单个sys_url实体
	 */
	public Url findById(Integer id) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(" AND t.id=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[] { id }, new BeanPropertyRowMapper<>(Url.class));
	}

	/**
	 * @方法说明 按条件查询sys_url记录个数
	 */
	public long queryCount(UrlCond cond) {
		String countSql = "SELECT COUNT(1) FROM sys_url t WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
	}

	/**
	 * @方法说明 逻辑删除sys_url记录
	 */
	public int deleteLogic(Integer ids[]) {
		String sql = "UPDATE sys_url SET delete_remark=1 WHERE id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}

}
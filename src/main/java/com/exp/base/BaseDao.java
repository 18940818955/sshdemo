package com.exp.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.exp.entities.PageBean;
import com.exp.util.QueryHelper;

public interface BaseDao<T> {
	
	UserDetails findByUsername(String username);

	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	Serializable save(T entity);

	/**
	 * 删除实体
	 * 
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	void update(T entity);
	/**
	 * 保存或更新实体
	 * 
	 * @param entity
	 */
	void saveOrUpdate(T entity);

	/**
	 * 按id查询
	 * 
	 * @param id
	 * @return
	 */
	T getById(Integer id);

	/**
	 * 按id查询
	 * 
	 * @param ids
	 * @return
	 */
	List<T> getByIds(Integer[] ids);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<T> findAll();
	
	/**
	 * 公共的查询分页信息的方法（最终版）
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param queryHelper
	 *            HQL语句与参数列表
	 * @return
	 */
	PageBean getPageBean(int pageNum, int pageSize, QueryHelper queryHelper);

}

package com.njupt.swg.dao;

import java.util.List;
import java.util.Map;
import com.njupt.swg.model.Pager;


public interface IBaseDao<T> {
	/*
	 * 增加，返回对应的类型
	 */
	public T add(T t);
	/*
	 * 更新
	 */
	public void update(T t);
	/*
	 * 删除
	 */
	public void delete(int id);
	/*
	 * 加载
	 */
	public T load(int id);
	/*
	 * 不分页，hql,返回一组查询结果
	 */
	public List<T> list(String hql,Object[] args);
	public List<T> list(String hql,Object arg);
	public List<T> list(String hql);
	/*
	 * 不分页，hql,基于别名和参数查询的混合列表对象
	 */
	public List<T> listByAlias(String hql,Object[] args,Map<String,Object> alias);
	public List<T> listByAlias(String hql,Object arg,Map<String,Object> alias);
	public List<T> listByAlias(String hql,Map<String,Object> alias);
	/*
	 * 分页，hql,返回一组查询结果
	 */
	public Pager<T> find(String hql,Object[] args);
	public Pager<T> find(String hql,Object arg);
	public Pager<T> find(String hql);
	/*
	 * 分页，hql,基于别名和参数查询的混合列表对象
	 */
	public Pager<T> findByAlias(String hql,Object[] args,Map<String,Object> alias);
	public Pager<T> findByAlias(String hql,Object arg,Map<String,Object> alias);
	public Pager<T> findByAlias(String hql,Map<String,Object> alias);
	/*
	 * hql查询返回一个对象
	 */
	public Object queryObject(String hql,Object[] args);
	public Object queryObject(String hql,Object arg);
	public Object queryObject(String hql);
	public Object queryObjectByAlias(String hql,Object[] args,Map<String,Object> alias);
	public Object queryObjectByAlias(String hql,Object arg,Map<String,Object> alias);
	public Object queryObjectByAlias(String hql,Map<String,Object> alias);
	
	/*
	 * 基于hql更新一组数据
	 */
	public void updateByHql(String hql,Object[] args);
	public void updateByHql(String hql,Object arg);
	public void updateByHql(String hql);
	
	/*
	 * 不分页，sql,返回一组查询结果
	 */
	public <N extends Object>List<N> listBySql(String sql,Object[] args,Class<?> clz,Boolean hasEntity);
	public <N extends Object>List<N> listBySql(String sql,Object arg,Class<?> clz,Boolean hasEntity);
	public <N extends Object>List<N> listBySql(String sql,Class<?> clz,Boolean hasEntity);
	/*
	 * 不分页，sql,基于别名和参数查询的混合列表对象
	 */
	public <N extends Object>List<N> listByAliasSql(String sql,Object[] args,Map<String, Object> alias,Class<?> clz,boolean hasEntity);
	public <N extends Object>List<N> listByAliasSql(String sql,Class<?> clz,Boolean hasEntity,Map<String,Object> alias);
	/*
	 * 分页，sql,返回一组查询结果
	 */
	public <N extends Object>Pager<N> findBySql(String sql,Object[] args,Class<?> clz,Boolean hasEntity);
	public <N extends Object>Pager<N> findBySql(String sql,Object arg,Class<?> clz,Boolean hasEntity);
	public <N extends Object>Pager<N> findBySql(String sql,Class<?> clz,Boolean hasEntity);
	/*
	 * 分页，sql,基于别名和参数查询的混合列表对象
	 */
	public <N extends Object>Pager<N> findByAliasSql(String sql,Object[] args,Map<String, Object> alias,Class<?> clz,boolean hasEntity);
	public <N extends Object>Pager<N> findByAliasSql(String sql,Class<?> clz,Boolean hasEntity,Map<String,Object> alias);
}

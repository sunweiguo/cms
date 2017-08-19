package com.njupt.swg.baseDao;

import java.util.List;
import java.util.Map;

import com.njupt.swg.model.Pager;

public interface IBaseDao<T> {
	public T add(T t);
	public void update(T t);
	public void delete(int id);
	public T load(int id);
	
	//普通参数查询，不分页
	public List<T> list(String hql,Object[] args);
	public List<T> list(String hql,Object arg);
	public List<T> list(String hql);
	
	//参数和别名的不分页查询
	public List<T> listByAlias(String hql,Object[] args,Map<String,Object> alias);
	public List<T> listByAlias(String hql,Object arg,Map<String,Object> alias);
	public List<T> listByAlias(String hql,Map<String,Object> alias);
	
	//普通参数查询，分页
	public Pager<T> find(String hql,Object[] args);
	public Pager<T> find(String hql,Object arg);
	public Pager<T> find(String hql);
	
	//参数和别名的分页查询
	public Pager<T> findByAlias(String hql,Object[] args,Map<String,Object> alias);
	public Pager<T> findByAlias(String hql,Object arg,Map<String,Object> alias);
	public Pager<T> findByAlias(String hql,Map<String,Object> alias);
	
	//hql查询返回一个对象
	public Object queryObject(String hql,Object[] args);
	public Object queryObject(String hql,Object arg);
	public Object queryObject(String hql);
	
	public Object queryObjectByAlias(String hql,Object[] args,Map<String,Object> alias);
	public Object queryObjectByAlias(String hql,Object arg,Map<String,Object> alias);
	public Object queryObjectByAlias(String hql,Map<String,Object> alias);
	
	//hql更新一组数据
	public void updateByHql(String hql,Object[] args);
	public void updateByHql(String hql,Object arg);
	public void updateByHql(String hql);
	
	//sql,不分页查询
	public <N extends Object>List<N> listBySql(String sql,Object[] args,Class<?> clz,Boolean hasEntity);
	public <N extends Object>List<N> listBySql(String sql,Object arg,Class<?> clz,Boolean hasEntity);
	public <N extends Object>List<N> listBySql(String sql,Class<?> clz,Boolean hasEntity);
	
	//sql，别名和参数不分页查询
	public <N extends Object>List<N> listByAliasSql(String sql,Object[] args,Class<?> clz,Boolean hasEntity,Map<String,Object> alias);
	public <N extends Object>List<N> listByAliasSql(String sql,Object arg,Class<?> clz,Boolean hasEntity,Map<String,Object> alias);
	public <N extends Object>List<N> listByAliasSql(String sql,Class<?> clz,Boolean hasEntity,Map<String,Object> alias);
	
	//sql，分页查询
	public <N extends Object>Pager<N> findBySql(String sql,Object[] args,Class<?> clz,Boolean hasEntity);
	public <N extends Object>Pager<N> findBySql(String sql,Object arg,Class<?> clz,Boolean hasEntity);
	public <N extends Object>Pager<N> findBySql(String sql,Class<?> clz,Boolean hasEntity);
	
	//sql别名和参数分页查询
	public <N extends Object>Pager<N> findByAliasSql(String sql,Object[] args,Class<?> clz,Boolean hasEntity,Map<String,Object> alias);
	public <N extends Object>Pager<N> findByAliasSql(String sql,Object arg,Class<?> clz,Boolean hasEntity,Map<String,Object> alias);
	public <N extends Object>Pager<N> findByAliasSql(String sql,Class<?> clz,Boolean hasEntity,Map<String,Object> alias);
	
}

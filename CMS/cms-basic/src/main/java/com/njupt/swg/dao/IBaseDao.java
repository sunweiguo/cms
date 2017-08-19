package com.njupt.swg.dao;

import java.util.List;
import java.util.Map;
import com.njupt.swg.model.Pager;


public interface IBaseDao<T> {
	/*
	 * ���ӣ����ض�Ӧ������
	 */
	public T add(T t);
	/*
	 * ����
	 */
	public void update(T t);
	/*
	 * ɾ��
	 */
	public void delete(int id);
	/*
	 * ����
	 */
	public T load(int id);
	/*
	 * ����ҳ��hql,����һ���ѯ���
	 */
	public List<T> list(String hql,Object[] args);
	public List<T> list(String hql,Object arg);
	public List<T> list(String hql);
	/*
	 * ����ҳ��hql,���ڱ����Ͳ�����ѯ�Ļ���б����
	 */
	public List<T> listByAlias(String hql,Object[] args,Map<String,Object> alias);
	public List<T> listByAlias(String hql,Object arg,Map<String,Object> alias);
	public List<T> listByAlias(String hql,Map<String,Object> alias);
	/*
	 * ��ҳ��hql,����һ���ѯ���
	 */
	public Pager<T> find(String hql,Object[] args);
	public Pager<T> find(String hql,Object arg);
	public Pager<T> find(String hql);
	/*
	 * ��ҳ��hql,���ڱ����Ͳ�����ѯ�Ļ���б����
	 */
	public Pager<T> findByAlias(String hql,Object[] args,Map<String,Object> alias);
	public Pager<T> findByAlias(String hql,Object arg,Map<String,Object> alias);
	public Pager<T> findByAlias(String hql,Map<String,Object> alias);
	/*
	 * hql��ѯ����һ������
	 */
	public Object queryObject(String hql,Object[] args);
	public Object queryObject(String hql,Object arg);
	public Object queryObject(String hql);
	public Object queryObjectByAlias(String hql,Object[] args,Map<String,Object> alias);
	public Object queryObjectByAlias(String hql,Object arg,Map<String,Object> alias);
	public Object queryObjectByAlias(String hql,Map<String,Object> alias);
	
	/*
	 * ����hql����һ������
	 */
	public void updateByHql(String hql,Object[] args);
	public void updateByHql(String hql,Object arg);
	public void updateByHql(String hql);
	
	/*
	 * ����ҳ��sql,����һ���ѯ���
	 */
	public <N extends Object>List<N> listBySql(String sql,Object[] args,Class<?> clz,Boolean hasEntity);
	public <N extends Object>List<N> listBySql(String sql,Object arg,Class<?> clz,Boolean hasEntity);
	public <N extends Object>List<N> listBySql(String sql,Class<?> clz,Boolean hasEntity);
	/*
	 * ����ҳ��sql,���ڱ����Ͳ�����ѯ�Ļ���б����
	 */
	public <N extends Object>List<N> listByAliasSql(String sql,Object[] args,Map<String, Object> alias,Class<?> clz,boolean hasEntity);
	public <N extends Object>List<N> listByAliasSql(String sql,Class<?> clz,Boolean hasEntity,Map<String,Object> alias);
	/*
	 * ��ҳ��sql,����һ���ѯ���
	 */
	public <N extends Object>Pager<N> findBySql(String sql,Object[] args,Class<?> clz,Boolean hasEntity);
	public <N extends Object>Pager<N> findBySql(String sql,Object arg,Class<?> clz,Boolean hasEntity);
	public <N extends Object>Pager<N> findBySql(String sql,Class<?> clz,Boolean hasEntity);
	/*
	 * ��ҳ��sql,���ڱ����Ͳ�����ѯ�Ļ���б����
	 */
	public <N extends Object>Pager<N> findByAliasSql(String sql,Object[] args,Map<String, Object> alias,Class<?> clz,boolean hasEntity);
	public <N extends Object>Pager<N> findByAliasSql(String sql,Class<?> clz,Boolean hasEntity,Map<String,Object> alias);
}

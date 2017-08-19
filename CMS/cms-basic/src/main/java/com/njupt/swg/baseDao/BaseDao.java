package com.njupt.swg.baseDao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import com.njupt.swg.model.Pager;
import com.njupt.swg.model.SystemContext;

public class BaseDao<T> implements IBaseDao<T> {
	//获取到Session
	private SessionFactory SessionFactory;
	
	public SessionFactory getSessionFactory() {
		return SessionFactory;
	}
	@Inject
	public void setSessionFactory(SessionFactory sessionFactory) {
		SessionFactory = sessionFactory;
	}
	public Session getSession(){
		return this.getSessionFactory().getCurrentSession();
	}
	
	//获取当前类
	private Class<T> clz;
	@SuppressWarnings("unchecked")
	public Class<T> getClz(){
		if(clz == null){
			clz = (Class<T>)((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0];
		}
		return clz;
	}
	//如果有排序的参数传进来，则先进行排序
	private String initsort(String hql){
		String sort = SystemContext.getSort();
		String order = SystemContext.getOrder();
		if(sort != null && !"".equals(sort)){
			hql += " order by "+sort;
			if(!"desc".equals(order)){
				hql += " asc";
			}else{
				hql += " desc";
			}
		}
		return hql;
	}
	//先将别名的参数传进去，进行别名的查询
	@SuppressWarnings("rawtypes")
	private void setAliasParameter(Query query,Map<String,Object> alias){
		if(alias != null){
			Set<String> keys = alias.keySet();
			for(String key:keys){
				Object val = alias.get(key);
				if(val instanceof Collection){
					query.setParameterList(key, (Collection) val);
				}else{
					query.setParameter(key,val);
				}
			}
		}
	}
	//再将普通的参数传进去，进行普通参数的查询
	private void setParameter(Query query,Object[] args){
		if(args != null && args.length > 0){
			int index = 0;
			for(Object arg:args){
				query.setParameter(index++, arg);
			}
		}
	}
	
	//分页的初始化
	@SuppressWarnings("unused")
	private void setPagers(Query query,Pager pages){
		Integer pageSize = SystemContext.getPageSize();
		Integer pageOffset = SystemContext.getPageOffset();
		if(pageSize == null || pageSize < 0){
			pageSize = 15;
		}
		if(pageOffset == null || pageOffset < 0){
			pageOffset = 0;
		}
		pages.setOffset(pageOffset);
		pages.setSize(pageSize);
		query.setFirstResult(pageOffset).setMaxResults(pageSize);
	}
	
	//获取总的记录数
	@SuppressWarnings("unused")
	private String getCountHql(String hql,Boolean isHql){
		String e = hql.substring(hql.indexOf("from"));
		String c = "select count(*) "+e;
		if(isHql){
			c.replace("fetch", "");
		}
		return c;
	}
	
	
	
	@Override
	public T add(T t) {
		this.getSession().save(t);
		return t;
	}

	@Override
	public void update(T t) {
		this.getSession().update(t);
	}

	@Override
	public void delete(int id) {
		this.getSession().delete(this.load(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public T load(int id) {
		return (T) this.getSession().load(getClz(), id);
	}

	@Override
	public List<T> list(String hql, Object[] args) {
		return this.listByAlias(hql, args, null);
	}

	@Override
	public List<T> list(String hql, Object arg) {
		return this.list(hql, new Object[]{arg});
	}

	@Override
	public List<T> list(String hql) {
		return this.list(hql, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listByAlias(String hql, Object[] args, Map<String, Object> alias) {
		//先进行排序
		hql = initsort(hql);
		Query query = this.getSession().createQuery(hql);
		
		//塞入别名进行查询
		setAliasParameter(query, alias);
		
		//塞入参数进行查询
		setParameter(query, args);
		
		return query.list();
	}

	@Override
	public List<T> listByAlias(String hql, Object arg, Map<String, Object> alias) {
		return this.listByAlias(hql, new Object[]{arg}, alias);
	}

	@Override
	public List<T> listByAlias(String hql, Map<String, Object> alias) {
		return this.listByAlias(hql, null, alias);
	}

	@Override
	public Pager<T> find(String hql, Object[] args) {
		return this.findByAlias(hql, args, null);
	}

	@Override
	public Pager<T> find(String hql, Object arg) {
		return this.find(hql, new Object[]{arg});
	}

	@Override
	public Pager<T> find(String hql) {
		return this.find(hql, null);
	}

	@Override
	public Pager<T> findByAlias(String hql, Object[] args, Map<String, Object> alias) {
		hql = initsort(hql);
		String cq = getCountHql(hql,true);
		
		Query query = getSession().createQuery(hql);
		Query cquery = getSession().createQuery(cq);
		
		setAliasParameter(query, alias);
		setAliasParameter(cquery, alias);
		
		setParameter(query, args);
		setParameter(cquery, args);
		
		Pager<T> pages = new Pager<T>();
		setPagers(cquery, pages);
		List<T> datas = query.list();
		pages.setDatas(datas);
		Long total = (Long) cquery.uniqueResult();
		pages.setTotal(total);
		return pages;
	}

	@Override
	public Pager<T> findByAlias(String hql, Object arg, Map<String, Object> alias) {
		return this.findByAlias(hql, new Object[]{arg}, alias);
	}

	@Override
	public Pager<T> findByAlias(String hql, Map<String, Object> alias) {
		return this.findByAlias(hql, null, alias);
	}

	@Override
	public Object queryObject(String hql, Object[] args) {
		return this.queryObjectByAlias(hql, args, null);
	}

	@Override
	public Object queryObject(String hql, Object arg) {
		return this.queryObject(hql, new Object[]{arg});
	}

	@Override
	public Object queryObject(String hql) {
		return this.queryObject(hql, null);
	}

	@Override
	public Object queryObjectByAlias(String hql, Object[] args, Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, args);
		return query.uniqueResult();
	}

	@Override
	public Object queryObjectByAlias(String hql, Object arg, Map<String, Object> alias) {
		return this.queryObjectByAlias(hql, new Object[]{arg}, alias);
	}

	@Override
	public Object queryObjectByAlias(String hql, Map<String, Object> alias) {
		return this.queryObjectByAlias(hql, null, alias);
	}

	@Override
	public void updateByHql(String hql, Object[] args) {
		Query query = this.getSession().createQuery(hql);
		setParameter(query, args);
		query.executeUpdate();
	}

	@Override
	public void updateByHql(String hql, Object arg) {
		this.updateByHql(hql, new Object[]{arg});
	}

	@Override
	public void updateByHql(String hql) {
		this.updateByHql(hql, null);
	}

	@Override
	public <N> List<N> listBySql(String sql, Object[] args, Class<?> clz, Boolean hasEntity) {
		return this.listByAliasSql(sql, args, clz, hasEntity, null);
	}

	@Override
	public <N> List<N> listBySql(String sql, Object arg, Class<?> clz, Boolean hasEntity) {
		return this.listBySql(sql, new Object[]{arg}, clz, hasEntity);
	}

	@Override
	public <N> List<N> listBySql(String sql, Class<?> clz, Boolean hasEntity) {
		return this.listBySql(sql, null, clz, hasEntity);
	}

	@Override
	public <N> List<N> listByAliasSql(String sql, Object[] args, Class<?> clz, Boolean hasEntity,
			Map<String, Object> alias) {
		sql = initsort(sql);
		SQLQuery sq = getSession().createSQLQuery(sql);
		setAliasParameter(sq, alias);
		setParameter(sq, args);
		if(hasEntity){
			sq.addEntity(clz);
		}else{
			sq.setResultTransformer(Transformers.aliasToBean(clz));
		}
		return sq.list();
	}

	@Override
	public <N> List<N> listByAliasSql(String sql, Object arg, Class<?> clz, Boolean hasEntity,
			Map<String, Object> alias) {
		return this.listByAliasSql(sql, new Object[]{arg}, clz, hasEntity, alias);
	}

	@Override
	public <N> List<N> listByAliasSql(String sql, Class<?> clz, Boolean hasEntity, Map<String, Object> alias) {
		return this.listByAliasSql(sql, null, clz, hasEntity, alias);
	}

	@Override
	public <N> Pager<N> findBySql(String sql, Object[] args, Class<?> clz, Boolean hasEntity) {
		return this.findByAliasSql(sql, args, clz, hasEntity, null);
	}

	@Override
	public <N> Pager<N> findBySql(String sql, Object arg, Class<?> clz, Boolean hasEntity) {
		return this.findBySql(sql, new Object[]{arg}, clz, hasEntity);
	}

	@Override
	public <N> Pager<N> findBySql(String sql, Class<?> clz, Boolean hasEntity) {
		return this.findBySql(sql, null, clz, hasEntity);
	}

	@Override
	public <N> Pager<N> findByAliasSql(String sql, Object[] args, Class<?> clz, Boolean hasEntity,
			Map<String, Object> alias) {
		sql = initsort(sql);
		String cq = getCountHql(sql, false);
		
		SQLQuery sq = getSession().createSQLQuery(sql);
		SQLQuery cquery = getSession().createSQLQuery(cq);
		
		setAliasParameter(sq, alias);
		setAliasParameter(cquery, alias);
		
		setParameter(sq, args);
		setParameter(cquery, args);
		
		Pager<N> pages = new Pager<N>();
		setPagers(cquery, pages);
		if(hasEntity){
			sq.addEntity(clz);
		}else{
			sq.setResultTransformer(Transformers.aliasToBean(clz));
		}
		List<N> datas = sq.list();
		pages.setDatas(datas);
		Long total = ((BigInteger)cquery.uniqueResult()).longValue();
		pages.setTotal(total);
		return pages;
	}

	@Override
	public <N> Pager<N> findByAliasSql(String sql, Object arg, Class<?> clz, Boolean hasEntity,
			Map<String, Object> alias) {
		return this.findByAliasSql(sql, new Object[]{arg}, clz, hasEntity, alias);
	}

	@Override
	public <N> Pager<N> findByAliasSql(String sql, Class<?> clz, Boolean hasEntity, Map<String, Object> alias) {
		return this.findByAliasSql(sql, null, clz, hasEntity, alias);
	}
	
}

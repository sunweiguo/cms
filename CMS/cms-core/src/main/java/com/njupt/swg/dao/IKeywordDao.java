package com.njupt.swg.dao;

import java.util.List;

import com.njupt.swg.baseDao.IBaseDao;
import com.njupt.swg.model.Keyword;
import com.njupt.swg.model.Pager;


public interface IKeywordDao extends IBaseDao<Keyword> {
//	public void addKeyword(String keyword);
//	public void deleteKeyword(int id);
//	public void updateKeywordTime(int id);
//	public Keyword loadKeyword(int id);
	
	public void addOrUpdate(String name);
	//获取主要的关键字，引用次数大于等于num次的关键字
	public List<Keyword> listChiefKeyword(int num);
	//查找没有使用的关键字
	public Pager<Keyword> findNoUserKeyword();
	//清空没有使用的关键字
	public void clearNoUseKeyword();
	//查找正在被引用的关键字
	public List<Keyword> findUseKeyword();
	public List<Keyword> listKeywordByCon(String con);
}

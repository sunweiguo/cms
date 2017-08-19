package com.njupt.swg.service;

import java.util.List;

import com.njupt.swg.model.Keyword;
import com.njupt.swg.model.Pager;

public interface IKeywordService {
	//获取引用次数大于等于num次的关键字
	public List<Keyword> getKeywordByTimes(int num);
	//获取最大的几个关键字
	public List<Keyword> getMaxTimesKeyword(int num);
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

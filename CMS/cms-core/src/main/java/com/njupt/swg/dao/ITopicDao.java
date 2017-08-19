package com.njupt.swg.dao;

import com.njupt.swg.baseDao.IBaseDao;
import com.njupt.swg.model.Pager;
import com.njupt.swg.model.Topic;


public interface ITopicDao extends IBaseDao<Topic> {
	public Pager<Topic> find(Integer cid,String title,Integer status);
	public Pager<Topic> find(Integer uid,Integer cid,String title,Integer status);
	public Pager<Topic> searchTopicByKeyword(String keyword);
	public Pager<Topic> searchTopic(String con);
	public Pager<Topic> findRecommendTopic(Integer cid);
}

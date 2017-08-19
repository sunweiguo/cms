package com.njupt.swg.dao;

import org.springframework.stereotype.Repository;

import com.njupt.swg.baseDao.BaseDao;
import com.njupt.swg.model.Pager;
import com.njupt.swg.model.Topic;

import javassist.compiler.ast.Keyword;

@Repository("topicDao")
public class TopicDao extends BaseDao<Topic> implements ITopicDao {

	@Override
	public Pager<Topic> find(Integer cid, String title, Integer status) {
		return this.find(null, cid, title, status);
	}

	@Override
	public Pager<Topic> find(Integer uid, Integer cid, String title, Integer status) {
		String hql = "select t from Topic t where 1=1";
		if(status!=null){
			hql += " and t.status="+status;
		}
		if(title!=null){
			hql += " and t.title like '%"+title+"%'";
		}
		if(uid!=null&&uid>0){
			hql += " and t.user.id="+uid;
		}
		if(cid!=null&&cid>0){
			hql += " and t.channel.id="+cid;
		}
		return this.find(hql);
	}

	@Override
	public Pager<Topic> searchTopicByKeyword(String keyword) {
		String hql = "select t from Topic t where t.status=1 and t.keyword like '%"+keyword+"%'";
		return this.find(hql);
	}

	@Override
	public Pager<Topic> searchTopic(String con) {
		String hql = "select t from Topic t where t.status=1 and "
				+ "(t.title like '%"+con+"%' or t.content like '%"+con+"%' or "
						+ "t.summary like '%"+con+"%')";
		return this.find(hql);
	}

	@Override
	public Pager<Topic> findRecommendTopic(Integer cid) {
		String hql = "select t from Topic t where t.status=1 and t.recommend=1";
		if(cid==null || cid==0){
			return this.find(hql);
		}else{
			hql += " and t.channel.id=?";
			return this.find(hql,cid);
		}
	}

	
}

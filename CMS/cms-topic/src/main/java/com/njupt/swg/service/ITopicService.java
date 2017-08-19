package com.njupt.swg.service;

import com.njupt.swg.model.Pager;
import com.njupt.swg.model.Topic;

public interface ITopicService {
	//添加带有附件信息的文章
	public void add(Topic topic,int cid,int uid,Integer[] aids);
	//添加不带有附件信息的文章
	public void add(Topic topic,int cid,int uid);
	//删除文章，先删除附件，再删除附件的文件对象
	public void delete(int tid);
	//更新文章，带附件
	public void update(Topic topic,int cid,Integer[] aids);
	//更新文章，不带附件
	public void update(Topic topic,int cid);
	//加载文章对象
	public Topic load(int tid);
	//更新文章状态
	public void updateStatus(int tid);

	public Pager<Topic> find(Integer cid,String title,Integer status);
	public Pager<Topic> find(Integer uid,Integer cid,String title,Integer status);
	public Pager<Topic> searchTopicByKeyword(String keyword);
	public Pager<Topic> searchTopic(String con);
	public Pager<Topic> findRecommendTopic(Integer cid);
}

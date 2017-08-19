package com.njupt.swg.service;


import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.njupt.swg.dao.IAttachmentDao;
import com.njupt.swg.dao.IChannelDao;
import com.njupt.swg.dao.ITopicDao;
import com.njupt.swg.dao.IUserDao;
import com.njupt.swg.model.Attachment;
import com.njupt.swg.model.Channel;
import com.njupt.swg.model.CmsException;
import com.njupt.swg.model.Pager;
import com.njupt.swg.model.Topic;
import com.njupt.swg.model.User;

@Service("topicService")
public class TopicService implements ITopicService {
	private ITopicDao topicDao;
	private IAttachmentDao attachmentDao;
	private IChannelDao channelDao;
	private IUserDao userDao;
	public IChannelDao getChannelDao() {
		return channelDao;
	}
	@Inject
	public void setChannelDao(IChannelDao channelDao) {
		this.channelDao = channelDao;
	}
	public IUserDao getUserDao() {
		return userDao;
	}
	@Inject
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	public IAttachmentDao getAttachmentDao() {
		return attachmentDao;
	}
	@Inject
	public void setAttachmentDao(IAttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}
	public ITopicDao getTopicDao() {
		return topicDao;
	}
	@Inject
	public void setTopicDao(ITopicDao topicDao) {
		this.topicDao = topicDao;
	}

	@Override
	public void add(Topic topic, int cid, int uid, Integer[] aids) {
		Channel c = channelDao.load(cid);
		User u = userDao.load(uid);
		if(c==null||u==null)
			throw new CmsException("要添加的文章必须有用户和栏目");
		System.out.println(u.getNickname()+":"+c.getName());
		topic.setAuthor(u.getNickname());
		topic.setChannel(c);
		topic.setUser(u);
		topic.setCreateDate(new Date());
		topicDao.add(topic);
		if(aids!=null){
			for(Integer aid:aids){
				Attachment a = attachmentDao.load(aid);
				if(a==null) continue;
				a.setTopic(topic);
			}
		}
	}

	@Override
	public void add(Topic topic, int cid, int uid) {
		this.add(topic, cid, uid, null);
	}

	@Override
	public void delete(int tid) {
		List<Attachment> atts = attachmentDao.listByTopic(tid);
		attachmentDao.deleteByTopic(tid);
		topicDao.delete(tid);
		//删除硬件上的文件
		for(Attachment a:atts){
			AttachmentService.deleteAttachFiles(a);
		}
	}

	@Override
	public void update(Topic topic, int cid, Integer[] aids) {
		Channel c = channelDao.load(cid);
		if(c==null)
			throw new CmsException("要添加的文章必须有栏目");
		System.out.println(topic);
		topic.setChannel(c);
		topicDao.update(topic);
		if(aids!=null){
			for(Integer aid:aids){
				Attachment a = attachmentDao.load(aid);
				if(a==null) continue;
				a.setTopic(topic);
			}
		}
	}

	@Override
	public void update(Topic topic, int cid) {
		this.update(topic, cid, null);
	}

	@Override
	public Topic load(int tid) {
		return topicDao.load(tid);
	}

	@Override
	public Pager<Topic> find(Integer cid, String title, Integer status) {
		return topicDao.find(cid,title,status);
	}

	@Override
	public Pager<Topic> find(Integer uid, Integer cid, String title, Integer status) {
		return topicDao.find(uid,cid,title,status);
	}

	@Override
	public Pager<Topic> searchTopicByKeyword(String keyword) {
		return topicDao.searchTopicByKeyword(keyword);
	}

	@Override
	public Pager<Topic> searchTopic(String con) {
		return topicDao.searchTopic(con);
	}

	@Override
	public Pager<Topic> findRecommendTopic(Integer cid) {
		return topicDao.findRecommendTopic(cid);
	}
	@Override
	public void updateStatus(int tid) {
		Topic t  = topicDao.load(tid);
		if(t.getStatus()==0)
			t.setStatus(1);
		else
			t.setStatus(0);
		topicDao.update(t);
	}

}

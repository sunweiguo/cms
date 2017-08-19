package com.njupt.swg.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.njupt.swg.baseDao.BaseDao;
import com.njupt.swg.model.Attachment;
import com.njupt.swg.model.Pager;

@Repository("attachmentDao")
public class AttachmentDao extends BaseDao<Attachment> implements IAttachmentDao {

	@Override
	public Pager<Attachment> findNoUseAttachment() {
		String hql = "select a from Attachment a where a.topic is null";
		return this.find(hql);
	}

	@Override
	public void clearNoUseAttachment() {
		String hql = "delete Attachment a where a.topic is null";
		this.updateByHql(hql);
	}

	@Override
	public void deleteByTopic(int tid) {
		String hql = "delete Attachment a where a.topic.id=?";
		this.updateByHql(hql, tid);
	}

	@Override
	public List<Attachment> listByTopic(int tid) {
		String hql = "select a from Attachment a where a.topic.id=?";
		return this.list(hql,tid);
	}

	@Override
	public List<Attachment> listIndexPic(int num) {
		String hql = "select a from Attachment a where a.isIndexPic=? and a.topic.status=1";
		return this.getSession().createQuery(hql).setParameter(0,1).
				setFirstResult(0).setMaxResults(num).list();
	}

	@Override
	public Pager<Attachment> findChannelPic(int cid) {
		String hql = "select a from Attachment a where a.topic.channel.id=? and a.topic.status=1 and a.id=a.topic.channelPicId";
		return this.find(hql,cid);
	}

	@Override
	public List<Attachment> listAttachmentByTopic(int tid) {
		String hql = "select a from Attachment a where a.topic.id=? and a.isAttach = 1";
		return this.list(hql,tid);
	}
	
	@Override
	public long findNoUseAttachmentNum() {
		String hql = "select count(*) from Attachment a where a.topic is null";
		return (Long)this.getSession().createQuery(hql).uniqueResult();
	}
	
	@Override
	public Pager<Attachment> listAllIndexPic() {
		String hql = "select a from Attachment a where a.isImg=? and a.topic.status=1";
		return this.find(hql,1);
	}

}

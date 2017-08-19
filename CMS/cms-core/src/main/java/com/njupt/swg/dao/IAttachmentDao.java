package com.njupt.swg.dao;

import java.util.List;

import com.njupt.swg.baseDao.IBaseDao;
import com.njupt.swg.model.Attachment;
import com.njupt.swg.model.Pager;

public interface IAttachmentDao extends IBaseDao<Attachment> {
	public Pager<Attachment> findNoUseAttachment();
	public void clearNoUseAttachment();
	public void deleteByTopic(int tid);
	public List<Attachment> listByTopic(int tid);
	public List<Attachment> listIndexPic(int num);
	public Pager<Attachment> findChannelPic(int cid);
	public List<Attachment> listAttachmentByTopic(int tid);
	public long findNoUseAttachmentNum();
	public Pager<Attachment> listAllIndexPic();
}

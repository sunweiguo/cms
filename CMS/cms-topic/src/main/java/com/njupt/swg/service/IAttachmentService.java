package com.njupt.swg.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.njupt.swg.model.Attachment;
import com.njupt.swg.model.Pager;

public interface IAttachmentService {
	public void add(Attachment a,InputStream is) throws IOException;
	public void delete(int id);
	public Attachment load(int id);
	
	public Pager<Attachment> findNoUseAttachment();
	public long findNoUseAttachmentNum();
	public void clearNoUseAttachment();
	public List<Attachment> listByTopic(int tid);
	public List<Attachment> listIndexPic(int num);
	public Pager<Attachment> findChannelPic(int cid);
	public List<Attachment> listAttachmentByTopic(int tid);
	
	public void updateIndexPic(int aid);
	public void updateAttachInfo(int aid);
	public Pager<Attachment> listAllPic();
	
}

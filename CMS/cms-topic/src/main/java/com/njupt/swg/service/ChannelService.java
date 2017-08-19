package com.njupt.swg.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.njupt.swg.dao.IChannelDao;
import com.njupt.swg.model.Channel;
import com.njupt.swg.model.ChannelTree;
import com.njupt.swg.model.CmsException;
import com.njupt.swg.model.SystemContext;

@Service("channelService")
public class ChannelService implements IChannelService {
	private IChannelDao channelDao;
	public IChannelDao getChannelDao() {
		return channelDao;
	}
	@Inject
	public void setChannelDao(IChannelDao channelDao) {
		this.channelDao = channelDao;
	}

	@Override
	public void add(Channel channel, Integer pid) {
		int orders = this.channelDao.getOrderByParent(pid);
		if(pid != null && pid>0){
			Channel pc = channelDao.load(pid);
			if(pc == null)
				throw new CmsException("要添加的栏目的父类对象不正确");
			channel.setParent(pc);
		}
		channel.setOrders(orders+1);
		this.channelDao.add(channel);
	}

	@Override
	public void update(Channel channel) {
		this.channelDao.update(channel);
	}

	@Override
	public void deleteChannel(int id) {
		//1、判断是否有子栏目
		List<Channel> cs = channelDao.listByParent(id);
		if(cs!=null&&cs.size()>0){
			throw new CmsException("下面还有子栏目，不能删除");
		}
		//2、判断是否有文章
		//3、删除和组的关联关系
		channelDao.delete(id);
	}

	@Override
	public void clearTopic(int id) {
		// TODO
	}

	@Override
	public Channel load(int id) {
		return this.channelDao.load(id);
	}

	@Override
	public List<Channel> listByParent(Integer pid) {
		String sort = SystemContext.getSort();
		if(sort == null || "".equals(sort.trim())){
			SystemContext.setSort("orders");
			SystemContext.setOrder("asc");
		}
		return channelDao.listByParent(pid);
	}
	@Override
	public List<ChannelTree> generateTree() {
		return channelDao.generateTree();
	}
	@Override
	public List<Channel> listPublishChannels() {
		return this.channelDao.listPublishChannels();
	}

}

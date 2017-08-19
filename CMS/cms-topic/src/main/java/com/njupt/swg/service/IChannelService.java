package com.njupt.swg.service;

import java.util.List;

import com.njupt.swg.model.Channel;
import com.njupt.swg.model.ChannelTree;

public interface IChannelService {
	//添加栏目
	public void add(Channel channel,Integer pid);
	
	public void update(Channel channel);
	
	public void deleteChannel(int id);
	
	public void clearTopic(int id);
	
	public Channel load(int id);
	
	//根据父亲id加载栏目
	public List<Channel> listByParent(Integer pid);
	
	public List<ChannelTree> generateTree();
	
	public List<Channel> listPublishChannels();
}

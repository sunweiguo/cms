package com.njupt.swg.dao;

import java.util.List;

import com.njupt.swg.baseDao.IBaseDao;
import com.njupt.swg.model.Channel;
import com.njupt.swg.model.ChannelTree;

public interface IChannelDao extends IBaseDao<Channel> {
	//根据父id获取所有的子栏目
	public List<Channel> listByParent(Integer pid);
	//获取子栏目中最大的排序号
	public int getOrderByParent(Integer pid);
	//生成一颗完整的树
	public List<ChannelTree> generateTree();
	//根据父对象获取子类栏目并且生成树
	//public List<ChannelTree> generateTreeByParent(Integer pid);
	public List<Channel> listPublishChannels();
}

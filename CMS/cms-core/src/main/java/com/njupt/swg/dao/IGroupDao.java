package com.njupt.swg.dao;

import java.util.List;

import com.njupt.swg.baseDao.IBaseDao;
import com.njupt.swg.model.Channel;
import com.njupt.swg.model.ChannelTree;
import com.njupt.swg.model.Group;
import com.njupt.swg.model.GroupChannel;
import com.njupt.swg.model.Pager;

public interface IGroupDao extends IBaseDao<Group> {
	public void deleteGroupUsers(int gid);
	
	public List<Group> listGroup();
	
	public Pager<Group> findGroup();
	
	public void addGroupChannel(Group group,Channel channel);
	
	public void deleteGroupChannel(int gid,int cid);
	
	public void clearGroupChannel(int gid);
	
	public GroupChannel loadGroupChannel(int gid,int cid);
	
	public List<Integer> listGroupChannelIds(int gid);
	//获取某个组的栏目树
	public List<ChannelTree> generateGroupChannelTree(int gid);
	
	public List<ChannelTree> generateUserChannelTree(int uid);
}

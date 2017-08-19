package com.njupt.swg.service;

import java.util.List;

import com.njupt.swg.baseDao.IBaseDao;
import com.njupt.swg.model.Channel;
import com.njupt.swg.model.ChannelTree;
import com.njupt.swg.model.Group;
import com.njupt.swg.model.GroupChannel;
import com.njupt.swg.model.Pager;
import com.njupt.swg.model.User;

public interface IGroupService extends IBaseDao<Group> {
	public void delete(int gid);
	public Group load(int gid);
	public Group add(Group group);
	public void update(Group group);
	public List<Group> listGroup();
	public Pager<Group> findGroup();
	public List<User> listGroupUsers(int gid);
	public void deleteGroupUsers(int gid);
	
	public void addGroupChannel(int gid,int cid);
	
	public void deleteGroupChannel(int gid,int cid);
	
	public void clearGroupChannel(int gid);
	
	public GroupChannel loadGroupChannel(int gid,int cid);
	
	public List<Integer> listGroupChannelIds(int gid);
	//获取某个组的栏目树
	public List<ChannelTree> generateGroupChannelTree(int gid);
	
	public List<ChannelTree> generateUserChannelTree(int uid);
}

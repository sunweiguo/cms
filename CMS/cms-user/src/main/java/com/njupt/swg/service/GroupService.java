package com.njupt.swg.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.njupt.swg.baseDao.BaseDao;
import com.njupt.swg.dao.IChannelDao;
import com.njupt.swg.dao.IGroupDao;
import com.njupt.swg.dao.IUserDao;
import com.njupt.swg.model.Channel;
import com.njupt.swg.model.ChannelTree;
import com.njupt.swg.model.CmsException;
import com.njupt.swg.model.Group;
import com.njupt.swg.model.GroupChannel;
import com.njupt.swg.model.Pager;
import com.njupt.swg.model.User;

@Service("groupService")
public class GroupService extends BaseDao<Group> implements IGroupService {
	private IGroupDao groupDao;
	private IUserDao userDao;
	private IChannelDao channelDao;

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
	public IGroupDao getGroupDao() {
		return groupDao;
	}
	@Inject
	public void setGroupDao(IGroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public void delete(int gid) {
		List<User> users = userDao.listGroupUsers(gid);
		if(users!=null && users.size()>0)
			throw new CmsException("这个分组内还有用户，不能删除");
		groupDao.delete(gid);
	}

	@Override
	public Group load(int gid) {
		return this.groupDao.load(gid);
	}

	@Override
	public Group add(Group group) {
		return this.groupDao.add(group);
	}

	@Override
	public void update(Group group) {
		this.groupDao.update(group);
	}

	@Override
	public List<Group> listGroup() {
		return this.groupDao.listGroup();
	}

	@Override
	public Pager<Group> findGroup() {
		return this.groupDao.findGroup();
	}
	@Override
	public List<User> listGroupUsers(int gid) {
		return this.userDao.listGroupUsers(gid);
	}
	@Override
	public void deleteGroupUsers(int gid) {
		this.groupDao.deleteGroupUsers(gid);
	}
	@Override
	public void addGroupChannel(int gid, int cid) {
		Group g = this.groupDao.load(gid);
		Channel c = this.channelDao.load(cid);
		if(g==null || c==null)
			throw new CmsException("对象不存在");
		groupDao.addGroupChannel(g, c);
	}
	@Override
	public void deleteGroupChannel(int gid, int cid) {
		this.deleteGroupChannel(gid, cid);
	}
	@Override
	public void clearGroupChannel(int gid) {
		groupDao.clearGroupChannel(gid);
	}
	@Override
	public GroupChannel loadGroupChannel(int gid, int cid) {
		return this.groupDao.loadGroupChannel(gid, cid);
	}
	@Override
	public List<Integer> listGroupChannelIds(int gid) {
		return this.groupDao.listGroupChannelIds(gid);
	}
	@Override
	public List<ChannelTree> generateGroupChannelTree(int gid) {
		return groupDao.generateGroupChannelTree(gid);
	}
	@Override
	public List<ChannelTree> generateUserChannelTree(int uid) {
		return groupDao.generateUserChannelTree(uid);
	}

}

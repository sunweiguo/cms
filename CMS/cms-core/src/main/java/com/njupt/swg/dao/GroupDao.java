package com.njupt.swg.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.njupt.swg.baseDao.BaseDao;
import com.njupt.swg.model.Channel;
import com.njupt.swg.model.ChannelTree;
import com.njupt.swg.model.Group;
import com.njupt.swg.model.GroupChannel;
import com.njupt.swg.model.Pager;

@Repository("groupDao")
public class GroupDao extends BaseDao<Group> implements IGroupDao {

	@Override
	public void deleteGroupUsers(int gid) {
		this.updateByHql("delete UserGroup ug where ug.group.id=?",gid);
	}

	@Override
	public List<Group> listGroup() {
		return this.list("from Group");
	}

	@Override
	public Pager<Group> findGroup() {
		return this.find("from Group");
	}

	@Override
	public void addGroupChannel(Group group, Channel channel) {
		GroupChannel gc = this.loadGroupChannel(group.getId(),channel.getId());
		if(gc == null)
			return;
		gc = new GroupChannel();
		gc.setGroup(group);
		gc.setChannel(channel);
		this.getSession().save(gc);
	}

	@Override
	public GroupChannel loadGroupChannel(int gid, int cid) {
		return (GroupChannel) this.queryObject("from GroupChannel where group.id=? and channel.id=?",new Object[]{gid,cid});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> listGroupChannelIds(int gid) {
		String hql = "select gc.channel.id from GroupChannel gc where gc.group.id=?";
		return this.getSession().createQuery(hql).setParameter(0, gid).list();
	}

	@Override
	public List<ChannelTree> generateGroupChannelTree(int gid) {
		String sql = "select c.id as id,c.name as name,c.pid as pid"
				+ " from t_group_channel gc left join t_channel c on (gc.c_id=c.id) "
				+ "where gc.g_id=?";
		List<ChannelTree> cts = this.listBySql(sql,gid,ChannelTree.class, false);
		cts.add(0,new ChannelTree(0,"网站系统栏目",-1));
		for(ChannelTree ct:cts){
			if(ct.getPid()==null)
				ct.setPid(0);
		}
		return cts;
	}

	@Override
	public List<ChannelTree> generateUserChannelTree(int uid) {
		String sql = "select distinct c.id as id,c.name as name,c.pid as pid"
				+ " from t_group_channel gc left join t_channel c on (gc.c_id=c.id) "
				+ "left join t_user_group ug on (ug.g_id=gc.g_id) where ug.u_id=?";
		List<ChannelTree> cts = this.listBySql(sql,uid,ChannelTree.class, false);
		cts.add(0,new ChannelTree(0,"网站系统栏目",-1));
		for(ChannelTree ct:cts){
			if(ct.getPid()==null)
				ct.setPid(0);
		}
		return cts;
	}

	@Override
	public void deleteGroupChannel(int gid, int cid) {
		this.updateByHql("delete GroupChannel gc where gc.group.id=? and gc.channel.id=?",new Object[]{gid,cid});
	}

	@Override
	public void clearGroupChannel(int gid) {
		this.updateByHql("delete GroupChannel gc where gc.group.id=?", gid);
	}
}

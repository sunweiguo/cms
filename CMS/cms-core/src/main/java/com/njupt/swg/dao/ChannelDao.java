package com.njupt.swg.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.njupt.swg.baseDao.BaseDao;
import com.njupt.swg.model.Channel;
import com.njupt.swg.model.ChannelTree;
import com.njupt.swg.model.ChannelType;

@Repository("channelDao")
public class ChannelDao extends BaseDao<Channel> implements IChannelDao {

	@Override
	public List<Channel> listByParent(Integer pid) {
		System.out.println("channeldao pid="+pid);
		String hql = "select c from Channel c where c.parent.id="+pid;
		if(pid == null || pid == 0)
			hql = "select c from Channel c where c.parent is null";
		return this.list(hql);
	}

	@Override
	public int getOrderByParent(Integer pid) {
		String hql = "select max(c.orders) from Channel c where c.parent.id="+pid;
		if(pid == null || pid == 0)
			hql = "select max(c.orders) from Channel c where c.parent is null";
		//这里要考虑order为null的情况
		Object obj = this.queryObject(hql);
		if(obj == null)
			return 0;
		return (Integer)obj;
	}

	@Override
	public List<ChannelTree> generateTree() {
		String sql = "select id,name,pid from t_channel order by orders";
		List<ChannelTree> cts = this.listBySql(sql, ChannelTree.class, false);
		cts.add(0,new ChannelTree(0,"网站系统栏目",-1));
		for(ChannelTree ct:cts){
			if(ct.getPid()==null)
				ct.setPid(0);
		}
		return cts;
	}

	@Override
	public List<Channel> listPublishChannels() {
		String hql = "select c from Channel c where c.status=1 and c.type!="+ChannelType.NAV_CHANNEL.ordinal();
		return this.list(hql);
	}

}

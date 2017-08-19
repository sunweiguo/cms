package com.njupt.swg.dao;

import java.util.List;

import com.njupt.swg.baseDao.IBaseDao;
import com.njupt.swg.model.Role;

public interface IRoleDao extends IBaseDao<Role> {
	public void deleteRoleUsers(int rid);
	
	public List<Role> listRole();
}

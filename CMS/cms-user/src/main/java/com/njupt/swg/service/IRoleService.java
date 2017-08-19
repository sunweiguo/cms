package com.njupt.swg.service;

import java.util.List;

import com.njupt.swg.baseDao.IBaseDao;
import com.njupt.swg.model.Role;
import com.njupt.swg.model.User;

public interface IRoleService extends IBaseDao<Role> {
	public void delete(int rid);
	public Role load(int rid);
	public Role add(Role role);
	public void update(Role role);
	public List<Role> listRole();
	public void deleteRoleUsers(int rid);
	public List<User> listRoleUsers(int rid);
}

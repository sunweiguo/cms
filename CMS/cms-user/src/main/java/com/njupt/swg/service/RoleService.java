package com.njupt.swg.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.njupt.swg.baseDao.BaseDao;
import com.njupt.swg.dao.IRoleDao;
import com.njupt.swg.dao.IUserDao;
import com.njupt.swg.model.CmsException;
import com.njupt.swg.model.Role;
import com.njupt.swg.model.User;

@Service("roleService")
public class RoleService extends BaseDao<Role> implements IRoleService {
	private IRoleDao roleDao;
	private IUserDao userDao;
	
	public IRoleDao getRoleDao() {
		return roleDao;
	}
	@Inject
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}
	@Inject
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void delete(int rid) {
		List<User> users = this.userDao.listRoleUsers(rid);
		if(users!=null && users.size()>0)
			throw new CmsException("该角色内还有用户，不能删除");
		this.roleDao.delete(rid);
	}

	@Override
	public Role load(int rid) {
		return this.roleDao.load(rid);
	}

	@Override
	public Role add(Role role) {
		return this.roleDao.add(role);
	}

	@Override
	public void update(Role role) {
		this.roleDao.update(role);
	}

	@Override
	public List<Role> listRole() {
		return this.roleDao.listRole();
	}
	@Override
	public void deleteRoleUsers(int rid) {
		this.roleDao.deleteRoleUsers(rid);
	}
	@Override
	public List<User> listRoleUsers(int rid) {
		return this.userDao.listRoleUsers(rid);
	}

}

package com.njupt.swg.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.njupt.swg.baseDao.BaseDao;
import com.njupt.swg.model.Role;

@Repository("roleDao")
public class RoleDao extends BaseDao<Role> implements IRoleDao {

	@Override
	public void deleteRoleUsers(int rid) {
		this.updateByHql("delete UserRole ur where ur.role.id=?",rid);
	}

	@Override
	public List<Role> listRole() {
		return this.list("from Role");
	}
}

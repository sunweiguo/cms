package com.njupt.swg.dao;

import org.springframework.stereotype.Repository;

import com.njupt.swg.model.User;

@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao {
	
}

package com.exp.service;

import com.exp.base.BaseDao;
import com.exp.entities.User;

public interface UserService extends BaseDao<User> {

	void updateUser(User model);

	void updateInfo(User model);

	void changePwd(User model);


}

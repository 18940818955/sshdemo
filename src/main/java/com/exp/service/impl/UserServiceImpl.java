package com.exp.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.base.BaseDaoImpl;
import com.exp.entities.Role;
import com.exp.entities.User;
import com.exp.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends BaseDaoImpl<User> implements UserService {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateUser(User model) {
		Role baseRole = (Role) getSession().get(Role.class, 1);
		if (model.getId() != null) {
			User user = (User) getById(model.getId());
			user.setUserid(model.getUserid());
			user.setName(model.getName());
			user.setMobile(model.getMobile());
			user.setEmail(model.getEmail());
			user.setWeixinid(model.getWeixinid());
			user.getRoles().clear();
			Set<Role> roles = model.getRoles();
			if (((Role)new ArrayList(roles).get(0)).getId()!=null) {
				for (Role role : roles) {
					if(role.getId()!=null)
					roles.add(role);
				}
				user.setRoles(roles);
			}
			user.getRoles().add(baseRole);
			update(user);
		} else {
			Set<Role> roles = new HashSet<>();
			roles.add(baseRole);
			if (model.getRoles().size()>0)
				for (Role role : model.getRoles()) {
					if(role.getId()!=null)
					roles.add(role);
				}
			model.setRoles(roles);
			model.setPassword("111");
			save(model);
		}
	}

	public void updateInfo(User model) {
		// TODO Auto-generated method stub
		User user = getById(model.getId());
		user.setName(model.getName());
		user.setMobile(model.getMobile());
		user.setWeixinid(model.getWeixinid());
		user.setEmail(model.getEmail());
		saveOrUpdate(user);
	}

	@Override
	public void changePwd(User model) {
		// TODO Auto-generated method stub
		User user = getById(model.getId());
		user.setPassword(model.getPassword());
		update(user);
	}
}

package com.exp.security;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exp.base.BaseDaoImpl;
import com.exp.entities.User;

@Service
public class MyUserDetailService extends BaseDaoImpl<User> implements UserDetailsService{
	public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        //用hibernate查询user表role字段。关联也可以。
        //可以返回User类型之后在转换成UserDetails类型ps:实体bean必须实现UserDetails接口，否则无法转换
        UserDetails user = findByUsername(username);
        return user;
    }
}

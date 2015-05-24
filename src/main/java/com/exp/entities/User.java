package com.exp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User implements UserDetails, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8646998060592948905L;
	/*
	 * 微信字段
	 */
	private String userid;// 企业内帐号（唯一）
	private String name;// 姓名
	private Integer department;// 部门
	private String position;// 职位
	private String mobile; // 手机
	private String email;// 邮箱
	private String weixinid;// 微信号 （三者不能同时为空）
	/*
	 * 数据库字段
	 */
	private Integer id;
	private String password;
	private String deleteFlag;// 删除
	private String activeFlag;// 激活位
	
	private Date createTime;
	private Date updateTime;
	@JsonIgnore
	private User createUser;
	@JsonIgnore
	private User updateUser;
	private Set<Role> roles;// 权限
	private Set<Product> products; 

	
	
	public User(String userid, String name, String email, String password,
			Set<Role> roles) {
		super();
		this.userid = userid;
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public User() {
		super();
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeixinid() {
		return weixinid;
	}

	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public User getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
		for (Role role : roles) {
			GrantedAuthority ga = new SimpleGrantedAuthority(role.getName());
			gas.add(ga);
		}
		return gas;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return userid;
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}

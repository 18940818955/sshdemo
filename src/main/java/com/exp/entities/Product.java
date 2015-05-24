package com.exp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 产品实体类
 * 
 * @author Administrator
 * @version 创建时间：2015年5月4日 下午3:03:04
 */
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5899052450104246287L;
	private Integer id;
	private String productName;
	private double price;// 单价

	private Date createTime;
	private Date updateTime;
	@JsonIgnore
	private User createUser;
	@JsonIgnore
	private User updateUser;
	@JsonIgnore
	private Set<User> users;

	public Product(String productName) {
		super();
		this.productName = productName;
	}

	public Product() {
		super();
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public User getCreateUser() {
		return createUser;
	}

	public Integer getId() {
		return id;
	}

	public double getPrice() {
		return price;
	}

	public String getProductName() {
		return productName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public User getUpdateUser() {
		return updateUser;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}

}

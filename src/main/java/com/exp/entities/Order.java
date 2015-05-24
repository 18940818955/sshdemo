package com.exp.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单实体类
 * 
 * @author Administrator
 * @version 创建时间：2015年5月4日 下午3:03:20
 */
public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5667023524551060871L;
	private String id;
	private Basedata status;// 状态
	private Date createTime;
	private Date updateTime;
	private double totalPrice;
	private String productName;
	private Date deliverTime;
	

	private User createUser;

	private User updateUser;

	public Date getCreateTime() {
		return createTime;
	}

	public User getCreateUser() {
		return createUser;
	}

	public Date getDeliverTime() {
		return deliverTime;
	}

	public String getId() {
		return id;
	}

	public String getProductName() {
		return productName;
	}
	public Basedata getStatus() {
		return status;
	}

	public double getTotalPrice() {
		return totalPrice;
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

	public void setDeliverTime(Date deliverTime) {
		this.deliverTime = deliverTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setStatus(Basedata status) {
		this.status = status;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}

}

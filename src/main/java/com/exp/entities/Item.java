package com.exp.entities;


public class Item {
	private Integer id;
	private double price;
	private double amount;//订货数量
	private double realAmount;//实际发货数量
	private double receiveAmount;//收货数量
	private Order order;
	private String productName;
	private String reason;//退货原因
	public double getAmount() {
		return amount;
	}
	public Integer getId() {
		return id;
	}
	public Order getOrder() {
		return order;
	}
	public double getPrice() {
		return price;
	}

	public String getProductName() {
		return productName;
	}

	public double getRealAmount() {
		return realAmount;
	}

	public String getReason() {
		return reason;
	}

	public double getReceiveAmount() {
		return receiveAmount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setRealAmount(double realAmount) {
		this.realAmount = realAmount;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setReceiveAmount(double receiveAmount) {
		this.receiveAmount = receiveAmount;
	}

}

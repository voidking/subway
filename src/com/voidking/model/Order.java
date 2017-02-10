package com.voidking.model;

import java.util.Date;

public class Order{
	private int id;
	private String orderNumber;
	private int userId;
	private String oneSite;
	private String twoSite;
	private int price;
	private int rePrice;
	private Date createAt;
	private String state;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getOneSite() {
		return oneSite;
	}
	public void setOneSite(String oneSite) {
		this.oneSite = oneSite;
	}
	public String getTwoSite() {
		return twoSite;
	}
	public void setTwoSite(String twoSite) {
		this.twoSite = twoSite;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getRePrice() {
		return rePrice;
	}
	public void setRePrice(int rePrice) {
		this.rePrice = rePrice;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(int id, String orderNumber, int userId, String oneSite, String twoSite, int price, int rePrice,
			Date createAt, String state) {
		super();
		this.id = id;
		this.orderNumber = orderNumber;
		this.userId = userId;
		this.oneSite = oneSite;
		this.twoSite = twoSite;
		this.price = price;
		this.rePrice = rePrice;
		this.createAt = createAt;
		this.state = state;
	}
	
}

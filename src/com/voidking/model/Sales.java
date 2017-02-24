package com.voidking.model;

import java.util.Date;

// 没有对应的表
public class Sales {

	private int sell;
	private int ret;
	private Date date;
	
	public int getSell() {
		return sell;
	}
	public void setSell(int sell) {
		this.sell = sell;
	}
	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Sales() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Sales(int sell, int ret) {
		super();
		this.sell = sell;
		this.ret = ret;
	}
	public Sales(int sell, int ret, Date date) {
		super();
		this.sell = sell;
		this.ret = ret;
		this.date = date;
	}
	
}

package com.voidking.model;

public class Stop {
	private int id;
	private String stopName;
	private String lineName;
	private int value;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStopName() {
		return stopName;
	}
	public void setStopName(String stopName) {
		this.stopName = stopName;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Stop() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Stop(int id, String stopName, String lineName, int value) {
		super();
		this.id = id;
		this.stopName = stopName;
		this.lineName = lineName;
		this.value = value;
	}
	
	
}

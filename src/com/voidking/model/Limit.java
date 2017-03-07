package com.voidking.model;

public class Limit {
	private int id;
	private String oneSite;
	private String twoSite;
	private int totalTickets;
	private int soldTickets;
	private int started;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getTotalTickets() {
		return totalTickets;
	}
	public void setTotalTickets(int totalTickets) {
		this.totalTickets = totalTickets;
	}
	public int getSoldTickets() {
		return soldTickets;
	}
	public void setSoldTickets(int soldTickets) {
		this.soldTickets = soldTickets;
	}
	public int getStarted() {
		return started;
	}
	public void setStarted(int started) {
		this.started = started;
	}
	
	public Limit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Limit(int id, String oneSite, String twoSite, int totalTickets, int soldTickets, int started) {
		super();
		this.id = id;
		this.oneSite = oneSite;
		this.twoSite = twoSite;
		this.totalTickets = totalTickets;
		this.soldTickets = soldTickets;
		this.started = started;
	}
	
}

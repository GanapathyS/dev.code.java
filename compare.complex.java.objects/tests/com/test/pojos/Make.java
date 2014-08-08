package com.test.pojos;

import java.util.Date;

import com.google.gson.Gson;

public class Make {
	private String companyName = null;
	private String location = null;
	private int numberEmployees = 0;
	private float costOfCompany = 0;
	private char tradingName;
	private boolean isGood;
	private long averageSalary;
	private double daysSinceOpened;
	private Date opened;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getNumberEmployees() {
		return numberEmployees;
	}
	public void setNumberEmployees(int numberEmployees) {
		this.numberEmployees = numberEmployees;
	}
	public float getCostOfCompany() {
		return costOfCompany;
	}
	public void setCostOfCompany(float costOfCompany) {
		this.costOfCompany = costOfCompany;
	}
	public char getTradingName() {
		return tradingName;
	}
	public void setTradingName(char tradingName) {
		this.tradingName = tradingName;
	}
	public boolean isGood() {
		return isGood;
	}
	public void setGood(boolean isGood) {
		this.isGood = isGood;
	}
	public long getAverageSalary() {
		return averageSalary;
	}
	public void setAverageSalary(long averageSalary) {
		this.averageSalary = averageSalary;
	}
	public double getDaysSinceOpened() {
		return daysSinceOpened;
	}
	public void setDaysSinceOpened(double daysSinceOpened) {
		this.daysSinceOpened = daysSinceOpened;
	}
	public Date getOpened() {
		return opened;
	}
	public void setOpened(Date opened) {
		this.opened = opened;
	}  
	
	@Override
	public String toString() {
		Gson objGson = new Gson();
		return objGson.toJson(this); 
	}	 
}

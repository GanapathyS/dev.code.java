package com.test.pojos;

public class Baggage {
	private boolean isCheckIn;
	private String makeBaggage;
	private long weight;
	
	public boolean isCheckIn() {
		return isCheckIn;
	}
	public void setCheckIn(boolean isCheckIn) {
		this.isCheckIn = isCheckIn;
	}
	public String getMakeBaggage() {
		return makeBaggage;
	}
	public void setMakeBaggage(String make) {
		this.makeBaggage = make;
	}
	public long getWeight() {
		return weight;
	}
	public void setWeight(long weight) {
		this.weight = weight;
	}
}

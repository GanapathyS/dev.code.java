package com.test.pojos;

import java.util.List;

public class Passengers {
	private String firstName;
	private String lastName;
	private List<Baggage> bags;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<Baggage> getBags() {
		return bags;
	}
	public void setBags(List<Baggage> bags) {
		this.bags = bags;
	} 
}

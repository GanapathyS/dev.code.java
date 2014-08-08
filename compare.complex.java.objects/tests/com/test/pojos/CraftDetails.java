package com.test.pojos;

public class CraftDetails {
	private String craftType = null;
	private String craftManufactureNumber = null;
	private Make make = null;
	
	public String getCraftType() {
		return craftType;
	}
	public void setCraftType(String craftType) {
		this.craftType = craftType;
	}
	public String getCraftManufactureNumber() {
		return craftManufactureNumber;
	}
	public void setCraftManufactureNumber(String craftManufactureNumber) {
		this.craftManufactureNumber = craftManufactureNumber;
	}
	public Make getMake() {
		return make;
	}
	public void setMake(Make make) {
		this.make = make;
	} 
}

package com.test.pojos;

import java.util.List;

import com.google.gson.Gson;

public class Flight {
	
	private int flightNumber = 0;
	private String flightName = "";
	private Integer numberOfPassengers = null;
	private Float costPerSeat = null;
	private Double mealCost = null;
	private Long flightDuration = null;
	private Destination destination = null;
	private CraftDetails craftDetails = null;
	private List<Passengers> passengers = null;
	
	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	} 
	
	public CraftDetails getCraftDetails() {
		return craftDetails;
	}

	public void setCraftDetails(CraftDetails craftDetails) {
		this.craftDetails = craftDetails;
	}

	public Integer getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(Integer numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}

	public Float getCostPerSeat() {
		return costPerSeat;
	}

	public void setCostPerSeat(Float costPerSeat) {
		this.costPerSeat = costPerSeat;
	}

	public Double getMealCost() {
		return mealCost;
	}

	public void setMealCost(Double mealCost) {
		this.mealCost = mealCost;
	}

	public Long getFlightDuration() {
		return flightDuration;
	}

	public void setFlightDuration(Long flightDuration) {
		this.flightDuration = flightDuration;
	} 
	
	public List<Passengers> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passengers> passengers) {
		this.passengers = passengers;
	}

	@Override
	public String toString() {
		Gson objGson = new Gson();
		return objGson.toJson(this); 
	}	  
}

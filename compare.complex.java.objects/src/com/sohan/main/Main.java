package com.sohan.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sohan.compare.util.CompareUtil;
import com.test.pojos.Baggage;
import com.test.pojos.CraftDetails;
import com.test.pojos.Destination;
import com.test.pojos.Flight;
import com.test.pojos.Make;
import com.test.pojos.Passengers;

/**
 * Test class to run the comparison of objects
 * 
 * @author Sohan
 */
public class Main { 
	
	public static void main(String[] args) throws Exception {
		
		Flight objFlightLHS = createLHSObject("12"); // Create LHS Object with 3 levels of complexity 
		Flight objFlightRHS = createRHSObject("12"); // Create RHS Object with 3 levels of complexity
		
		System.out.println(objFlightLHS.toString());
		System.out.println(objFlightRHS.toString());
		
		System.out.println("Are the objects equal: "+new CompareUtil().compareComplexObject(objFlightLHS, objFlightRHS)); 
	}
	
	/**
	 * Create a dummy LHS object.
	 * 
	 * @param num - to define the variance of an object
	 * @return
	 */
	private static Flight createLHSObject(String num)
	{
		Flight objFlight = new Flight();
		
		//Set flight details.
		objFlight.setFlightNumber(Integer.parseInt(num));
		objFlight.setFlightName("Flight-"+num);
		objFlight.setNumberOfPassengers(new Integer(num));
		objFlight.setCostPerSeat(new Float(num));
		objFlight.setMealCost(new Double(num));
		objFlight.setFlightDuration(new Long(num));
		
		//Set destination details.
		Destination objDestination = new Destination();
		objDestination.setFromCity("FROM"+num);
		objDestination.setToCity("TO"+num); 
		objFlight.setDestination(objDestination); 
		
		//Set Craft details.
		CraftDetails objCraftDetails = new CraftDetails();
		objCraftDetails.setCraftType("TYPE"+num);
		objCraftDetails.setCraftManufactureNumber("Manu. No."+num); 
		
		//Set Make details
		Make objMake = new Make();
		objMake.setNumberEmployees(Integer.parseInt(num));
		objMake.setLocation(num+"");
		objMake.setCompanyName(num+"");
		objMake.setCostOfCompany(Float.parseFloat(num)); 
		objMake.setTradingName('a');
		objMake.setGood(true);
		objMake.setAverageSalary(Long.parseLong(num));
		objMake.setDaysSinceOpened(Double.parseDouble(num)); 
		objMake.setOpened(new Date(1071980));
		objCraftDetails.setMake(objMake);
		objFlight.setCraftDetails(objCraftDetails);    
		
		//Set Passanger details
		List<Passengers> passangers = new ArrayList<Passengers>();
		Passengers obj1 = createTestPassengerObject(num);
		Passengers obj2 = createTestPassengerObject(num);
		Passengers obj3 = createTestPassengerObject(num);
		passangers.add(obj1);passangers.add(obj2);passangers.add(obj3);
		objFlight.setPassengers(passangers);
				
		return objFlight;
	}
	
	/**
	 * Create a dummy RHS object.
	 * 
	 * @param num - to define the variance of an object
	 * @return
	 */
	private static Flight createRHSObject(String num)
	{
		Flight objFlight = new Flight();
		
		//Set flight details.
		objFlight.setFlightNumber(Integer.parseInt(num));
		objFlight.setFlightName("Flight-"+num);
		objFlight.setNumberOfPassengers(new Integer(num));
		objFlight.setCostPerSeat(new Float(num));
		objFlight.setMealCost(new Double(num));
		objFlight.setFlightDuration(new Long(num));
		
		//Set destination details.
		Destination objDestination = new Destination();
		objDestination.setFromCity("FROM"+num);
		objDestination.setToCity("TO"+num); 
		objFlight.setDestination(objDestination); 
		
		//Set Craft details.
		CraftDetails objCraftDetails = new CraftDetails();
		objCraftDetails.setCraftType("TYPE"+num);
		objCraftDetails.setCraftManufactureNumber("Manu. No."+num); 
		
		//Set Make details
		Make objMake = new Make(); 
		objMake.setNumberEmployees(Integer.parseInt(num));
		objMake.setLocation(num+"");
		objMake.setCompanyName(num+"");
		objMake.setCostOfCompany(Float.parseFloat(num)); 
		objMake.setTradingName('a');
		objMake.setGood(true);
		objMake.setAverageSalary(Long.parseLong(num));
		objMake.setDaysSinceOpened(Double.parseDouble(num)); 
		objMake.setOpened(new Date(1071980));
		objCraftDetails.setMake(objMake);
		objFlight.setCraftDetails(objCraftDetails);  
		
		//Set Passanger details
		List<Passengers> passangers = new ArrayList<Passengers>();
		Passengers obj1 = createTestPassengerObject(num);
		Passengers obj2 = createTestPassengerObject(num);
		Passengers obj3 = createTestPassengerObject(num);
		passangers.add(obj1);passangers.add(obj2);passangers.add(obj3);
		objFlight.setPassengers(passangers);
				
		return objFlight;
	}
	
	/**
	 * Create a dummy object.
	 * 
	 * @param num - to define the variance of an object
	 * @return
	 */
	private static Passengers createTestPassengerObject(String num)
	{
		Passengers objPassengers = new Passengers(); 
		objPassengers.setFirstName("FN "+num);
		objPassengers.setLastName("LN "+num);
		
		List<Baggage> bags = new ArrayList<Baggage>();
		Baggage objBaggage = null;
		
		for(int i=0; i<2; i++)
		{
			objBaggage = new Baggage();
			objBaggage.setCheckIn(true);
			objBaggage.setMakeBaggage("American "+num+i);
			objBaggage.setWeight(new Long(num+i)*10);
			bags.add(objBaggage);
		}
		
		objPassengers.setBags(bags);
		return objPassengers;
	}
}

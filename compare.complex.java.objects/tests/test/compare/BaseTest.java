package test.compare;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.test.pojos.Baggage;
import com.test.pojos.CraftDetails;
import com.test.pojos.Destination;
import com.test.pojos.Flight;
import com.test.pojos.Make;
import com.test.pojos.Passengers;

public class BaseTest {
	/**
	 * Prints a provided object in JSON format.
	 * Usage: {@code printObject(obj1);}
	 * @param o
	 */
	public void printObject(Object o) {
		Gson objGson = new Gson();
		System.out.println(objGson.toJson(o)); 
	}	 
	
	/**
	 * Create a dummy object.
	 * 
	 * @param num - to define the variance of an object
	 * @return
	 */
	public Passengers createTestPassengerObject(String num)
	{
		Passengers objPassengers = new Passengers(); 
		objPassengers.setFirstName("FN "+num);
		objPassengers.setLastName("LN "+num);
		
		List<Baggage> bags = getBaggages(num);
		
		objPassengers.setBags(bags);
		return objPassengers;
	}

	/**
	 * Create a dummy object.
	 * 
	 * @param num - to define the variance of an object
	 * @return
	 */
	private List<Baggage> getBaggages(String num) {
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
		return bags;
	}
	
	/**
	 * Create a dummy object.
	 * 
	 * @param num - to define the variance of an object
	 * @return
	 */
	public Flight createTestObject(String num)
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
		Destination objDestination = createDestinationObject(num); 
		objFlight.setDestination(objDestination); 
		
		//Set Craft details.
		CraftDetails objCraftDetails = createCraftDetailsObject(num); 
		
		//Set Make details
		Make objMake = createMakeObject(num);
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
	public Destination createDestinationObject(String num) {
		Destination objDestination = new Destination();
		objDestination.setFromCity("FROM"+num);
		objDestination.setToCity("TO"+num);
		return objDestination;
	}

	/**
	 * Create a dummy object.
	 * 
	 * @param num - to define the variance of an object
	 * @return
	 */
	public CraftDetails createCraftDetailsObject(String num) {
		CraftDetails objCraftDetails = new CraftDetails();
		objCraftDetails.setCraftType("TYPE"+num);
		objCraftDetails.setCraftManufactureNumber("Manu. No."+num);
		return objCraftDetails;
	} 
	
	/**
	 * Create a dummy object.
	 * 
	 * @param num - to define the variance of an object
	 * @return
	 */
	public Make createMakeObject(String num) {
		Make objMake = new Make();
		objMake.setNumberEmployees(Integer.parseInt(num));
		objMake.setLocation(num+"");
		objMake.setCompanyName(num+"");
		objMake.setCostOfCompany(Float.parseFloat(num)); 
		objMake.setTradingName('a');
		objMake.setGood(true);
		objMake.setAverageSalary(Long.parseLong(num));
		objMake.setDaysSinceOpened(Double.parseDouble(num)); 
		objMake.setOpened(new Date(98364872));
		return objMake;
	}
}

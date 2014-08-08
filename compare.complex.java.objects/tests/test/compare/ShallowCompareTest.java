package test.compare;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.sohan.compare.util.CompareUtil;
import com.test.pojos.CraftDetails;
import com.test.pojos.Flight;
import com.test.pojos.Make;


/**
 * Test for a Shallow equals validation for Bunch of test Pojo's.
 * This class also tests for primitive, Wrapper and java.util.Date fields.
 * @author Sohan
 */
public class ShallowCompareTest {

	@Test
	public void testSameClassType() throws Exception { 
		Make obj1 = new Make(); 
		Make obj2 = new Make(); 
		Assert.assertEquals(true, new CompareUtil().compareSimpleObject(obj1, obj2)); 
	}
	
	@Test
	public void testDiffClassType() throws Exception { 
		Make obj1 = new Make(); 
		CraftDetails obj2 = new CraftDetails();  //Change
		Assert.assertEquals(false, new CompareUtil().compareSimpleObject(obj1, obj2)); 
	}
	
	@Test
	public void testNullsRHSSameType() throws Exception { 
		Make obj1 = new Make(); 
		Make obj2 = null; //Change
		Assert.assertEquals(false, new CompareUtil().compareSimpleObject(obj1, obj2)); 
	}
	
	@Test
	public void testNullsLHSSameType() throws Exception { 
		Make obj1 = null;  //Change 
		Make obj2 = new Make();
		Assert.assertEquals(false, new CompareUtil().compareSimpleObject(obj1, obj2)); 
	} 
	
	@Test
	public void testBothNullsSameType() throws Exception { 
		Make obj1 = null; 
		Make obj2 = null; 
		Assert.assertEquals(false, new CompareUtil().compareSimpleObject(obj1, obj2)); 
	}
	
	@Test
	public void testNullsDIfferentType() throws Exception { 
		Make obj1 = new Make(); 
		CraftDetails obj2 = null;  //Change
		Assert.assertEquals(false, new CompareUtil().compareSimpleObject(obj1, obj2)); 
	}
	
	@Test
	public void testFilledSimpleEqualPojo() throws Exception {
		int testNum = 123;
		Make obj1 = new Make(); 
		obj1.setNumberEmployees(testNum);
		obj1.setLocation(testNum+"");
		obj1.setCompanyName(testNum+"");
		
		Make obj2 = new Make();  
		obj2.setNumberEmployees(testNum);
		obj2.setLocation(testNum+"");
		obj2.setCompanyName(testNum+"");
		
		Assert.assertEquals(true, new CompareUtil().compareSimpleObject(obj1, obj2)); 
	}
	
	@Test
	public void testFilledComplexEqualPrimitivePojo() throws Exception {
		int testNum = 123;
		Make obj1 = new Make(); 
		obj1.setNumberEmployees(testNum);
		obj1.setLocation(testNum+"");
		obj1.setCompanyName(testNum+"");
		obj1.setCostOfCompany(testNum); 
		obj1.setTradingName('a');
		obj1.setGood(true);
		obj1.setAverageSalary(testNum);
		obj1.setDaysSinceOpened(testNum); 
		
		Make obj2 = new Make();  
		obj2.setNumberEmployees(testNum);
		obj2.setLocation(testNum+"");
		obj2.setCompanyName(testNum+"");
		obj2.setCostOfCompany(testNum); 
		obj2.setTradingName('a');
		obj2.setGood(true);
		obj2.setAverageSalary(testNum);
		obj2.setDaysSinceOpened(testNum); 
		
		Assert.assertEquals(true, new CompareUtil().compareSimpleObject(obj1, obj2)); 
	}
	
	@Test
	public void testEqualDatesInPojo() throws Exception {
		Make obj1 = new Make(); 
		obj1.setOpened(new Date());
		
		Make obj2 = new Make();  
		obj2.setOpened(new Date());
		
		Assert.assertEquals(true, new CompareUtil().compareSimpleObject(obj1, obj2)); 
	}
	
	@Test
	public void testUnEqualDatesInPojo() throws Exception {
		Make obj1 = new Make(); 
		obj1.setOpened(new Date());
		
		Make obj2 = new Make();  
		obj2.setOpened(new Date(999999999)); //Change
		
		Assert.assertEquals(false, new CompareUtil().compareSimpleObject(obj1, obj2)); 
	}
	
	@Test
	public void testFilledSimpleUnEqualPojo() throws Exception {
		int testNum = 123;
		Make obj1 = new Make(); 
		obj1.setNumberEmployees(testNum);
		obj1.setLocation(testNum+"");
		obj1.setCompanyName(testNum+"");
		obj1.setCostOfCompany(testNum); 
		obj1.setTradingName('a');
		obj1.setGood(true);
		obj1.setAverageSalary(testNum);
		obj1.setDaysSinceOpened(testNum); 
		
		Make obj2 = new Make();  
		obj2.setNumberEmployees(testNum);
		obj2.setLocation(testNum+"");
		obj2.setCompanyName(testNum+"");
		obj2.setCostOfCompany(testNum); 
		obj2.setTradingName('b'); //Change
		obj2.setGood(false);
		obj2.setAverageSalary(testNum);
		obj2.setDaysSinceOpened(testNum); 
		
		Assert.assertEquals(false, new CompareUtil().compareSimpleObject(obj1, obj2)); 
	}
	
	@Test
	public void testFilledComplexEqualWrapperPojo() throws Exception {
		int testNum = 123;
		
		Flight obj1 = new Flight(); 
		obj1.setFlightNumber(testNum);
		obj1.setFlightName("Flight-"+testNum);
		obj1.setNumberOfPassengers(new Integer(testNum));
		obj1.setCostPerSeat(new Float(testNum));
		obj1.setMealCost(new Double(testNum));
		obj1.setFlightDuration(new Long(testNum));
		
		
		Flight obj2 = new Flight(); 
		obj2.setFlightNumber(testNum);
		obj2.setFlightName("Flight-"+testNum);
		obj2.setNumberOfPassengers(new Integer(testNum));
		obj2.setCostPerSeat(new Float(testNum));
		obj2.setMealCost(new Double(testNum));
		obj2.setFlightDuration(new Long(testNum));
		
		Assert.assertEquals(true, new CompareUtil().compareSimpleObject(obj1, obj2)); 
	}
	
	@Test
	public void testFilledComplexUnEqualWrapperPojo() throws Exception {
		int testNum = 123;
		
		Flight obj1 = new Flight(); 
		obj1.setFlightNumber(testNum);
		obj1.setFlightName("Flight-"+testNum);
		obj1.setNumberOfPassengers(new Integer(testNum));
		obj1.setCostPerSeat(new Float(testNum));
		obj1.setMealCost(new Double(testNum));
		obj1.setFlightDuration(new Long(testNum));
		
		
		Flight obj2 = new Flight(); 
		obj2.setFlightNumber(testNum);
		obj2.setFlightName("Flight-"+testNum);
		obj2.setNumberOfPassengers(new Integer(testNum));
		obj2.setCostPerSeat(new Float(testNum));
		obj2.setMealCost(new Double(testNum+1)); //Change
		obj2.setFlightDuration(new Long(testNum));
		
		Assert.assertEquals(false, new CompareUtil().compareSimpleObject(obj1, obj2)); 
	}
	
	@Test
	public void testFilledComplexUnEqualPojo() throws Exception {
		int testNum = 123;
		CraftDetails obj1 = new CraftDetails();
		obj1.setCraftManufactureNumber(testNum+"");
		obj1.setCraftType(testNum+"");
		obj1.setMake(new Make());
		
		CraftDetails obj2 = new CraftDetails();
		obj2.setCraftManufactureNumber(testNum+"");
		obj2.setCraftType(testNum+"1"); //Change
		obj2.setMake(new Make());
		
		Assert.assertEquals(false, new CompareUtil().compareSimpleObject(obj1, obj2)); 
	}
}

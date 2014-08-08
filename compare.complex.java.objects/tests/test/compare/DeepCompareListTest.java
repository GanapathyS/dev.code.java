package test.compare;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.sohan.compare.util.CompareUtil;
import com.test.pojos.Baggage;
import com.test.pojos.Flight;
import com.test.pojos.Passengers;

/**
 * Test for a Deep multi layer, equals validation for Bunch of test Pojo's.
 * This class doesn't test for Collection / Map / Set.
 * 
 * @author Sohan
 */
public class DeepCompareListTest extends BaseTest {
	
	@Test
	public void testListForEqualObjects() throws Exception { 
		Passengers obj1 = createTestPassengerObject("123");
		Passengers obj2 = createTestPassengerObject("123");
		
		Assert.assertEquals(true, new CompareUtil().compareComplexObject(obj1, obj2)); 
	} 

	@Test
	public void testListForUnEqualObjects() throws Exception { 
		Passengers obj1 = createTestPassengerObject("123");
		Passengers obj2 = createTestPassengerObject("1234");  //Change
		
		Assert.assertEquals(false, new CompareUtil().compareComplexObject(obj1, obj2)); 
	} 
	
	@Test
	public void testListForUnEqualLeve1Object() throws Exception { 
		Passengers obj1 = createTestPassengerObject("123");
		Passengers obj2 = createTestPassengerObject("123"); 
		
		List<Baggage> bags = obj2.getBags();
		Baggage objBaggage = bags.get(1);
		bags.remove(1);
		objBaggage.setCheckIn(false); //Change
		bags.add(objBaggage);
		obj2.setBags(bags);
		
		Assert.assertEquals(false, new CompareUtil().compareComplexObject(obj1, obj2)); 
	} 
	
	@Test
	public void testListForEqualObjectsDifferentSizes() throws Exception { 
		Passengers obj1 = createTestPassengerObject("123");
		Passengers obj2 = createTestPassengerObject("123");
		
		obj2.getBags().remove(1);  //Change
		Assert.assertEquals(false, new CompareUtil().compareComplexObject(obj1, obj2)); 
	} 

	@Test
	public void testListForEqualObjectsWIthLHSNull() throws Exception { 
		Passengers obj1 = createTestPassengerObject("123");
		Passengers obj2 = createTestPassengerObject("123");
		
		obj1.setBags(null);  //Change
		Assert.assertEquals(false, new CompareUtil().compareComplexObject(obj1, obj2)); 
	} 
	
	@Test
	public void testListForEqualObjectsWIthRHSNull() throws Exception { 
		Passengers obj1 = createTestPassengerObject("123");
		Passengers obj2 = createTestPassengerObject("123");
		
		obj2.setBags(null);  //Change
		Assert.assertEquals(false, new CompareUtil().compareComplexObject(obj1, obj2)); 
	}  
	
	@Test
	public void testMultiLevelListForEqualObjects() throws Exception { 
		Flight obj1 = createTestObject("123");
		Flight obj2 = createTestObject("123");
		Assert.assertEquals(true, new CompareUtil().compareComplexObject(obj1, obj2)); 
	}
	
	@Test
	public void testMultiLevelListForUnEqualObjects() throws Exception { 
		Flight obj1 = createTestObject("123");
		Flight obj2 = createTestObject("123");
		
		Passengers objP1 = createTestPassengerObject("123");
		obj2.getPassengers().add(objP1); //Change
		
		Assert.assertEquals(false, new CompareUtil().compareComplexObject(obj1, obj2)); 
	} 
	
	@Test
	public void testMultiLevel2ListForUnEqualObjects() throws Exception { 
		Flight obj1 = createTestObject("123");
		Flight obj2 = createTestObject("123");
		
		Passengers objP1 = createTestPassengerObject("123");
		obj2.getPassengers().add(objP1); //Change
		
		Assert.assertEquals(false, new CompareUtil().compareComplexObject(obj1, obj2)); 
	} 
}

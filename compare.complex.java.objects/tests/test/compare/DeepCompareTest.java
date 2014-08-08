package test.compare;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.sohan.compare.util.CompareUtil;
import com.test.pojos.CraftDetails;
import com.test.pojos.Flight;
import com.test.pojos.Make;

/**
 * Test for a Deep multi layer, equals validation for Bunch of test Pojo's.
 * This class doesn't test for Collection / Map / Set.
 * 
 * @author Sohan
 */
public class DeepCompareTest  extends BaseTest {
	@Test
	public void testDeepForEqualObjects() throws Exception { 
		Flight obj1 = createTestObject("123");
		Flight obj2 = createTestObject("123");
		
		Assert.assertEquals(true, new CompareUtil().compareComplexObject(obj1, obj2)); 
	}
	
	@Test
	public void testDeepForUnEqualObjects() throws Exception { 
		Flight obj1 = createTestObject("123");
		Flight obj2 = createTestObject("1234"); //Change
		
		Assert.assertEquals(false, new CompareUtil().compareComplexObject(obj1, obj2)); 
	}
	
	@Test
	public void testDeep2LevelForEqualObjects() throws Exception { 
		CraftDetails obj1 = createCraftDetailsObject("123");
		Make objM1 = createMakeObject("123");
		obj1.setMake(objM1);
		
		CraftDetails obj2 = createCraftDetailsObject("123");  
		Make objM2 = createMakeObject("123");
		obj2.setMake(objM2);

		Assert.assertEquals(true, new CompareUtil().compareComplexObject(obj1, obj2)); 
	}
	
	@Test
	public void testDeep1LevelForUnEqualObjects() throws Exception { 
		Flight obj1 = createTestObject("123");
		Flight obj2 = createTestObject("123");
		
		obj2.getCraftDetails().setCraftType("Boeing-747");  //Change
		
		Assert.assertEquals(false, new CompareUtil().compareComplexObject(obj1, obj2)); 
	}
	
	@Test
	public void testDeep2LevelForUnEqualObjects() throws Exception { 
		Flight obj1 = createTestObject("123");
		Flight obj2 = createTestObject("123");
		
		obj2.getCraftDetails().getMake().setCompanyName("TESTING123"); //Change
		
		Assert.assertEquals(false, new CompareUtil().compareComplexObject(obj1, obj2)); 
	}
	
	@Test
	public void testDeep2LevelForUnEqualComplexObject() throws Exception { 
		Flight obj1 = createTestObject("123");
		Flight obj2 = createTestObject("123");
		
		obj2.getCraftDetails().getMake().setOpened(new Date(2173213)); //Change
		
		Assert.assertEquals(false, new CompareUtil().compareComplexObject(obj1, obj2)); 
	}
	
	@Test
	public void testDeepForEqualObjectsWithNull() throws Exception { 
		Flight obj1 = createTestObject("123");
		Flight obj2 = createTestObject("123");
		
		obj1.setDestination(null); // Make object Null for Testing
		obj2.setDestination(null); // Make object Null for Testing
		
		Assert.assertEquals(true, new CompareUtil().compareComplexObject(obj1, obj2)); 
	}
	
	@Test
	public void testDeepForUnEqualObjectsWithNullRHS() throws Exception { 
		Flight obj1 = createTestObject("123");
		Flight obj2 = createTestObject("123");
		 
		obj2.setDestination(null); // Make object Null for Testing
		
		Assert.assertEquals(false, new CompareUtil().compareComplexObject(obj1, obj2)); 
	}
	
	@Test
	public void testDeepForUnEqualObjectsWithNullRLHS() throws Exception { 
		Flight obj1 = createTestObject("123");
		Flight obj2 = createTestObject("123");
		 
		obj1.setDestination(null); // Make object Null for Testing
		
		Assert.assertEquals(false, new CompareUtil().compareComplexObject(obj1, obj2)); 
	} 
}

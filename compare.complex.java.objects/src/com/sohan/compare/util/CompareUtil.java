package com.sohan.compare.util;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This utility class provides the call with methods to validate if two similar instance Objects are equal.
 * The {@link #compareSimpleObject(Object, Object) compareSimpleObject} method, performs a shallow comparison validating 
 * only the java variable  types of the given objects, whereas the {@link #compareComplexObject(Object, Object) compareComplexObject} 
 * method, performs a deep equals validating all complex type in the given class, traverse's through the various complex types 
 * and other complex types within to validate the equals. 
 * <p>
 * Usage: {@code new CompareUtil().compareSimpleObject(instanceA, instanceB);}
 * <p>
 * @author Sohan
 */
public class CompareUtil {
	
	private final String JAVA_LANG="java.lang";
	private final String JAVA_UTIL="java.util"; 
	
	/**
	 * This method does basic / shallow  level of comparison, i.e, compare only the simple type fields of that 
	 * class.<br>
	 * The method returns a 'false' if not equal & 'true' if equal.
	 * <p> 
	 * Example:-
	 * {@code
	 * public class TestA {
	 *    String name;
	 *    int age;
	 *    TestB objB;
	 * }
	 * }
	 * The method would check for equals in String and primitive type only.
	 * <p>
	 * @param objLHS - Object to compare.
	 * @param objRHS - Object to compare.
	 * @return Boolean
	 * @throws Exception
	 */
	public <T> boolean compareSimpleObject(T objLHS, T objRHS) throws Exception
	{
		return compare(objLHS, objRHS, false);
	}

	/**
	 * This method does basic level of comparison, i.e, compare the complex types in the current class and the 
	 * complex class within.<br>
	 * The method returns a 'false' if not equal & 'true' if equal.
	 * <p> 
	 * Example:-
	 * {@code
	 * public class TestA {
	 *    String name;
	 *    int age;
	 *    TestB objB;
	 * }
	 * }
	 * The method would check for all types and all types within class TestB.
	 * <p>
	 * @param objLHS - Object to compare.
	 * @param objRHS - Object to compare.
	 * @return Boolean
	 * @throws Exception
	 */
	public <T> boolean compareComplexObject(T objLHS, T objRHS) throws Exception
	{
		return compare(objLHS, objRHS, true);
	}
	
	/**
	 * Method which does the comparison, of two objects. Depending on the boolean sent it either performs a single basic 
	 * object equals or performs a deep equals.
	 * <p>
	 * - Check for null & checks for same instance of variable being compared.<br>
	 * - Fetch all the fields which needs to be compared. (Can become annotated to leave out fields.)<br>
	 * - Invoke separate methods based on ComplexCompare.<br>
	 * <p>
	 * @param objLHS - Object to compare.
	 * @param objRHS - Object to compare.
	 * @param isComplexCompare - Boolean to perform a deep equals. ('true' for deep equals.)
	 * @return Boolean
	 * @throws Exception
	 */
	private <T> boolean compare(T objLHS, T objRHS, boolean isComplexCompare) throws Exception
	{ 
		boolean isEqual = false;
		
		if(objLHS == null || objRHS == null) //Basic null check
			return false; 
		
		if(!(objLHS.getClass().equals(objRHS.getClass()))) //Check for same instance
			return false;  
		
		//Get all the fields, types and values for both the objects.
		Map<String, FieldNode> mapLHSFields = constructFieldsToCompare(objLHS);
		Map<String, FieldNode> mapRHSFields = constructFieldsToCompare(objRHS);
		 
		if(!isComplexCompare) { // if the caller performed a Simple compare.
			isEqual = equalsSimple(mapLHSFields,mapRHSFields, objLHS, objRHS);
		}
		else { // if the caller performed a Complex deep compare.
			isEqual = equalsComplex(mapLHSFields,mapRHSFields, objLHS, objRHS);
		}
		
		return isEqual;
	}
	
	/**
	 *  This method constructs the {@link FieldNode} object, which holds the various fields details of the object 
	 *  being compared, but also checks if the type of the variable being compared.
	 *   
	 * @param obj - Object being processed for field information.
	 * @return Map<String, FieldNode>
	 */
	private <T> Map<String, FieldNode> constructFieldsToCompare(T obj) {
		Map<String, FieldNode> mapFieldNode = new HashMap<String, FieldNode>();
		FieldNode objFieldNode = null;
		for (Field variable : (obj.getClass()).getDeclaredFields()) { 
			variable.setAccessible(true);
			
			objFieldNode = new FieldNode();
			objFieldNode.setVariable(variable);
			objFieldNode.setCustomType(isCustomType(variable));
			objFieldNode.setCollection(isCollection(variable));
			mapFieldNode.put(variable.getName(), objFieldNode);
		}
		return mapFieldNode;
	}
	
	/**
	 * Check if the field is a custom type / java type (Primitive, Wrappers & Date(Util Package)).
	 * 
	 * @param variable - variable being verified.
	 * @return boolean
	 */
	private boolean isCustomType(Field variable) { 
		return !(variable.getType().isPrimitive() || variable.getType().getName().startsWith(JAVA_LANG) 
				|| variable.getType().getName().startsWith(JAVA_UTIL));
	} 
	
	/**
	 * Check if the field is collection.
	 * 
	 * @param variable - variable being verified.
	 * @return boolean
	 */
	private boolean isCollection(Field variable) { 
		return Collection.class.isAssignableFrom(variable.getType()) || Map.class.isAssignableFrom(variable.getType());
	}
	
	/**
	 * This method does a shallow comparison of the objects validated for equals. The method basically checks if the
	 * type is a Java type. The equals check is performed for fields which are Java type only. The method returns a 
	 * 'false' if not equal & 'true' if equal.
	 * 
	 * @param mapLHSFields - Map of all the fields being compared on the LHS.
	 * @param mapRHSFields - Map of all the fields being compared on the RHS.
	 * @param objLHS - LHS Object holding the value which needs to be compared.
	 * @param objRHS - RHS Object holding the value which needs to be compared.
	 * @return Boolean.
	 * @throws Exception
	 */
	private boolean equalsSimple(Map<String, FieldNode> mapLHSFields, Map<String, FieldNode> mapRHSFields, 
			Object objLHS, Object objRHS) throws Exception { 
		
		for ( String key : mapLHSFields.keySet() ) { // For all the keys in the FieldNode map. 
			//Get the FieldNode for the LHS and RHS objects.
		    FieldNode fieldNodeLHS = mapLHSFields.get(key);
		    FieldNode fieldNodeRHS = mapRHSFields.get(key);
		    
		    Field lhsVariable = fieldNodeLHS.getVariable();
		    Field rhsVariable = fieldNodeRHS.getVariable();
		    
		    if(!fieldNodeLHS.isCustomType() && !fieldNodeLHS.isCollection()) { //If the field is a Java type
		    	if(lhsVariable.get(objLHS) != null || rhsVariable.get(objRHS) != null) { // If either of the objects are not null
		    		if(!(lhsVariable.get(objLHS).equals(rhsVariable.get(objRHS)))) // perform the equals check.
		    			return false;
		    	}
		    }
		}
		return true;
	}
	
	/**
	 * This method does a deep comparison of the objects validated for equals. The method performs a deep equals 
	 * validating all complex type in the given class, traverse's through the various complex types and other complex 
	 * types within to validate the equals. The method returns a 'false' if not equal & 'true' if equal. 
	 * 
	 * @param mapLHSFields - Map of all the fields being compared on the LHS.
	 * @param mapRHSFields - Map of all the fields being compared on the RHS.
	 * @param objLHS - LHS Object holding the value which needs to be compared.
	 * @param objRHS - RHS Object holding the value which needs to be compared.
	 * @return Boolean.
	 * @throws Exception
	 */
	private boolean equalsComplex(Map<String, FieldNode> mapLHSFields, Map<String, FieldNode> mapRHSFields, 
			Object objLHS, Object objRHS) throws Exception { 
		
		for ( String key : mapLHSFields.keySet() ) { // For all the keys in the FieldNode map.
			
			//Get the FieldNode for the LHS and RHS objects.
		    FieldNode fieldNodeLHS = mapLHSFields.get(key);
		    FieldNode fieldNodeRHS = mapRHSFields.get(key);   
		    
		    Field lhsVariable = fieldNodeLHS.getVariable();
		    Field rhsVariable = fieldNodeRHS.getVariable();
		    
		    if(!fieldNodeLHS.isCustomType() && !fieldNodeLHS.isCollection()) { //If the field is a Java type
		    	if(lhsVariable.get(objLHS) != null) { // If either of the objects are not null 
			    	if(!(lhsVariable.get(objLHS).equals(rhsVariable.get(objRHS)))) // perform the equals check.
			    		return false; 
		    	}
		    	else if(rhsVariable.get(objRHS) != null)
		    	{
		    		if(!(rhsVariable.get(objRHS).equals(lhsVariable.get(objLHS)))) // perform the equals check.
			    		return false; 
		    	}
		    }
		    else if (fieldNodeLHS.isCustomType() && !fieldNodeLHS.isCollection()) { 
		    	//If the field is a Complex type, recursively call the 'compare()', for a deep validation.
		    	if(lhsVariable.get(objLHS) != null || rhsVariable.get(objRHS) != null) { // If either of the objects are not null 
			    	if(!compare(lhsVariable.get(objLHS), rhsVariable.get(objRHS), true))
			    		return false; 
		    	}
		    }
		    else {  // Handle Collections and Maps.
		    	if(!handleJavaColandMap(objLHS, objRHS, lhsVariable, rhsVariable)) 
		    		return false;
		    }
		}
		return true;
	}

	/**
	 * This method does a deep comparison of the Collection and Map objects. The method returns a 'false' if not
	 * equal & 'true' if equal. 
	 * 
	 * @param mapLHSFields - Map of all the fields being compared on the LHS.
	 * @param mapRHSFields - Map of all the fields being compared on the RHS.
	 * @param objLHS - LHS Object holding the value which needs to be compared.
	 * @param objRHS - RHS Object holding the value which needs to be compared.
	 * @return Boolean.
	 * @throws IllegalAccessException
	 */
	private <T> boolean handleJavaColandMap(T objLHS, T objRHS,
			Field lhsVariable, Field rhsVariable) throws Exception { 
		if(lhsVariable.get(objLHS) != null || rhsVariable.get(objRHS) != null) { // If either of the objects are not null 
			if(Collection.class.isAssignableFrom(lhsVariable.getType())) { //Check if its a collection 
				if(!handleCollectionEquals(lhsVariable, rhsVariable, objLHS, objRHS))
	    		{
	    			return false;
	    		}
			}
			else if (Map.class.isAssignableFrom(lhsVariable.getType())) {
				if(!handleMapEquals(lhsVariable, rhsVariable, objLHS, objRHS))
	    		{
	    			return false;
	    		}
			}
		}
		
		return true;
	}
	 
	/**
	 * This method does a deep comparison of the Map objects. The method returns a 'false' if not
	 * equal & 'true' if equal. 
	 * 
	 * @param lhsVariable - LHS Field variable.
	 * @param rhsVariable - RHS field variable.
	 * @param objLHS - LHS Object holding the value which needs to be compared.
	 * @param objRHS - RHS Object holding the value which needs to be compared.
	 * @return boolean
	 * @exception Exception
	 */
	private <T> boolean handleMapEquals(Field lhsVariable, Field rhsVariable, T objLHS, T objRHS) {
		// TODO handle java.util.Map
		return true;
	}

	/**
	 * This method does a deep comparison of the Collection objects. The method returns a 'false' if not
	 * equal & 'true' if equal. 
	 * 
	 * @param <T> - Generic Type of collection being verified.
	 * @param lhsVariable - LHS Field variable.
	 * @param rhsVariable - RHS field variable.
	 * @param objLHS - LHS Object holding the value which needs to be compared.
	 * @param objRHS - RHS Object holding the value which needs to be compared.
	 * @return boolean
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	private <T, L> boolean handleCollectionEquals(Field lhsVariable, Field rhsVariable, L objLHS, L objRHS) throws Exception { 
		// Get the two lists being compared. 
		Collection<T> objLhsLst = (Collection<T>) lhsVariable.get(objLHS);
		Collection<T> objRhsLst = (Collection<T>) lhsVariable.get(objRHS); 
		
		if (objLhsLst == objRhsLst) //Same list they match.
	        return true;
		
		// If either list is null, or the lengths are not equal, they can't match.
		if(objLhsLst != null && objRhsLst != null && objLhsLst.size() == objRhsLst.size())
		{ 
			int count = 0;
			 
			/*
			 * Brute force the compare, since the order of the Object in the List may have changed.
			 * 1) Iterate through all items in LHS. Recursively invoke 'compare()' to validate if they equal.
			 * 2) When an Equal object is found, increment the counter.
			 * 3) Check the counter size and LHS size. If the count is same, the lists are equal.
			 */
		    for (T lhsItem : objLhsLst) { 
		    	for (T rhsItem : objRhsLst) { 
		    		if(compare(lhsItem, rhsItem, true)) {// Recursively invoke 'compare()' to validate the items are equal.
		    			count++;
		    			break;
		    		}
		    	}
		    }
		    if(objLhsLst.size() != count) //If the size of the counter and LHS are not the same, they are not equal.
			    return false; 
		    
			return true; //Lists are equal, after doing a deep comparision.
		}
		else {
			return false; //Lists are not equal, their sizes differ or they are null.
		}
	} 
	
	/**
	 * Inner DataStructure to hold the fields that have been fetched from the object variables.
	 */
	private class FieldNode	{
		private Field variable; //Holds all field details.
		private boolean isCustomType; //Boolean to know if the fields is a custom field.
		private boolean isCollection; //Boolean to know if the fields is a Collection / Map.
		
		public Field getVariable() {
			return variable;
		}
		public void setVariable(Field variable) {
			this.variable = variable;
		}
		public boolean isCustomType() {
			return isCustomType;
		}
		public void setCustomType(boolean isCustomType) {
			this.isCustomType = isCustomType;
		}
		public boolean isCollection() {
			return isCollection;
		}
		public void setCollection(boolean isCollection) {
			this.isCollection = isCollection;
		} 
	}
}

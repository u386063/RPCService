package com.rpc.server.helper;

import java.util.HashSet;
import java.util.Set;

import com.rpc.server.constants.TreeOperations;

/*
 * 
 */
public class TreeObjects {

	private static Set<Object> objectset;

	private static TreeObjects instance = null;

	/*
	 * Initializes the tree objects object.
	 */
	private TreeObjects(){
		objectset = new HashSet<Object>();
	}

	/*
	 * Getter for TreeObjects instance.
	 */
	public static TreeObjects getInstance(){
		if(instance == null){
			instance = new TreeObjects();
		}
		return instance;
	}
	
	/*
	 * Adds new object to the TreeOperations.
	 */
	public void addObject(Object obj){
		objectset.add(obj);
	}
	
	/*
	 * Getter for object present in the TreeOperations based on method name.
	 */
	public Object getObject(String methodName){
		String className = TreeOperations.getById(methodName);
		try{
		for(Object obj : objectset){
			
			if(Class.forName(className).isInstance(obj)){
				return obj;
			}
		}
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		return null;
	}
 
}

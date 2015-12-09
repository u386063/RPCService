package com.rpc.server.constants;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/*
 * TreeOperations class describes the operations that can be performed on the tree.
 */
public enum TreeOperations {
	
	PRE_ORDER("preOrder","com.rpc.server.binarysearchtree.BinarySearchTree"),
	IN_ORDER("inOrder","com.rpc.server.binarysearchtree.BinarySearchTree"),
	POST_ORDER("postOrder","com.rpc.server.binarysearchtree.BinarySearchTree"),
	SIZE("size","com.rpc.server.binarysearchtree.BinarySearchTree"),
	ADD("add","com.rpc.server.binarysearchtree.BinarySearchTree"),
	SEARCH_NODE("searchNode","com.rpc.server.binarysearchtree.BinarySearchTree"),
	MAX_NODE("findMax","com.rpc.server.binarysearchtree.BinarySearchTree");
	
	private String methodName;
	private String className;
	private static Set<String> operationSet = new TreeSet<String>();
	
	/*
	 * Initializes the object with the method name and class name.
	 */
	private TreeOperations(String methodName, String className){
		
		this.methodName = methodName;
		this.className = className;
	}
	
	/*
	 * Getter for method name of the tree operation.
	 */
	public String getMethodName(){
		return methodName;
	}
	
	/*
	 * Getter for class name of the tree operation.
	 */
	public String getClassName(){
		
		return className;
	}

	/*
	 * Gets the class name based on the ID of the method name.
	 */
	public static String getById(String action) {
	    for(TreeOperations e : values()) {
	        if(e.methodName.equals(action)) {
	        	return e.className;
	        }
	    }
	    return null;
	 }
	
	/*
	 * Getter for operations that needs to be performed.
	 */
	public static Set<String> getOperations(){
		 for(TreeOperations e : values()) {
		        operationSet.add(e.methodName);
		    }
		 return operationSet;
	}

}

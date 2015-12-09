package com.rpc.data;

import java.util.List;

/*
 * RequestData extends the Data class. Deals with the data that is related
 * to the incoming request. Getters and setters for the parameter list and 
 * no. of parameters help in determining the request method and its behavior.
 */
public class RequestData extends Data{
	
	private static final long serialVersionUID = -5606406464729176758L;
	
	private Integer parameterCount;
	private List<Object> parameterList;

	/*
	 * Gets the parameter count from the request method.
	 */
	public Integer getParameterCount() {
		return parameterCount;
	}
	
	/*
	 * Sets the parameter count for the request method.
	 */
	public void setParameterCount(Integer parameterCount) {
		this.parameterCount = parameterCount;
	}
	
	/*
	 * Gets the list of parameters from the request method.
	 */
	public List<Object> getParameterList() {
		return parameterList;
	}

	/*
	 * 	Sets the parameter list for the request method.
	 */
	public void setParameterList(List<Object> parameterList) {
		this.parameterList = parameterList;
	}
	
	
	
}

package com.rpc.data;

import java.io.Serializable;

/*
 * Data class for tracking requests from clients and the system's response
 * to the incoming requests. Getters and setters help in determining the type of request
 * and associated response and the correlation between them.
 */
public class Data implements Serializable{
	
	private static final long serialVersionUID = 2259659060753221367L;
	protected String requestMethodName;
	protected String responseMethodName;
	protected String correlationId;
	
	/*
	 * Gets the method name for the request.
	 */
	public String getRequestMethodName() {
		return requestMethodName;
	}
	
	/*
	 * Sets the method name in request.
	 */
	public void setRequestMethodName(String requestMethodName) {
		this.requestMethodName = requestMethodName;
	}
	
	/*
	 * Gets the method name for the response.
	 */
	public String getResponseMethodName() {
		return responseMethodName;
	}
	
	/*
	 * Sets the method name in the response.
	 */
	public void setResponseMethodName(String responseMethodName) {
		this.responseMethodName = responseMethodName;
	}
	
	/*
	 * Gets the correlation ID of the request - response.
	 */
	public String getCorrelationId() {
		return correlationId;
	}
	
	/*
	 * Sets the correlation ID of the request-response.
	 */
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
}

package com.rpc.data;

import java.io.Serializable;

public class Data implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2259659060753221367L;
	protected String requestMethodName;
	protected String responseMethodName;
	protected String correlationId;
	
	
	public String getRequestMethodName() {
		return requestMethodName;
	}
	public void setRequestMethodName(String requestMethodName) {
		this.requestMethodName = requestMethodName;
	}
	public String getResponseMethodName() {
		return responseMethodName;
	}
	public void setResponseMethodName(String responseMethodName) {
		this.responseMethodName = responseMethodName;
	}
	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
}

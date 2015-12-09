package com.rpc.data;

import java.util.List;

public class RequestData extends Data {

	private static final long serialVersionUID = -5606406464729176758L;
	
	private Integer parameterCount;
	private List<Object> parameterList;
	
	
	public Integer getParameterCount() {
		return parameterCount;
	}

	public void setParameterCount(Integer parameterCount) {
		this.parameterCount = parameterCount;
	}

	public List<Object> getParameterList() {
		return parameterList;
	}

	public void setParameterList(List<Object> parameterList) {
		this.parameterList = parameterList;
	}
	
	
	

}

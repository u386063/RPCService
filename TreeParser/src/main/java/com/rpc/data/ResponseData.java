package com.rpc.data;

import java.util.List;

/*
 * ResponseData extends the Data class. Deals with the data that is related
 * to the outgoing response. Getters and setters for the parameter list and 
 * no. of parameters help in determining the response method and its behavior.
 */
public class ResponseData extends Data {

	private static final long serialVersionUID = -2925396225996033732L;
	private int errorCode;
	private List<String> methodReturnValue;

	public ResponseData() {

	}

	/*
	 * Initializes the object with incoming request data.
	 */
	public ResponseData(RequestData requestMsg) {
		correlationId = requestMsg.getCorrelationId();
		requestMethodName = requestMsg.getRequestMethodName();
		responseMethodName = requestMsg.getResponseMethodName();
	}

	/*
	 * Getter for error code.
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/*
	 * Setter for error code.
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/*
	 * Getter for response method's return value.
	 */
	public List<String> getMethodReturnValue() {
		return methodReturnValue;
	}

	/*
	 * Setter for response method's return value.
	 */
	public void setMethodReturnValue(List<String> responseMessage) {
		this.methodReturnValue = responseMessage;
	}

}

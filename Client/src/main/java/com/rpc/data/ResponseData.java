package com.rpc.data;

import java.util.List;

public class ResponseData extends Data {

	private static final long serialVersionUID = -2925396225996033732L;
	private int errorCode;
	private List<String> methodReturnValue;

	public ResponseData() {

	}

	public ResponseData(RequestData requestMsg) {
		correlationId = requestMsg.getCorrelationId();
		requestMethodName = requestMsg.getRequestMethodName();
		responseMethodName = requestMsg.getResponseMethodName();
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public List<String> getMethodReturnValue() {
		return methodReturnValue;
	}

	public void setMethodReturnValue(List<String> responseMessage) {
		this.methodReturnValue = responseMessage;
	}

}

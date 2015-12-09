package com.rpc.client.event;

import com.rpc.data.ResponseData;


public interface CallBack {

	public void callBackMethod(String correlationId, ResponseData response);


}

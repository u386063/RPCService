package com.rpc.client.util;

import com.rpc.data.ResponseData;

public class Utils {
	
	public static String showResponse(ResponseData response){
		
		int errorCode = response.getErrorCode();
		String result = null;
		System.out.println("-------------"+ response.getRequestMethodName() +" Response------------------");
		if(errorCode > 0){
			System.out.println("################## Error Returned ###################"+errorCode);
		}
		for(String list: response.getMethodReturnValue()){
			result= list;
			System.out.println(list);
		}
		System.out.println("------------- End "+ response.getRequestMethodName() +" Response-------------\n");
		return result;
	}

}


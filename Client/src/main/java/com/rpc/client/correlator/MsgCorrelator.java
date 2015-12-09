package com.rpc.client.correlator;

import java.util.Hashtable;


public class MsgCorrelator {
	

	public static Hashtable<String, Object> correlationMap = new Hashtable<String, Object>();

	public static void addObject(String correlationID,  Object caller){
		correlationMap.put(correlationID, caller);
	}
	
	public static Object removeObject(String correlationID){
		return correlationMap.remove(correlationID);
	}
	
	public static Object correlateObject(String correlationID){
		return correlationMap.get(correlationID);
	}

}

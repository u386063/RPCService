package com.rpc.client;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.rpc.client.constants.Constants;
import com.rpc.client.correlator.MsgCorrelator;
import com.rpc.client.event.CallBack;
import com.rpc.client.receiver.Receiver;
import com.rpc.client.sender.Sender;
import com.rpc.client.util.Utils;
import com.rpc.data.RequestData;
import com.rpc.data.ResponseData;

/**
 * The Client class creates the Receiver to listen on the queue and processes
 * the incoming messages and Sender sends the response to the queue
 *
 * @author RaviTeja
 * @version 1.0
 */
public class Client implements CallBack {
	private Sender sender;
	private Receiver receiver;
	private List<Object> parameterList = null;
	public String result;
	public static Client obj = null;

	// Creates the object - Sender & Receiver
	private Client() {
		sender = new Sender();
		receiver = new Receiver();
	}

	/**
	 * This method is used to print the response. This is a helper method, just
	 * to print the response.
	 * 
	 * @param correlationId
	 *            This is used to identify the request & response intended to
	 *            the same client
	 * @param response
	 *            This parameter contains the response from the server
	 */
	public void callBackMethod(String correlationId, ResponseData response) {

		String responseMethod = response.getResponseMethodName();
		System.out.println("Received callback for method : " + responseMethod);
		if (responseMethod.equals("showResponse")) {
			result = Utils.showResponse(response);
		}
	}

	// Sending requests to the queue
	public static void main(String[] args) {
		// Creating singleton instance
		Client obj = Client.getInstance();
		// Sending request to server to display list of tree operations
		obj.sendRequest(Constants.SHOW_TREE_OPERATIONS);
		// Sending request to server to parse the tree using In Order Traversal
		obj.sendRequest(Constants.IN_ORDER);
		// Sending request to server to parse the tree using Pre Order Traversal
		obj.sendRequest(Constants.PRE_ORDER);
		// Sending request to server to parse the tree using Post Order
		// Traversal
		obj.sendRequest(Constants.POST_ORDER);
		// Sending request to server to calculate the size of tree
		obj.sendRequest(Constants.SIZE);
		// Sending request to server to calculate the addition of all the nodes
		obj.sendRequest(Constants.ADD);
		// Sending request to server to search the node of binary search tree
		obj.sendRequest(Constants.SEARCH_NODE, "13");
		
		//Sending request to server to search the node with multiple parameters
		obj.sendRequest(Constants.SEARCH_NODE, "13","1");
		
		//Sending request to server to search the node with multiple parameters
		obj.sendRequest(Constants.SEARCH_NODE, "13","1","90","8");
		
		
		// Close the sender connection opened at the time of class
		// initialization
		obj.getSender().closeConnection();
		// Close the receiver connection opened at the time of class
		// initialization
		obj.getReceiver().closeConnection();
	}

	/**
	 * This method is a overloaded method to send the request.
	 * 
	 * @param requestMethodName
	 *            This parameter is holds the tree operation requested from the
	 *            client
	 */
	public void sendRequest(String requestMethodName) {
		parameterList = new ArrayList<Object>();
		parameterList.add(null);
		sendRequest(requestMethodName, parameterList, 0);

	}
	
	/**
	 * This method is a overloaded method to send the request.
	 * 
	 * @param requestMethodName
	 *            This parameter is holds the tree operation requested from the
	 *            client
	 * @param parameter
	 *            This parameter holds the search node
	 */
	public void sendRequest(String requestMethodName, String parameter) {
		parameterList = new ArrayList<Object>();
		parameterList.add(parameter);
		sendRequest(requestMethodName, parameterList, 1);
	}
	
	/**
	 * This method is a overloaded method to send the request.
	 * 
	 * @param requestMethodName
	 *            This parameter is holds the tree operation requested from the
	 *            client
	 * @param parameter1
	 *            This parameter1 holds the search node
	 * @param parameter2
	 *            This parameter1 holds the search node 
	 *            
	 */
	public void sendRequest(String requestMethodName, String parameter1,String parameter2) {
		parameterList = new ArrayList<Object>();
		parameterList.add(parameter1);
		parameterList.add(parameter2);
		sendRequest(requestMethodName, parameterList, 2);
	}
	
	/**
	 * This method is a overloaded method to send the request.
	 * 
	 * @param requestMethodName
	 *            This parameter is holds the tree operation requested from the
	 *            client
	 * @param parameter1
	 *            This parameter1 holds the search node
	 * @param parameter2
	 *            This parameter1 holds the search node 
	 * @param parameter3
	 *            This parameter1 holds the search node 
	 * @param parameter4
	 *            This parameter1 holds the search node 
	 *            
	 */
	public void sendRequest(String requestMethodName, String parameter1,String parameter2,String parameter3, String parameter4) {
		parameterList = new ArrayList<Object>();
		parameterList.add(parameter1);
		parameterList.add(parameter2);
		parameterList.add(parameter3);
		parameterList.add(parameter4);
		sendRequest(requestMethodName, parameterList, 4);
	}
	
	
	
	/**
	 * This method is a overloaded method to send the request.
	 * 
	 * @param requestMethodName
	 *            This parameter is holds the tree operation requested from the
	 *            client
	 * @param parameter
	 *            This parameter holds the search node
	 * @param parameterCount
	 *            This parameter holds the total parameters to be sent
	 */
	private void sendRequest(String requestMethodName, List<Object> parameterList, Integer parameterCount) {
		sendRequest(requestMethodName,Constants.SHOW_RESPONSE, parameterList, parameterCount);
	}

	/**
	 * This method is a overloaded method used by other public overloaded
	 * methods to send the final request to the server
	 * 
	 * @param requestMethodName
	 *            This parameter is holds the tree operation requested from the
	 *            client
	 * @responseMethodName
	 * 			  This parameter holds the response method name to be
	 *                     called for printing the response
	 * @param parameter
	 *            This parameter holds the search node
	 * @param parameterCount
	 *            This parameter holds the total parameters to be sent
	 */
	private void sendRequest(String requestMethodName, String responseMethodName, List<Object> parameterList, Integer parameterCount) {

		RequestData request = new RequestData();
		String correlationId = UUID.randomUUID().toString();
		request.setCorrelationId(correlationId);
		request.setRequestMethodName(requestMethodName);
		request.setResponseMethodName(responseMethodName);
		request.setParameterCount(parameterCount);
		request.setParameterList(parameterList);
		MsgCorrelator.addObject(correlationId, this);
		sender.sendObjectMessage(request);
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public Receiver getReceiver() {
		return receiver;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}

	/* Singleton object creation*/
	public static Client getInstance() {

		if (obj == null) {
			obj = new Client();
		}
		return obj;
	}

}

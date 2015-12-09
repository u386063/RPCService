package com.rpc.server.sender;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;

import com.rpc.data.RequestData;
import com.rpc.data.ResponseData;
import com.rpc.server.connection.RPCConnection;
import com.rpc.server.constants.Constants;

/*
 * Sender class for sending response to request.
 */
public class Sender extends RPCConnection{

	private Destination destination = null;
	private MessageProducer producer = null;

	/*
	 * Initializes the sender by creating a topic and its associated producer.
	 */
	public Sender()
	{
		try {

			destination = session.createTopic(propertyHandler.getValue(Constants.RPC_TOPIC));
			producer = session.createProducer(destination);

		} catch (JMSException e) {
			System.out.println("Failed to create SenderConnection");
			e.printStackTrace();
		}
	}


	/*
	 * Method used to send success response message
	 */
	public void sendResponseObject(String serviceResponse, RequestData requestData) throws JMSException {
		// This is a success message. set error code and send message
		sendResponseObject(0, serviceResponse, requestData);
	}

	/*
	 * Method used to send error messages
	 */
	public void sendErrorResponseObject(int errorCode, String message,	RequestData requestData)  {
		try {
			sendResponseObject(1, message, requestData);
		} catch (Exception e) {
			System.out.println("Caught exception while sending error response");
			e.printStackTrace();
		}
	}

	/*
	 * Method used to send response message
	 */
	public void sendResponseObject(int errorCode, String serviceResponse, RequestData requestData) throws JMSException {
		ResponseData responseMsg = new ResponseData(requestData);
		List<String> result = new ArrayList<String>();
		result.add(serviceResponse);
		responseMsg.setMethodReturnValue(result);
		responseMsg.setErrorCode(errorCode);
		ObjectMessage respObj = session.createObjectMessage();
		//Setting correlationId on ActiveMQ message itself.
		respObj.setJMSCorrelationID(requestData.getCorrelationId());
		respObj.setObject(responseMsg);
		producer.send(respObj);
	}

	/* 
	 * Special method that sends services supported by RPC Service
	 */
	public void sendResponseObject(Set<String> serviceList,
			RequestData requestData) throws JMSException {
		ResponseData responseMsg = new ResponseData(requestData);
		responseMsg.setMethodReturnValue(new ArrayList<String>(serviceList));
		responseMsg.setErrorCode(0);
		ObjectMessage respObj = session.createObjectMessage();
		//Setting correlation id on ActiveMQ message itself so that we can check .
		respObj.setJMSCorrelationID(requestData.getCorrelationId());
		respObj.setObject(responseMsg);
		producer.send(respObj);
	}

	/*
	 * Closes the session.
	 */
	public void closeSession(){
		if (session !=null){
			try {
				session.close();
			} catch (JMSException e) {
				System.out.println("Failed to close session");
				e.printStackTrace();
			}
		}

	}

	/*
	 * Closes the connection.
	 */
	public void closeConnection(){
		closeSession();
		if(this.getConnection() != null){
			try {
				this.getConnection().close();
			} catch (JMSException e) {
				System.out.println("Failed to close connection");
				e.printStackTrace();
			}
		}
	}

	/*
	 * Getter for destinaion.
	 */
	public Destination getDestination() {
		return destination;
	}
	
	/*
	 * Setter for destination.
	 */
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	
	/*
	 * Getter for message producer.
	 */
	public MessageProducer getProducer() {
		return producer;
	}
	
	/*
	 * Setter for message producer.
	 */
	public void setProducer(MessageProducer producer) {
		this.producer = producer;
	}

	/*
	 * Closes the connection on destruction.
	 * @see java.lang.Object#finalize()
	 */
	public void finalize(){
		closeConnection();
	}

}

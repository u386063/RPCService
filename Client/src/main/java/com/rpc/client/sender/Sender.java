package com.rpc.client.sender;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;

import com.rpc.client.connection.RPCConnection;
import com.rpc.client.constants.Constants;
import com.rpc.data.RequestData;


public class Sender extends RPCConnection{

	private Destination destination = null;
	private MessageProducer producer = null;

	/*
	 * Constructor for Sender. Creates a connection with queue as a producer of messages.
	 */
	public Sender()
	{
		try {

			destination = session.createQueue(propertyHandler.getValue(Constants.RPC_QUEUE));
			producer = session.createProducer(destination);

		} catch (JMSException e) {
			System.out.println("Failed to create SenderConnection");
			e.printStackTrace();
		}
	}

	/*
	 * Method that wraps request method into ObjectMethod and sends it to ActiveMQ
	 */
	public void sendObjectMessage(RequestData requestData) {
		try {
			ObjectMessage message = session.createObjectMessage();
			//Setting correlation id even on AMQ message to be on safer side.
			message.setJMSCorrelationID(requestData.getCorrelationId());
			message.setObject(requestData);
			producer.send(message);

		} catch (JMSException e) {
			System.out.println("Failed to send ObjectMessage SenderConnection");
			e.printStackTrace();
		}
	}

	/*
	 * Close Session 
	 */
	public void tearDownSession(){
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
	 * Closes both Session and Connection.
	 */
	public void closeConnection(){
		tearDownSession();
		if(this.getConnection() != null){
			try {
				this.getConnection().close();
			} catch (JMSException e) {
				System.out.println("Failed to close connection");
				e.printStackTrace();
			}
		}
	}

	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	public MessageProducer getProducer() {
		return producer;
	}
	public void setProducer(MessageProducer producer) {
		this.producer = producer;
	}

	public void finalize(){
		closeConnection();
	}
}

package com.rpc.server.receiver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.rpc.data.RequestData;
import com.rpc.server.connection.RPCConnection;
import com.rpc.server.constants.Constants;
import com.rpc.server.constants.TreeOperations;
import com.rpc.server.helper.TreeObjects;
import com.rpc.server.sender.Sender;

/**
 * The Receiver class creates the ActiveMQ Connection, Queue and Listener to
 * listen on the queue and processes the incoming messages.
 *
 * @author RaviTeja
 * @version 1.0
 */
public class Receiver extends RPCConnection {

	private Destination destination = null;
	private MessageConsumer messageConsumer = null;
	private MessageListener messageListener = null;

	public Receiver() {

		try {
			// Creating Queue,Consumer & Listener on creation of Receiver
			destination = session.createQueue(propertyHandler.getValue(Constants.RPC_QUEUE));
			messageConsumer = session.createConsumer(destination);
			messageListener = new ReceiverListener();
			messageConsumer.setMessageListener(messageListener);

		} catch (JMSException e) {
			System.out.println("Error Occured on creating Receiver");
			e.printStackTrace();
		}
	}

	/*
	 * Getter for destination.
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
	 * Getter for consumer.
	 */
	public MessageConsumer getConsumer() {
		return messageConsumer;
	}

	/*
	 * Setter for consumer.
	 */
	public void setConsumer(MessageConsumer messageConsumer) {
		this.messageConsumer = messageConsumer;
	}

	/*
	 * Getter for listener.
	 */
	public MessageListener getListener() {
		return messageListener;
	}

	/*
	 * Setter for listener.
	 */
	public void setListener(MessageListener listener) {
		this.messageListener = listener;
	}

	/*
	 * Closes the session.
	 */
	public void closeSession() {
		if (session != null) {
			try {
				session.close();
			} catch (JMSException e) {
				System.out.println("Error Occured on session close");
				e.printStackTrace();
			}
		}

	}

	/*
	 * Closes the connection.
	 */
	public void closeConnection() {
		closeSession();
		if (this.getConnection() != null) {
			try {
				this.getConnection().close();
			} catch (JMSException e) {
				System.out.println("Error Occured on connection close");
				e.printStackTrace();
			}
		}
	}

	/*
	 * Closes connection on destruction.
	 */
	public void finalize() {
		closeConnection();
	}

	/*
	 * The ReceiverListener class is nested class tied to the Receiver class to
	 * listen on queue and processes incoming messages
	 */
	class ReceiverListener implements MessageListener {

		Sender response;

		public ReceiverListener() {
			response = new Sender();
		}

		/*
		 * Assigns handler to incoming message.
		 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
		 */
		public void onMessage(Message message) {
			if (message instanceof ObjectMessage) {
				ObjectMessage objectMessage = (ObjectMessage) message;
				handleIncomingMessages(objectMessage);
			}
		}

		/*
		 * A handler for incoming message.
		 * Gets the tree operations based on the request data and sends response data.
		 */
		private void handleIncomingMessages(ObjectMessage objectMessage) {
			String requestMethodName;
			RequestData requestData = null;
			try {
				requestData = (RequestData) objectMessage.getObject();
				requestMethodName = requestData.getRequestMethodName();
				System.out.println("Received call for method : " + requestMethodName);
				if (requestMethodName.equalsIgnoreCase(Constants.SHOW_TREE_OPERATIONS)) {
					 Set<String> operationsSet = TreeOperations.getOperations();
					 response.sendResponseObject(operationsSet, requestData);
				} 
				//Added new condition to search the tree with 1 or more parameters - Start
				else if(requestMethodName.equalsIgnoreCase(Constants.SEARCH_NODE)){
					Object object = TreeObjects.getInstance().getObject(requestMethodName);
					if(object != null) {
					Method method = object.getClass().getMethod(requestMethodName, new Class[]{ArrayList.class});
					Object result = method.invoke(object, requestData.getParameterList());
					response.sendResponseObject((String) result, requestData);
					}
				}
				//Added new condition to search the tree with 1 or more parameters - End
				else {
					Object object = TreeObjects.getInstance().getObject(requestMethodName);
					if (object != null) {
						Object result = null;
						int parameterCount = requestData.getParameterCount();
						if (parameterCount == 0) {
							Method method = object.getClass().getMethod(requestMethodName);
							result = method.invoke(object);
							response.sendResponseObject((String) result, requestData);
						} else if (parameterCount==1) {
						    Method method = object.getClass().getMethod(requestMethodName, getParameterClasses(requestData.getParameterList()));
							result = method.invoke(object, requestData.getParameterList().get(0));
							response.sendResponseObject((String) result, requestData);
						} else {
							response.sendErrorResponseObject(1, "Message with wrong number of parameters received",
									requestData);
						}
						System.out.println("Response from " + requestMethodName + " = " + result.toString());
					} else {
						response.sendErrorResponseObject(2, "Received an request for invalid method message", requestData);
					}
				}

			} catch (JMSException e) {
				response.sendErrorResponseObject(1, "JMSException : " + e.getMessage(), requestData);
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				response.sendErrorResponseObject(1, "NoSuchMethodException : " + e.getMessage(), requestData);
				e.printStackTrace();
			} catch (SecurityException e) {
				response.sendErrorResponseObject(1, "SecurityException : " + e.getMessage(), requestData);
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				response.sendErrorResponseObject(1, "IllegalAccessException : " + e.getMessage(), requestData);
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				response.sendErrorResponseObject(1, "IllegalArgumentException : " + e.getMessage(), requestData);
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				response.sendErrorResponseObject(1, "InvocationTargetException : " + e.getMessage(), requestData);
				e.printStackTrace();
			}
		}

		/*
		 * Getter for parameter classes which returns type of parameter class.
		 */
		public Class[] getParameterClasses(List<Object> parameterList){		
			Class[] paremeterClasses = new Class[parameterList.size()];
			for(int index = 0;index<parameterList.size();index++){
				paremeterClasses[index] = parameterList.get(index).getClass();
			}
			return paremeterClasses;
		}
		
		/*
		 * Closes session on destruction.
		 * @see java.lang.Object#finalize()
		 */
		public void finalize() {
			response.closeSession();
		}

	}
}

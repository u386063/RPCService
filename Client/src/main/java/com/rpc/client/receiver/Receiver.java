package com.rpc.client.receiver;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.rpc.client.connection.RPCConnection;
import com.rpc.client.constants.Constants;
import com.rpc.client.correlator.MsgCorrelator;
import com.rpc.client.event.CallBack;
import com.rpc.data.ResponseData;

public class Receiver extends RPCConnection {

	private Destination destination = null;
	private MessageConsumer consumer = null;
	private MessageListener listener = null;

	public Receiver() {

		try {
			destination = session.createTopic(propertyHandler.getValue(Constants.RPC_TOPIC));
			consumer = session.createConsumer(destination);
			listener = new ReceiverListener();
			consumer.setMessageListener(listener);

		} catch (JMSException e) {
			System.out.println("Failed to create ReceiverConnection");
			e.printStackTrace();
		}
	}

	public void tearDownSession() {
		if (session != null) {
			try {
				session.close();
			} catch (JMSException e) {
				System.out.println("Failed to close session");
				e.printStackTrace();
			}
		}

	}

	public void closeConnection() {
		tearDownSession();
		if (this.getConnection() != null) {
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

	public MessageConsumer getConsumer() {
		return consumer;
	}

	public void setConsumer(MessageConsumer consumer) {
		this.consumer = consumer;
	}

	public MessageListener getListener() {
		return listener;
	}

	public void setListener(MessageListener listener) {
		this.listener = listener;
	}

	public void finalize() {
		closeConnection();
	}

	class ReceiverListener implements MessageListener {

		public void onMessage(Message message) {
			String correlationId = null;
			try {
				correlationId = message.getJMSCorrelationID();

				if (correlationId != null) {
					handleResponse(correlationId, message);
				} else {
					System.out.println("Received response without correlationId. Discarding message");
				}
			} catch (JMSException e) {
				System.out.println("Unable to process response message.");
				e.printStackTrace();
			}
		}

		private void handleResponse(String correlationId, Message message) throws JMSException {

			CallBack caller = (CallBack) MsgCorrelator.correlateObject(correlationId);
			if (caller != null) {
				MsgCorrelator.removeObject(correlationId);
				if (message instanceof ObjectMessage) {

					ObjectMessage objectMessage = (ObjectMessage) message;

					ResponseData response = (ResponseData) objectMessage.getObject();
					caller.callBackMethod(correlationId, response);
				}
			} else {
				System.out.println("This message is not intended to this client");
			}
		}

	}
}

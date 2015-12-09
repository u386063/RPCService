package com.rpc.client.connection;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;
import com.rpc.client.constants.Constants;
import com.rpc.client.util.PropertyHandler;

public class RPCConnection {

	private ConnectionFactory connectionFactory = null;
	private Connection connection = null;
	protected Session session = null;
	protected PropertyHandler propertyHandler = null;
	
	public RPCConnection()
	{
		propertyHandler = PropertyHandler.getInstance();
		connectionFactory = new ActiveMQConnectionFactory(propertyHandler.getValue(Constants.ACTIVEMQ_CONNECTION_URL));
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			System.out.println("Error Creating BaseConnection Object");
			e.printStackTrace();
		}
	}
	public ConnectionFactory getFactory() {
		return connectionFactory;
	}
	public void setFactory(ConnectionFactory factory) {
		this.connectionFactory = factory;
	}
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
}

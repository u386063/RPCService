package com.rpc.server.connection;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.rpc.server.constants.Constants;
import com.rpc.server.util.PropertyHandler;

/*
 * The RPCConnection class is used to create a connection with the remote party.
 * Getters and setters are used to get and set the individual  objects' values or properties.
 */
public class RPCConnection {

	private ConnectionFactory factory = null;
	private Connection connection = null;
	protected Session session = null;
	protected PropertyHandler propertyHandler = null;
	
	/*
	 * Initializes the RPC connection by first creating a factory object which uses the property
	 * handler. Then, a connection based on the factory is created which in turn is used to create 
	 * a session for the remote conneciton.
	 */
	public RPCConnection()
	{
		propertyHandler = PropertyHandler.getInstance();
		factory = new ActiveMQConnectionFactory(propertyHandler.getValue(Constants.ACTIVEMQ_CONNECTION_URL));
		try {
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			System.out.println("Error Creating BaseConnection Object");
			e.printStackTrace();
		}
	}
	
	/*
	 * Getter for factory object.
	 */
	public ConnectionFactory getFactory() {
		return factory;
	}
	
	/*
	 * Setters for factory object.
	 */
	public void setFactory(ConnectionFactory factory) {
		this.factory = factory;
	}
	
	/*
	 * Getter for connection object.
	 */
	public Connection getConnection() {
		return connection;
	}
	
	/*
	 * Setter for connection object.
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	/*
	 * Getter for session object.
	 */
	public Session getSession() {
		return session;
	}
	
	/*
	 * Setter for session object.
	 */
	public void setSession(Session session) {
		this.session = session;
	}
}

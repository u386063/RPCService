/**
 * 
 */
package com.rpc.server.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The PropertyHandler class is used to read the property file 
 * and load values from the file into constant variables of the RPCConnection class.
 * @author Ravi
 */
public class PropertyHandler {
	private static PropertyHandler instance = null;
	private Properties props = new Properties();

	private PropertyHandler() {
		try {
			 InputStream in = new FileInputStream(new File("app.properties"));
			 props.load(in);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	}

	public static PropertyHandler getInstance() {
		if (instance == null)
			instance = new PropertyHandler();
		return instance;
	}

	public String getValue(String propKey) {
		return props.getProperty(propKey);
	}

}

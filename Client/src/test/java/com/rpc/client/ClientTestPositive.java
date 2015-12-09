/**
 * 
 */
package com.rpc.client;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.rpc.client.constants.Constants;

public class ClientTestPositive {
	
	private String preOrderResult = "";
	private String inOrderResult = "";
	private String postOrderResult = "";
	private String addResult = "";
	private String searchResult = "";
	private String sizeResult = "";

	@Before
	public void setUp() throws Exception {
	
		preOrderResult = " 8  3  1  6  4  7  10  14  13 ";
		inOrderResult = " 1  3  4  6  7  8  10  13  14 ";
		postOrderResult = " 1  4  7  6  3  13  14  10  8 ";
		addResult = "66";
		searchResult = "true";
		sizeResult = "9";
		
	}
	
	@Test
	public void testPreOrder() {
		
		Client client = Client.getInstance();
		client.sendRequest(Constants.PRE_ORDER);
		assertEquals("Pre Order Test",preOrderResult,client.result);
	}
	
	@Test
	public void testPostOrder() {
		Client client = Client.getInstance();
		client.sendRequest(Constants.POST_ORDER);
		assertEquals("Post Order Test",postOrderResult,client.result);
	}
	
	@Test
	public void testInOrder() {
		Client client = Client.getInstance();
		client.sendRequest(Constants.IN_ORDER);
		assertEquals("In order Test",inOrderResult,client.result);
	}
	
	@Test
	public void testAdd() {
		Client client = Client.getInstance();
		client.sendRequest(Constants.ADD);
		assertEquals("Sum of Nodes Test",addResult,client.result);
	}
	
	@Test
	public void testSearch() {
		Client client = Client.getInstance();
		client.sendRequest(Constants.SEARCH_NODE, "8");
		assertEquals("Search Node Test",searchResult,client.result);
	}
	
	@Test
	public void testSize() {
		Client client = Client.getInstance();
		client.sendRequest(Constants.SIZE);
		assertEquals("Search Node Test",sizeResult,client.result);
	}
	
	@After
	public void tearDown() throws Exception {
	}
}

/**
 * 
 */
package com.rpc.client;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.rpc.client.constants.Constants;


public class ClientTestNegative {
	
	public String preOrderResult = "";
	public String inOrderResult = "";
	public String postOrderResult = "";
	public String addResult = "";
	public String searchResult = "";
	public String searchValue = "";
	public String sizeResult = "";
	
	
	@Before
	public void setUp() throws Exception {
	
		preOrderResult = " 8  3  1  6  4  7  10  13  14 ";
		inOrderResult = " 1  3  4  6  7  8  1  13  14 ";
		postOrderResult = " 1  4  7  3  6  14  13  10  8 ";
		addResult = "60";
		searchValue = "123";
		searchResult = "true";
		sizeResult = "91";
		
	}
	
	
	@Test
	public void testPreOrder(){
		
		Client client = Client.getInstance();
		client.sendRequest(Constants.PRE_ORDER);
		//Positive Output -  8  3  1  6  4  7  10  14  13 
		assertEquals("Pre Order Test",preOrderResult,client.result);
	}
	
	@Test
	public void testPostOrder(){
		Client client = Client.getInstance();
		client.sendRequest(Constants.POST_ORDER);
		//Positive Output -  1  4  7  6  3  13  14  10  8 
		assertEquals("Post Order Test",postOrderResult,client.result);
	}
	
	@Test
	public void testInOrder(){
		Client client = Client.getInstance();
		client.sendRequest(Constants.IN_ORDER);
		//Positive Output -  1  3  4  6  7  8  10  13  14  
		assertEquals("In order Test",inOrderResult,client.result);
	}
	
	@Test
	public void testAdd(){
		Client client = Client.getInstance();
		client.sendRequest(Constants.ADD);
		//Positive Output - 66
		assertEquals("Sum of Nodes Test",addResult,client.result);
	}
	
	@Test
	public void testSearch(){
		Client client = Client.getInstance();
		client.sendRequest(Constants.SEARCH_NODE, searchValue);
		//Positive Output - false
		assertEquals("Search Node Test",searchResult,client.result);
	}
	
	@Test
	public void testSize(){
		Client client = Client.getInstance();
		client.sendRequest(Constants.SIZE);
		//Positive Output - 9
		assertEquals("Search Node Test",sizeResult,client.result);
	}
	
	@After
	public void tearDown() throws Exception {
	}
}

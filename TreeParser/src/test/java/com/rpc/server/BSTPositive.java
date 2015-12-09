package com.rpc.server;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.rpc.server.binarysearchtree.BinarySearchTree;

public class BSTPositive {

	private String preOrderResult = "";
	private String inOrderResult = "";
	private String postOrderResult = "";
	private String addResult = "";
	private String searchResult = "";
	private String sizeResult = "";
	private BinarySearchTree bst = null;
	
	@Before
	public void setUp() throws Exception {
		bst = new BinarySearchTree();
		bst.insert(8);
		bst.insert(3);
		bst.insert(10);
		bst.insert(1);
		bst.insert(6);
		bst.insert(14);
		bst.insert(4);
		bst.insert(7);
		bst.insert(13);
		preOrderResult = " 8  3  1  6  4  7  10  14  13 ";
		inOrderResult = " 1  3  4  6  7  8  10  13  14 ";
		postOrderResult = " 1  4  7  6  3  13  14  10  8 ";
		addResult = "66";
		searchResult = "true";
		sizeResult = "9";
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testFindMax(){
		assertEquals("Maximum element in the binary search tree","14",bst.findMax());
	}
	
	@Test
	public void testFindMin(){
		assertEquals("Minimum element in the binary search tree","1",bst.findMin());
	}
	
	@Test
	public void testPreOrder() {
		assertEquals("Pre Order Test",preOrderResult,bst.preOrder());
	}
	
	@Test
	public void testInOrder() {
		assertEquals("In order Test",inOrderResult,bst.inOrder());
	}
	
	@Test
	public void testPostOrder() {
		assertEquals("Post Order Test",postOrderResult,bst.postOrder());
	}
	
	@Test
	public void testAdd() {
		assertEquals("Sum of Nodes Test",addResult,bst.add());
	}
	
	@Test
	public void testSearch() {
		assertEquals("Search Node Test",searchResult,bst.searchNode("8"));
	}
	
	@Test
	public void testSize() {
		assertEquals("Search Node Test",sizeResult,bst.size());
	}
	
	

}

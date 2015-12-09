package com.rpc.server.binarysearchtree;

/*
 * Binary search tree operations pertaining to a node are performed 
 * with the help of the members of this class. 
 */
public class BinarySearchTreeNode {
	
	private int data;
	private BinarySearchTreeNode leftNode;
	private BinarySearchTreeNode rightNode;
	
	/*
	 * Constructor to initialize the node with given data, left and right node values
	 */
	public BinarySearchTreeNode(int data, BinarySearchTreeNode leftNode, BinarySearchTreeNode rightNode) {
		this.data = data;
		this.leftNode = leftNode;
		this.rightNode = rightNode;
	}
	
	/*
	 * Get the data value of the node
	 */
	public int getData() {
		return data;
	}
	
	/*
	 * Set the data value of the node
	 */
	public void setData(int data) {
		this.data = data;
	}
	
	/*
	 * Getter for left node
	 */
	public BinarySearchTreeNode getLeftNode() {
		return leftNode;
	}
	
	/*
	 * Setter for left node
	 */
	public void setLeftNode(BinarySearchTreeNode leftNode) {
		this.leftNode = leftNode;
	}
	
	/*
	 * Getter for right node
	 */
	public BinarySearchTreeNode getRightNode() {
		return rightNode;
	}
	
	/*
	 * Setter for right node
	 */
	public void setRightNode(BinarySearchTreeNode rightNode) {
		this.rightNode = rightNode;
	}
}

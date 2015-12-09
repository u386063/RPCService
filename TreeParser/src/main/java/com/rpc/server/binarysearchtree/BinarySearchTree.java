package com.rpc.server.binarysearchtree;

/*
 * Binary search tree operations pertaining to the whole tree are performed through 
 * the members of this class.
 */
public class BinarySearchTree {

	private BinarySearchTreeNode binarySearchTreeNode = null;
	
	/*
	 * Insert new value into the tree.
	 */
	public void insert(int data){

		binarySearchTreeNode = insert(binarySearchTreeNode, data);
	}
	
	/*
	 * Traverse the tree recursively in order to find the appropriate position for
	 * insertion. then insert the value creating a new node and initializing
	 * its pointers appropriately.
	 */
	private BinarySearchTreeNode insert(BinarySearchTreeNode node, int data) {

		if (node == null){
			node = new BinarySearchTreeNode(data,null,null);
		}
		else{
			if(data>=node.getData()){
				node.setRightNode(insert(node.getRightNode(), data));
			}
			else{
				node.setLeftNode(insert(node.getLeftNode(), data));
			}
		}
		return node;
	}
	
	/*
	 * Inorder traversal of the tree
	 */
	public String inOrder(){
		StringBuffer stringBuffer = new StringBuffer();
		inOrder(binarySearchTreeNode, stringBuffer);
		return stringBuffer.toString();
	}
	
	/*
	 * Traverse the tree recursively and print the tree in "inorder" fashion
	 * i.e., left, root & right.
	 */
	private StringBuffer inOrder(BinarySearchTreeNode node, StringBuffer stringBuffer) {
		if(node != null){
			inOrder(node.getLeftNode(),stringBuffer);
			stringBuffer.append(" ");
			stringBuffer.append(node.getData());
			stringBuffer.append(" ");
			inOrder(node.getRightNode(),stringBuffer);
		}
		return stringBuffer;
	}
	
	/*
	 * Preorder traversal of the tree.
	 */	
	public String preOrder(){
		StringBuffer stringBuffer = new StringBuffer();
		preOrder(binarySearchTreeNode, stringBuffer);
		return stringBuffer.toString();
	}
	
	/*
	 * Traverse the tree recursively and print the tree in "ipreorder" fashion
	 * i.e., root, left & right.
	 */
	private StringBuffer preOrder(BinarySearchTreeNode node, StringBuffer stringBuffer){
		
		if(node != null){
			stringBuffer.append(" ");
			stringBuffer.append(node.getData());
			stringBuffer.append(" ");
			preOrder(node.getLeftNode(),stringBuffer);
			preOrder(node.getRightNode(),stringBuffer);
		}
		return stringBuffer;
	}
	
	/*
	 * Postorder traversal of the tree.
	 */
	public String postOrder(){
		StringBuffer stringBuffer = new StringBuffer();
		postOrder(binarySearchTreeNode, stringBuffer);
		return stringBuffer.toString();
	}
	
	/*
	 * Traverse the tree recursively and print the tree in "postorder" fashion
	 * i.e., left, right & root.
	 */
	private StringBuffer postOrder(BinarySearchTreeNode node, StringBuffer stringBuffer){
		
		if(node != null){
			postOrder(node.getLeftNode(),stringBuffer);
			postOrder(node.getRightNode(),stringBuffer);
			stringBuffer.append(" ");
			stringBuffer.append(node.getData());
			stringBuffer.append(" ");
		}
		return stringBuffer;
	}
	
	/*
	 * Search the tree for a particular node.
	 */
	public String searchNode(String data){
		Integer d = Integer.parseInt(data);
		Boolean isFound =  searchNode(binarySearchTreeNode, d);
		System.out.println("search return value =" + isFound);
		return isFound.toString();
	}
	
	/*
	 * Traverse the tree in binary search mode. 
	 * i.e., check the root, if element is found - return.
	 * else traverse the right sub-tree if data is greater than root's
	 * else traverse the left sub-tree
	 */
	private Boolean searchNode(BinarySearchTreeNode node, Integer data) {
		if(node==null){
			return false;
		}
		else if(node.getData() == data){
			return true;
		} else if(data>node.getData()){
			return searchNode(node.getRightNode(), data);
		} else{
			return searchNode(node.getLeftNode(), data);
		}
		
	}
	
	/*
	 * Returns size of the tree i.e., no. of nodes in the tree.
	 */
	public String size(){
		Integer size = size(binarySearchTreeNode);
		return size.toString();
	}
	
	/*
	 * Calculates the size of the by counting no. of left nodes
	 * and right nodes and increments the count by 1
	 */
	private Integer size(BinarySearchTreeNode node){
		if(node==null){
			return 0;
		}
		else{
			return(size(node.getLeftNode())+ 1 + size(node.getRightNode()));
		}
	}
	
	/*
	 * Returns the sum of all elements in the tree
	 */
	public String add(){
		Integer total = add(binarySearchTreeNode);
		return total.toString();
	}
	
	/*
	 * Calculate the sum of elements present in the tree by traversing every node recursively.
	 */
	private Integer add(BinarySearchTreeNode node){
		if(node==null){
			return 0;
		}
		else{
			return(node.getData() +add(node.getLeftNode())+ add(node.getRightNode()));
		}
	}
	
	public String findMax(){
		Integer maxNode = findMax(binarySearchTreeNode).getData();
		return maxNode.toString();
	}
	
	private BinarySearchTreeNode findMax(BinarySearchTreeNode node){
		if(node==null)
			return null;
		else 
		{
			if(node.getRightNode()==null)
				return node;
			else
				return findMax(node.getRightNode());
		}
	}
	
	public String findMin(){
		Integer minNode = findMin(binarySearchTreeNode).getData();
		return minNode.toString();
	}
	
	private BinarySearchTreeNode findMin(BinarySearchTreeNode node){
		if(node==null)
			return null;
		else 
		{
			if(node.getLeftNode()==null)
				return node;
			else
				return findMin(node.getLeftNode());
		}
	}	 

}

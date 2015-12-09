package com.rpc.server;

import com.rpc.server.binarysearchtree.BinarySearchTree;
import com.rpc.server.helper.TreeObjects;
import com.rpc.server.receiver.Receiver;

/**
 * The Server class initializes the Binary Search Tree and creates the Receiver
 * to listen on the queue and processes the incoming messages and Sender sends
 * the response to the queue
 *
 * @author RaviTeja
 * @version 1.0
 */
public class Server {
	// On class load Binary Search Tree is getting initialized
	static {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		binarySearchTree.insert(8);
		binarySearchTree.insert(3);
		binarySearchTree.insert(10);
		binarySearchTree.insert(1);
		binarySearchTree.insert(6);
		binarySearchTree.insert(14);
		binarySearchTree.insert(4);
		binarySearchTree.insert(7);
		binarySearchTree.insert(13);
		TreeObjects.getInstance().addObject(binarySearchTree);
	}

	public static void main(String[] args) {
		new Receiver(); // Creating Receiver to listen on the queue to processes
						// the incoming messages
		System.out.println("Server started - Listening to the queue");
	}

}

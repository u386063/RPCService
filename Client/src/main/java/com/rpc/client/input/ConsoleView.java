package com.rpc.client.input;

import java.util.Scanner;

import com.rpc.client.Client;
import com.rpc.client.constants.Constants;

public class ConsoleView {

	public static void main(String[] args) {
		
		Client client = Client.getInstance();
		client.sendRequest(Constants.SHOW_TREE_OPERATIONS);
		System.out.println("Enter the your choice. Type 0 to exit");
		Scanner scanner = new Scanner(System.in);
		while(true){
			Integer choice = scanner.nextInt();
			switch(choice){
			case 1: client.sendRequest(Constants.ADD);break;
			case 2: client.sendRequest(Constants.MAX_NODE);break;
			case 3: client.sendRequest(Constants.IN_ORDER);break;
			case 4: client.sendRequest(Constants.POST_ORDER);break;
			case 5: client.sendRequest(Constants.PRE_ORDER);break;
			case 6: System.out.println("Enter the search node");
					String searchNode = scanner.next();
					client.sendRequest(Constants.SEARCH_NODE, searchNode);
					break;
			case 7: client.sendRequest(Constants.SIZE);break;
			case 0: System.exit(0);break;
			default: System.out.println("Invalid choice. Please try again!!");
			}
		}

	}

}

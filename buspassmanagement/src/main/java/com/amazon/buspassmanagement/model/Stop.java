package com.amazon.buspassmanagement.model;

import java.util.Scanner;

/*

MySQL:
create table Stop(
	id INT PRIMARY KEY AUTO_INCREMENT,
	address VARCHAR(256),
	sequenceOrder INT,
	routeId INT,
	adminId INT,
	createdOn DATETIME DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (routeId) REFERENCES Route(id),
	FOREIGN KEY (adminId) REFERENCES User(id)
);

*/

public class Stop {
	
	// Attributes
	public int id;
	public String address;
	public int sequenceOrder;
	public int routeId;
	public int adminId;
	public String createdOn;
	
	public Stop() {
	
	}

	public Stop(int id, String address, int sequenceOrder, int routeId, int adminId, String createdOn) {
		this.id = id;
		this.address = address;
		this.sequenceOrder = sequenceOrder;
		this.routeId = routeId;
		this.adminId = adminId;
		this.createdOn = createdOn;
	}
	
	public void getDetails(boolean updateMode) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Capturing Stop Details....");
		
		System.out.println("Enter Stop Address:");
		address = scanner.nextLine();
		
		System.out.println("Enter Sequence Order:");
		sequenceOrder = Integer.parseInt(scanner.nextLine());
		
		System.out.println("Enter Route ID:");
		routeId = Integer.parseInt(scanner.nextLine());
		
		if(updateMode) {
			System.out.println("Enter Stop ID to update:");
			id = Integer.parseInt(scanner.nextLine());
		}

	}
	
	public void prettyPrint() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Address:\t\t"+address);
		System.out.println("Sequence:\t\t"+sequenceOrder);
		System.out.println("Route:\t\t"+routeId);
		System.out.println("Admin:\t\t"+adminId);
		System.out.println("Created On:\t"+createdOn);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
	}

	@Override
	public String toString() {
		return "Stop [id=" + id + ", address=" + address + ", sequenceOrder=" + sequenceOrder + ", routeId=" + routeId
				+ ", adminId=" + adminId + ", createdOn=" + createdOn + "]";
	}
	
}

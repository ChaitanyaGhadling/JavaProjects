package com.amazon.buspassmanagement.model;

import java.util.Scanner;

/*

MySQL:
create table Vehicle(
	id INT PRIMARY KEY AUTO_INCREMENT,
	registrationNumber VARCHAR(256),
	totalSeats INT,
	filledSeats INT,
	routeId INT,
	type INT,
	vehicleStatus INT,
	startPickUpTime VARCHAR(256),
	startDropOffTime VARCHAR(256),
	adminId INT,
	driverID INT,
	createdOn DATETIME DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (routeId) REFERENCES Route(id),
	FOREIGN KEY (adminId) REFERENCES User(id),
);

*/

public class Vehicle {
	
	public int id;
	public String registrationNumber;
	public int totalSeats;
	public int filledSeats;
	public int routeId;
	public int type;		  // 1: bus, 2: innova
	public int vehicleStatus; // 1: available, 2: not available
	public String startPickUpTime;
	public String startDropOffTime;
	public int adminId;
	public int driverID;
	public String createdOn;
	
	public Vehicle() {
	
	}

	public Vehicle(int id, String registrationNumber, int totalSeats, int filledSeats, int routeId, int type,
			int vehicleStatus, String startPickUpTime, String startDropOffTime, int adminId, int driverID,
			String createdOn) {
		this.id = id;
		this.registrationNumber = registrationNumber;
		this.totalSeats = totalSeats;
		this.filledSeats = filledSeats;
		this.routeId = routeId;
		this.type = type;
		this.vehicleStatus = vehicleStatus;
		this.startPickUpTime = startPickUpTime;
		this.startDropOffTime = startDropOffTime;
		this.adminId = adminId;
		this.driverID = driverID;
		this.createdOn = createdOn;
	}



	public void getDetails(boolean updateMode) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Capturing Vehicle Details....");
		if(updateMode) {
			System.out.println("Enter Vehicle ID for Update:");
			id = Integer.parseInt(scanner.nextLine());
		}
		
		System.out.println("Enter Registration Number:");
		registrationNumber = scanner.nextLine();
		
		System.out.println("Enter Start Pick Up Time:");
		startPickUpTime = scanner.nextLine();
		
		System.out.println("Enter Start Drop Off Up Time:");
		startDropOffTime = scanner.nextLine();
		
		System.out.println("Enter Total Seats:");
		totalSeats = Integer.parseInt(scanner.nextLine());
		
		System.out.println("Enter Filled Seats:");
		filledSeats = Integer.parseInt(scanner.nextLine());
		
		System.out.println("Enter Vehcile Type (1: Bus 2: Innova) :");
		type = Integer.parseInt(scanner.nextLine());
		
		System.out.println("Enter Vehcile Status (1: Available 2: Unavailable) :");
		vehicleStatus = Integer.parseInt(scanner.nextLine());
		
		System.out.println("Enter Route ID:");
		routeId = Integer.parseInt(scanner.nextLine());
		
		System.out.println("Enter Driver ID:");
		driverID = Integer.parseInt(scanner.nextLine());
		


	}
	
	public void prettyPrint() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Registration:\t\t"+registrationNumber);
		System.out.println("Total Seats:\t\t"+totalSeats);
		System.out.println("Filled Seats:\t\t"+filledSeats);
		System.out.println("Available Seats:\t\t"+(totalSeats - filledSeats));
		
		String vehicle = (type == 1) ? "BUS" : "INNOVA";
		System.out.println("Vehicle Type:\t\t"+vehicle);
		
		String status =  (vehicleStatus == 1) ? "AVAILABLE" : "UAVAILABLE";
		System.out.println("Vehicle Status:\t\t"+status);
		
		System.out.println("Start PickUp Time:\t\t"+startPickUpTime);
		System.out.println("Start DropOff Time::\t\t"+startDropOffTime);
		
		System.out.println("Route:\t\t"+routeId);
		System.out.println("Driver:\t\t"+driverID);
		System.out.println("Admin:\t\t"+adminId);
		System.out.println("Created On:\t"+createdOn);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", registrationNumber=" + registrationNumber + ", totalSeats=" + totalSeats
				+ ", filledSeats=" + filledSeats + ", routeId=" + routeId + ", type=" + type + ", vehicleStatus="
				+ vehicleStatus + ", startPickUpTime=" + startPickUpTime + ", startDropOffTime=" + startDropOffTime
				+ ", adminId=" + adminId + ", driverID=" + driverID + ", createdOn=" + createdOn + "]";
	}


}

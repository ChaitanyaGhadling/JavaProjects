package com.amazon.buspassmanagement.model;

import java.util.Scanner;


/*

MySQL:
create table BusPass(
	id INT PRIMARY KEY AUTO_INCREMENT,
	uid INT,
	routeId INT,
	requestedOn DATETIME DEFAULT CURRENT_TIMESTAMP,
	approvedRejectedOn DATETIME,
	validTill DATETIME,
	status INT DEFAULT 1,
	FOREIGN KEY (uid) REFERENCES User(id),
	FOREIGN KEY (routeId) REFERENCES Route(id)
);

*/

public class BusPass {
	
	public int id;
	public int uid;
	public int routeId;
	public String requestedOn;
	public String approvedRejectedOn;
	public String validTill;
	public int status;
	
	public BusPass() {
	
	}

	public BusPass(int id, int uid, int routeId, String requestedOn, String approvedRejectedOn, String validTill,
			int status) {
		this.id = id;
		this.uid = uid;
		this.routeId = routeId;
		this.requestedOn = requestedOn;
		this.approvedRejectedOn = approvedRejectedOn;
		this.validTill = validTill;
		this.status = status;
	}
	
	public void getDetails(boolean updateMode) {
		
		Scanner scanner = new Scanner(System.in);
				
		System.out.println("Capturing Bus Pass Details....");
		
		System.out.println("Enter Route ID to apply for Bus Pass:");
		routeId = Integer.parseInt(scanner.nextLine());
		
	}
	
	public void prettyPrint() {
		System.out.println("~~~~~~~~~~~BusPass~~~~~~~~~~");
		System.out.println("Pass ID:\t\t"+id);
		System.out.println("User ID:\t\t"+uid);
		System.out.println("Route ID:\t\t"+routeId);
		System.out.println("Updated On:\t\t"+approvedRejectedOn);
		System.out.println("Valid Till:\t\t"+validTill);
		
		String statusText = "";
		
		if(status == 1) {
			statusText = "Requested";
		}else if (status == 2) {
			statusText = "Approved";
		}else if (status == 3) {
			statusText = "Canceled";
		}else {
			statusText = "Suspended";
		}
		
		System.out.println("Status:\t\t"+statusText);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
	}

	@Override
	public String toString() {
		return "BusPass [id=" + id + ", uid=" + uid + ", routeId=" + routeId + ", requestedOn=" + requestedOn
				+ ", approvedRejectedOn=" + approvedRejectedOn + ", validTill=" + validTill + ", status=" + status + "]";
	}
	
}

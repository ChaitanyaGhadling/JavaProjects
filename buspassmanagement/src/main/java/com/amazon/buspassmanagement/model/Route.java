package com.amazon.buspassmanagement.model;

import java.util.Scanner;

/*

MySQL:
create table Route(
	id INT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(256),
	description VARCHAR(256),
	adminId INT,
	createdOn DATETIME DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (adminId) REFERENCES User(id)
);

*/
// Model or POJO (Plain Old Java Object) or Bean
public class Route {
	
	// Attributes :)
    public int id;
    public String title;
    public String description;
    public int adminId;
    public String createdOn;
    
    public Route() {
    	
    }
    
	public Route(int id, String title, String description, int adminId, String createdOn) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.adminId = adminId;
		this.createdOn = createdOn;
	}

	public void getDetails(boolean updateMode) {
		
		Scanner scanner = new Scanner(System.in);
				
		System.out.println("Capturing Route Details....");
		if(updateMode) {
			System.out.println("Enter Route ID to be updated:");
			id = Integer.parseInt(scanner.nextLine());
		}
		
		System.out.println("Enter Route Title:");
		title = scanner.nextLine();
		
		System.out.println("Enter Route Description:");
		description = scanner.nextLine();
		


	}
	
	public void prettyPrint() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Route ID:\t\t"+id);
		System.out.println("Title:\t\t"+title);
		System.out.println("Description:\t\t"+description);
		System.out.println("Admin:\t\t"+adminId);
		System.out.println("Created On:\t"+createdOn);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
	}

	
	@Override
	public String toString() {
		return "Route [id=" + id + ", title=" + title + ", description=" + description + ", adminId=" + adminId
				+ ", createdOn=" + createdOn + "]";
	}
    
    
}

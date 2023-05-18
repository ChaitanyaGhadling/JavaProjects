package com.amazon.buspassmanagement.model;

import java.util.Scanner;

/*

MySQL:
create table Feedback(
	id INT PRIMARY KEY AUTO_INCREMENT,
	userId INT,
	title VARCHAR(256),
	description VARCHAR(2048),
	type INT,
	raisedBy VARCHAR(256),
	createdOn DATETIME DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (userId) REFERENCES User(id)
);

*/

public class Feedback {

	public int id;
	public int userId;	// user id for the user who raised the feedback
	public String title;
	public String description;
	public int type;
	public String raisedBy; // email
	public String createdOn;
	
	public Feedback() {
	
	}

	public Feedback(int id, int userId, String title, String description, int type, String raisedBy, String createdOn) {
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.description = description;
		this.type = type;
		this.raisedBy = raisedBy;
		this.createdOn = createdOn;
	}
	
	public void getDetails() {
		
		Scanner scanner = new Scanner(System.in);
				
		System.out.println("Capturing Feedback Details....");
		
		System.out.println("1: Suggestion");
		System.out.println("2: Complaint");
		System.out.println("3: Pass Suspension");
		System.out.println("Select Type of Feedback:");
		type = Integer.parseInt(scanner.nextLine());
		
		if(type == 1) {
			title = "Suggestion";
		}else if(type == 2) {
			title = "Complaint";
		}else if(type == 3) {
			title = "Pass Suspension";
		}else {
			title = "";
		}
		
		System.out.println("Enter Description:");
		description = scanner.nextLine();

	}
	
	public void prettyPrint() {
		System.out.println("~~~~~~~~~~~Feedback~~~~~~~~~~");
		System.out.println("Feedback ID:\t\t"+id);
		System.out.println("Title:\t\t"+title);
		System.out.println("Description:\t\t"+description);
		System.out.println("Type:\t\t"+type);
		System.out.println("Raised By:\t\t"+raisedBy);
		System.out.println("Created On:\t\t"+createdOn);
	}

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", userId=" + userId + ", title=" + title + ", description=" + description
				+ ", type=" + type + ", raisedBy=" + raisedBy + ", createdOn=" + createdOn + "]";
	}
	
}

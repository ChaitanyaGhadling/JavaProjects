package com.amazon.buspassmanagement;

import java.util.Date;

import com.amazon.buspassmanagement.model.User;

public class AdminMenu extends Menu{
		
	private static AdminMenu menu = new AdminMenu();
	
	public static AdminMenu getInstance() {

		return menu;
	}
	
	private AdminMenu() {
		
	}

	public void showMenu() {
		
		System.out.println("Navigating to Admin Menu...");
		
		// Login Code should come before the Menu becomes Visible to the Admin
		User adminUser = new User();
		
		System.out.println("Enter Your Email:");
		adminUser.email = scanner.nextLine();
		
		System.out.println("Enter Your Password:");
		adminUser.password = scanner.nextLine();
		
		boolean result = auth.loginUser(adminUser,1);
		
		if(result) {
			
			// Link the Admin User to the Session User :)
			BusPassSession.user = adminUser;
		
			System.out.println("*********************");
			System.out.println("Welcome to Admin App");
			System.out.println("Hello, "+adminUser.name);
			System.out.println("Its: "+new Date());
			System.out.println("*********************");
			
			boolean quit = false;
			
			while(true) {
	        	System.out.println("1: Manage Routes"); // here we mean -> add, update, delete and view :)
	        	System.out.println("2: Manage Stops");
	        	System.out.println("3: Manage Vehicles");
	        	System.out.println("4: Manage Bus Pass"); // approve or reject
	        	System.out.println("5: Manage Feedbacks"); //
				System.out.println("6: View Expired Passes");
				System.out.println("7: View Passes between particular dates");
	        	System.out.println("8: Quit Admin App");
	        	System.out.println("Select an Option");
	        	
	        	int choice = Integer.parseInt(scanner.nextLine());
	        	
	        	switch (choice) {
					case 1:
						
						System.out.println("1: Add Route");
						System.out.println("2: Update Route");
						System.out.println("3: Delete Route");
						System.out.println("4: View Routes");
						
						System.out.println("Enter Your Choice: ");
			        	int routeChoice = Integer.parseInt(scanner.nextLine());
						
						if(routeChoice == 1) {
							routeService.addRoute();
						}else if(routeChoice == 2) {
							routeService.updateRoute();
						}else if (routeChoice == 3) {
							routeService.deleteRoute();
						}else if(routeChoice == 4) {
							routeService.viewRoutes();
						}else {
							System.err.println("Invalid Route Choice..");
						}
						
						break;
						
					case 2:
						
						System.out.println("1: Add Stop");
						System.out.println("2: Update Stop");
						System.out.println("3: Delete Stop");
						System.out.println("4: View Stops");
						
						System.out.println("Enter Your Choice: ");
			        	int stopChoice = Integer.parseInt(scanner.nextLine());
						
						if(stopChoice == 1) {
							routeService.addStop();
						}else if(stopChoice == 2) {
							routeService.updateStop();
						}else if (stopChoice == 3) {
							routeService.deleteStop();
						}else if(stopChoice == 4) {
							routeService.viewStops();
						}else {
							System.err.println("Invalid Stop Choice..");
						}
						
						break;
	
					case 3:
						
						System.out.println("1: Add Vehicle");
						System.out.println("2: Update Vehicle");
						System.out.println("3: Delete Vehicle");
						System.out.println("4: View Vehicle");
						
						System.out.println("Enter Your Choice: ");
			        	int vehicleChoice = Integer.parseInt(scanner.nextLine());
						
						if(vehicleChoice == 1) {
							routeService.addVehicle();
						}else if(vehicleChoice == 2) {
							routeService.updateVehicle();
						}else if (vehicleChoice == 3) {
							routeService.deleteVehicle();
						}else if(vehicleChoice == 4) {
							routeService.viewVehicles();
						}else {
							System.err.println("Invalid Vehicle Choice..");
						}
						
						break;
						
					case 4:
						
						System.out.println("1: View Pass Requests");
						System.out.println("2: View Pass Request By UID");
						System.out.println("3: Update Pass Request");
						System.out.println("4: Delete Pass Request");
						
						System.out.println("Enter Your Choice: ");
			        	int passChoice = Integer.parseInt(scanner.nextLine());
						
						if(passChoice == 1) {
							passService.viewPassRequests();
						}else if(passChoice == 2) {
							System.out.println("Enter User ID: ");
							int uid = Integer.parseInt(scanner.nextLine());
							passService.viewPassRequestsByUser(uid);
						}else if(passChoice == 3) {
							passService.approveRejectPassRequest();
						}else if (passChoice == 4) {
							passService.deletePass();
						}else {
							System.err.println("Invalid Pass Choice..");
						}
						
						break;
						
					case 5:
						
						System.out.println("1: View Feedbacks");
						System.out.println("2: View Feedbacks by User");
						
						System.out.println("Enter Your Choice: ");
			        	int feedbackChoice = Integer.parseInt(scanner.nextLine());
			        	
			        	if(feedbackChoice == 1) {
							feedbackService.viewFeedbacks();
						}else if(feedbackChoice == 2) {
							System.out.println("Enter User ID: ");
							int uid = Integer.parseInt(scanner.nextLine());
							feedbackService.viewFeedbacksByUser(uid);
						}else {
							System.err.println("Invalid Pass Choice..");
						}
						
						break;
					case 6:
						passService.viewExpiredPass();
						break;

					case 7:
						passService.viewPassBetweenDate();
						break;

					case 8:
						System.out.println("Thank You for Using Admin App !!");
						quit = true;
						break;
		
					default:
						System.err.println("Invalid Choice...");
						break;
				}
	        	
	        	if(quit) {
	        		break;
	        	}
	        	
	        }
		}else {
			System.err.println("Invalid Credentials. Please Try Again !!");
		}
	}
	
}

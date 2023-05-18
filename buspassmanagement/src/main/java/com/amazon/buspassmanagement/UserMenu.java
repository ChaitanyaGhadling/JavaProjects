package com.amazon.buspassmanagement;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;

import com.amazon.buspassmanagement.model.User;

public class UserMenu extends Menu{
		
	private static UserMenu menu = new UserMenu();
	
	public static UserMenu getInstance() {
		return menu;
	}
	
	private UserMenu() {
		
	}
	
	public void showMenu() {
		
		System.out.println("Navigating to User Menu...");
		
		System.out.println("1: Register");
		System.out.println("2: Login");
		System.out.println("3: Cancel");
		
		System.out.println("Enter Your Choice: ");
		int initialChoice = Integer.parseInt(scanner.nextLine());
		
		boolean result = false;
		
		User user = new User();
		
		
		if(initialChoice == 1) {
			
			System.out.println("Enter Your Name:");
			user.name = scanner.nextLine();
			
			System.out.println("Enter Your Phone:");
			user.phone = scanner.nextLine();
			
			System.out.println("Enter Your Email:");
			user.email = scanner.nextLine();
			
			System.out.println("Enter Your Password:");
			user.password = scanner.nextLine();
			
			try {
				// Hash the Password of User :)
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
				byte[] hash = digest.digest(user.password.getBytes(StandardCharsets.UTF_8));
				user.password = Base64.getEncoder().encodeToString(hash);
			}catch (Exception e) {
				System.err.println("Something Went Wrong: "+e);
			}
			
			System.out.println("Enter Your Address:");
			user.address = scanner.nextLine();
			
			System.out.println("Enter Your Department:");
			user.department = scanner.nextLine();
			
			// As we know, user is registering :)
			user.type = 2;
			
			result = auth.registerUser(user);
			
		}else if(initialChoice == 2) {
			
			System.out.println("Enter Your Email:");
			user.email = scanner.nextLine();
			
			System.out.println("Enter Your Password:");
			user.password = scanner.nextLine();
			
			try {
				// Encoded to Hash i.e. SHA-256 so as to match correctly
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
				byte[] hash = digest.digest(user.password.getBytes(StandardCharsets.UTF_8));
				user.password = Base64.getEncoder().encodeToString(hash);
			}catch (Exception e) {
				System.err.println("Something Went Wrong: "+e);
			}
			
			result = auth.loginUser(user,2);
			
		}else if(initialChoice == 3) {
			System.out.println("Thank You for Using Bus Pass App");
		}else {
			System.err.println("Invalid Choice...");
			System.out.println("Thank You for Using Bus Pass App");
		}
		
		
		if(result) {
		
			// Link the User to the Session User :)
			BusPassSession.user = user;
			
			System.out.println("^^^^^^^^^^^^^^^^^^^");
			System.out.println("Welcome to User App");
			System.out.println("Hello, "+user.name);
			System.out.println("Its: "+new Date());
			System.out.println("^^^^^^^^^^^^^^^^^^^");
			
			boolean quit = false;
			
			while(true) {
	        	
	        	System.out.println("1: View Routes");
	        	System.out.println("2: Apply For Bus Pass");
	        	System.out.println("3: My Bus Pass");
	        	System.out.println("4: Request Pass Suspension");
	        	System.out.println("5: Write Feedback");
	        	System.out.println("6: My Profile");
	        	System.out.println("7: Quit User App");
	        	System.out.println("Select an Option");
	        	
	        	int choice = Integer.parseInt(scanner.nextLine());

	        	switch (choice) {
					case 1:
						routeService.viewRoutes();
						break;
						
					case 2:
						routeService.viewRoutes();
						System.out.println("****************************");
						passService.requestPass();
						break;
	
					case 3:
						passService.viewPassRequestsByUser(BusPassSession.user.id);
						break;
						
					case 4:
						passService.requestPassSuspension(BusPassSession.user.id);
						break;
					case 5:
						feedbackService.createFeedback();
						break;
						
					case 6:
						System.out.println("My Profile");
						user.prettyPrint();
						
						System.out.println("Do you wish to update Profile (1: update 0: cancel)");
						
			        	choice = Integer.parseInt(scanner.nextLine());

						
						if(choice == 1) {
							
							
							System.out.println("Enter Your Name:");
							String name = scanner.nextLine();
							if(!name.isEmpty()) {
								user.name = name;
							}
							
							System.out.println("Enter Your Phone:");
							String phone = scanner.nextLine();
							if(!phone.isEmpty()) {
								user.phone = phone;
							}
							
							System.out.println("Enter Your Password:");
							String password = scanner.nextLine();
							if(!password.isEmpty()) {
								user.password = password;
							}
							
							System.out.println("Enter Your Address:");
							String address = scanner.nextLine();
							if(!address.isEmpty()) {
								user.address = address;
							}
							
							System.out.println("Enter Your Department:");
							String department = scanner.nextLine();
							if(!department.isEmpty()) {
								user.department = department;
							}
							
							if(auth.updateUser(user)) {
								System.out.println("Profile Updated Successfully");
							}else {
								System.err.println("Profile Update Failed...");
							}
							
						}
						break;
	
					case 7:
						System.out.println("Thank You for Using User App !!");
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
			System.err.println("Authentication Failed..");
		}
	}
	
	
}

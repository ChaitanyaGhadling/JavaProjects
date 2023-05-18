package classified;

import classified.model.User;

import java.util.Date;

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
		
		boolean result = auth.loginUser(adminUser);
		
		if(result) {
			
			// Link the Admin User to the Session User :)
			ClassifiedsSession.user = adminUser;
		
			System.out.println("*********************");
			System.out.println("Welcome to Admin App");
			System.out.println("Hello, "+adminUser.name);
			System.out.println("Its: "+new Date());
			System.out.println("*********************");
			
			boolean quit = false;
			
			while(true) {
	        	System.out.println("1: Add Classified");
	        	System.out.println("2: Remove Classified");
	        	System.out.println("3: Approve/Reject Classified Request");
				System.out.println("4: View Classifieds");
	        	System.out.println("5: Activate/Deactivate User");
				System.out.println("6: Manage Type/Category of Classified");
				System.out.println("7: Generate Reports");
	        	System.out.println("8: Quit Admin App");
	        	System.out.println("Select an Option");
	        	
	        	int choice = Integer.parseInt(scanner.nextLine());
	        	
	        	switch (choice) {
					case 1:
						classService.addClassified();
						break;
					case 2:
						classService.removeClassified();
						break;
					case 3:
						classService.approveRejectClassified();
						break;
					case 4:
						System.out.println("1: View all Classifieds");
						System.out.println("2: Search Classified by ID");
						System.out.println("Enter Your Choice: ");
			        	int ch = Integer.parseInt(scanner.nextLine());

						if(ch == 1) {
							classService.viewClassified();
						}else if(ch == 2) {
							System.out.println("Enter ID of Classified you want to view: ");
							int id = Integer.parseInt(scanner.nextLine());
							classService.viewClassified(id);
						}
						else {
							System.err.println("Invalid Choice..");
						}
						break;
					case 5:
						
						System.out.println("Enter the ID of User you want to activate/deactivate");
			        	int userID = Integer.parseInt(scanner.nextLine());
						auth.ChangeUserStatus(userID);
						break;
						
					case 6:
						System.out.println("Enter the ID of Classified you want to change type/category of:");
						int id = Integer.parseInt(scanner.nextLine());
						classService.UpdateType(id);
						break;

					case 7:
						break;
						
					case 8:
						System.out.println("Thank You for Using Admin Menu !!");
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

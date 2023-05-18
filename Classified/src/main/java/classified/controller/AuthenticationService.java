package classified.controller;

import classified.db.UserDAO;
import classified.model.Classified;
import classified.model.User;

import java.util.List;
import java.util.Scanner;

public class AuthenticationService {
	
	private static AuthenticationService service = new AuthenticationService();
	UserDAO dao = new UserDAO();
	Scanner scanner = new Scanner(System.in);

	private AuthenticationService(){

	}
	
	public static AuthenticationService getInstance() {
		return service;
	}
	
	/*public boolean loginUser(User user) {
		
		boolean result = false;
		
		
		Iterator<Integer> itr = users.keySet().iterator();
		while(itr.hasNext()) {
			Integer key = itr.next();
			
			User u = users.get(key);
			// Select * from User where email = '' and password = ''
			if(u.email.equals(user.email) && u.password.equals(user.password)) {
				// user will now point to a new Object i.e. referred by u
				user.name = u.name;
				user.type = u.type; 
				result = true;
				break;
			}
		
		}
		return result;
	}*/
	
	public boolean loginUser(User user) {
				
		String sql = "SELECT * FROM User WHERE email = '"+user.email+"' AND password = '"+user.password+"'";
		List<User> users = dao.retrieve(sql);

		if(users.size() > 0) {
			User u = users.get(0);
			user.id = u.id;
			user.name = u.name;
			user.phone = u.phone;
			user.email = u.email;
			user.address = u.address;
			user.department = u.department;
			user.type = u.type;
			user.lastUpdatedOn = u.lastUpdatedOn;
			user.status = u.status;
			return true;
		}
		
		return false;
	}
	
	public boolean registerUser(User user) {
		//int result = dao.insert(user);
		//return result > 0;
		return dao.insert(user) > 0;
	}
	
	public boolean updateUser(User user) {
		return dao.update(user) > 0;
	}

	public void ChangeUserStatus(int userID) {
		String sql = "select * from user where id="+userID;
		List<User> object = dao.retrieve(sql);
		if(object.size()!=0) {
			User obj = object.get(0);
			String status;
			if (obj.status == 1) {
				status = "Active";
			} else {
				status = "Inactive";
			}
			System.out.println("Current Status of User: " + status);
			System.out.println("Do you want to change the status?\nPress 1 to change and anything else to not.");
			int ch = Integer.parseInt(scanner.nextLine());
			if (ch == 1) {
				if (obj.status == 0) {
					obj.status = 1;
				} else {
					obj.status = 0;
				}

				dao.update(obj);
			} else {
				System.out.println("No change requested.");
			}
		}
		else{
			System.out.println("Not found");
		}

	}

}

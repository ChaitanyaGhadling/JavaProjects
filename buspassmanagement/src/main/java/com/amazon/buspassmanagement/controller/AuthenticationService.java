package com.amazon.buspassmanagement.controller;

import java.util.List;

import com.amazon.buspassmanagement.db.UserDAO;
import com.amazon.buspassmanagement.model.User;

public class AuthenticationService {
	
	private static AuthenticationService service = new AuthenticationService();
	UserDAO dao = new UserDAO();

	/*
	// Dummy DataBase in RAM
	LinkedHashMap<Integer, User> users = new LinkedHashMap<Integer, User>();
	
	private AuthenticationService(){
		
		User user1 = new User();
		user1.id = 1;
		user1.name = "John Watson";
		user1.email = "john@example.com";
		user1.password = "admin123";
		user1.type = 1;		// admin
		
		User user2 = new User();
		user2.id = 2;
		user2.name = "Fionna Flynn";
		user2.email = "fionna@example.com";
		user2.password = "fionna123";
		user2.type = 2;		// user or visitor
		
		// Dummy Users in the HashMap :)
		users.put(user1.id, user1);	// 1, user1
		users.put(user2.id, user2); // 2. user2
		
		System.out.println("DataBase Details:");
		System.out.println(users);
	}
	*/
	
	private AuthenticationService(){
		/*
		User user1 = new User();
		user1.id = 1;
		user1.name = "John Watson";
		user1.phone = "+91 99999 11111";
		user1.email = "john@example.com";
		user1.password = "admin123";
		user1.address = "Redwood Shores";
		user1.department = "admin";
		user1.type = 1;		// admin
		
		User user2 = new User();
		user2.id = 2;
		user2.name = "Fionna Flynn";
		user2.phone = "+91 99999 22222";
		user2.email = "fionna@example.com";
		user2.password = "fionna123";
		user2.address = "Country Homes";
		user2.department = "hr";
		user2.type = 2;		// user or visitor
		
		// Add 2 Users in DataBase
		UserDAO dao = new UserDAO();
		dao.insert(user1);
		dao.insert(user2);
		*/
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
	
	public boolean loginUser(User user, Integer type) {
				
		String sql = "SELECT * FROM User WHERE email = '"+user.email+"' AND password = '"+user.password+"' AND type = " +
				"'"+type+"'";
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
			user.createdOn = u.createdOn;
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
	
}

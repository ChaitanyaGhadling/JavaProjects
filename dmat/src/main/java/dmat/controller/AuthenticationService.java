package dmat.controller;

import dmat.db.UserDAO;
import dmat.model.User;


import java.util.List;

public class AuthenticationService {
	
	private static AuthenticationService service = new AuthenticationService();
	UserDAO dao = new UserDAO();

	
	public static AuthenticationService getInstance() {
		return service;
	}

	public boolean loginUser(User user) {
				
		String sql = "SELECT * FROM User WHERE email = '"+user.email+"' AND password = '"+user.password+"'";
		List<User> users = dao.retrieve(sql);

		
		if(users.size() > 0) {
			User u = users.get(0);
			user.account_no = u.account_no;
			user.name = u.name;
			user.balance = u.balance;
			user.email = u.email;
			return true;
		}
		
		return false; 
	}

	public boolean updateUser(User user) {
		return dao.update(user) > 0;
	}

	public boolean registerUser(User user) {
		return dao.insert(user) > 0;
	}
}

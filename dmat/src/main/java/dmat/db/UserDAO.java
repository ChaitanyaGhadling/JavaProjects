package dmat.db;

import dmat.model.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User>{

	DB db = DB.getInstance();
	
	@Override
	public int insert(User object) {
		String sql = "INSERT INTO User (account_no, name, email, password) VALUES ('"+object.account_no+"', '"+object.name+"', '"+object.email+"', '"+object.password+"')";
		return db.executeSQL(sql);
	}

	@Override
	public int update(User object) {
		String sql = "UPDATE User set balance = '"+object.balance+"' WHERE account_no = '"+object.account_no+"'";
		return db.executeSQL(sql);
	}

	@Override
	public int delete(User object) {
		String sql = "DELETE FROM User WHERE email = '"+object.email+"'";
		return db.executeSQL(sql);
	}

	@Override
	public List<User> retrieve() {
		
		String sql = "SELECT * from User";
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			while(set.next()) {
				
				User user = new User();
				
				// Read the row from ResultSet and put the data into User Object
				user.account_no = set.getInt("account_no");
				user.name = set.getString("name");
				user.email = set.getString("email");
				user.password = set.getString("password");
				user.balance = set.getInt("balance");
				users.add(user);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}
		return users;
	}
	
	@Override
	public List<User> retrieve(String sql) {
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			while(set.next()) {
				
				User user = new User();
				
				// Read the row from ResultSet and put the data into User Object
				user.account_no = set.getInt("account_no");
				user.name = set.getString("name");
				user.email = set.getString("email");
				user.password = set.getString("password");
				user.balance = set.getInt("balance");
				users.add(user);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return users;
	}
	
}

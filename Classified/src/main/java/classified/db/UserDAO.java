package classified.db;

import classified.model.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User>{

	DB db = DB.getInstance();
	
	@Override
	public int insert(User object) {
		String sql = "INSERT INTO User (name, phone, email, password, address, department, type,status) VALUES " +
				"('"+object.name+"', '"+object.phone+"', '"+object.email+"', '"+object.password+"', '"+object.address+"', " +
				"'"+object.department+"', '"+object.type+"',"+object.status+")";
		return db.executeSQL(sql);
	}

	@Override
	public int update(User object) {
		String sql = "UPDATE User set name = '"+object.name+"', phone='"+object.phone+"', password='"+object.password+"', address='"+object.address+"',status='"+object.status+"', department='"+object.department+"' WHERE id = '"+object.id+"'";
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
				user.id = set.getInt("id");
				user.name = set.getString("name");
				user.phone = set.getString("phone");
				user.email = set.getString("email");
				user.password = set.getString("password");
				user.address = set.getString("address");
				user.department = set.getString("department");
				user.type = set.getInt("type");
				user.status = set.getInt("status");
				user.lastUpdatedOn = set.getString("lastUpdatedOn");
				
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
				user.id = set.getInt("id");
				user.name = set.getString("name");
				user.phone = set.getString("phone");
				user.email = set.getString("email");
				user.password = set.getString("password");
				user.address = set.getString("address");
				user.department = set.getString("department");
				user.type = set.getInt("type");
				user.status = set.getInt("status");
				user.lastUpdatedOn = set.getString("lastUpdatedOn");
				
				users.add(user);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return users;
	}
	
}

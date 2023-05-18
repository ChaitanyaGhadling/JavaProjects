package com.amazon.buspassmanagement.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazon.buspassmanagement.model.Route;

public class RouteDAO implements DAO<Route> {

	DB db = DB.getInstance();
	
	@Override
	public int insert(Route object) {
		String sql = "INSERT INTO Route (title, description, adminId) VALUES ('"+object.title+"', '"+object.description+"', "+object.adminId+")";
		return db.executeSQL(sql);
	}

	@Override
	public int update(Route object) {
		String sql = "UPDATE Route set title = '"+object.title+"', description = '"+object.description+"' WHERE id = "+object.id;
		return db.executeSQL(sql);
	}

	@Override
	public int delete(Route object) {
		String sql = "DELETE from Route WHERE id = "+object.id;
		return db.executeSQL(sql);
	}

	@Override
	public List<Route> retrieve() {
		
		String sql = "SELECT * from Route";
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<Route> objects = new ArrayList<Route>();
		
		try {
			while(set.next()) {
				
				Route object = new Route();
				
				// Read the row from ResultSet and put the data into Object
				object.id = set.getInt("id");
				object.title = set.getString("title");
				object.description = set.getString("description");
				object.adminId = set.getInt("adminId");
				object.createdOn = set.getString("createdOn");
				
				objects.add(object);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return objects;
	}

	@Override
	public List<Route> retrieve(String sql) {
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<Route> objects = new ArrayList<Route>();
		
		try {
			while(set.next()) {
				
				Route object = new Route();
				
				// Read the row from ResultSet and put the data into Object
				object.id = set.getInt("id");
				object.title = set.getString("title");
				object.description = set.getString("description");
				object.adminId = set.getInt("adminId");
				object.createdOn = set.getString("createdOn");
				
				objects.add(object);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return objects;
		
	}

}

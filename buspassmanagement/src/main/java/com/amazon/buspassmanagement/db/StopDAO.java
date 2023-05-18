package com.amazon.buspassmanagement.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazon.buspassmanagement.model.Stop;

public class StopDAO implements DAO<Stop>{

	DB db = DB.getInstance();
	
	@Override
	public int insert(Stop object) {
		String sql = "INSERT INTO Stop (address, sequenceOrder, routeId, adminId) VALUES ('"+object.address+"', "+object.sequenceOrder+", "+object.routeId+", "+object.adminId+")";
		return db.executeSQL(sql);
	}

	@Override
	public int update(Stop object) {
		String sql = "UPDATE Stop set address = '"+object.address+"', sequenceOrder = "+object.sequenceOrder+", routeId = "+object.routeId+" WHERE id = "+object.id;
		return db.executeSQL(sql);
	}

	@Override
	public int delete(Stop object) {
		String sql = "DELETE from Stop WHERE id = "+object.id;
		return db.executeSQL(sql);
	}

	@Override
	public List<Stop> retrieve() {
		
		String sql = "SELECT * from Stop";
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<Stop> objects = new ArrayList<Stop>();
		
		try {
			while(set.next()) {
				
				Stop object = new Stop();
				
				// Read the row from ResultSet and put the data into Object
				object.id = set.getInt("id");
				object.address = set.getString("address");
				object.sequenceOrder = set.getInt("sequenceOrder");
				object.routeId = set.getInt("routeId");
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
	public List<Stop> retrieve(String sql) {
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<Stop> objects = new ArrayList<Stop>();
		
		try {
			while(set.next()) {
				
				Stop object = new Stop();
				
				// Read the row from ResultSet and put the data into Object
				object.id = set.getInt("id");
				object.address = set.getString("address");
				object.sequenceOrder = set.getInt("sequenceOrder");
				object.routeId = set.getInt("routeId");
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

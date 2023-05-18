package com.amazon.buspassmanagement.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazon.buspassmanagement.model.BusPass;

public class BusPassDAO implements DAO<BusPass> {

	DB db = DB.getInstance();
	
	@Override
	public int insert(BusPass object) {
			String sql = "INSERT INTO BusPass (uid, routeId, status) VALUES (" + object.uid + ", " + object.routeId + ", " + object.status + ")";
			return db.executeSQL(sql);
	}

	@Override
	public int update(BusPass object) {
		String sql = "UPDATE BusPass set approvedRejectedOn = '"+object.approvedRejectedOn
				+"', validTill = '"+object.validTill+"', status = "+object.status +" WHERE id = "+object.id;
		return db.executeSQL(sql);
	}

	@Override
	public int delete(BusPass object) {
		String sql = "DELETE FROM BusPass WHERE id = "+object.id;
		return db.executeSQL(sql);
	}

	@Override
	public List<BusPass> retrieve() {
		
		String sql = "SELECT * from BusPass";
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<BusPass> objects = new ArrayList<BusPass>();
		
		try {
			while(set.next()) {
				
				BusPass object = new BusPass();
				
				// Read the row from ResultSet and put the data into Object
				object.id = set.getInt("id");
				object.uid = set.getInt("uid");
				object.routeId = set.getInt("routeId");
				object.requestedOn = set.getString("requestedOn");
				object.approvedRejectedOn = set.getString("approvedRejectedOn");
				object.validTill = set.getString("validTill");
				object.status = set.getInt("status");
				
				objects.add(object);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return objects;
	}

	@Override
	public List<BusPass> retrieve(String sql) {
				
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<BusPass> objects = new ArrayList<BusPass>();
		
		try {
			while(set.next()) {
				
				BusPass object = new BusPass();
				
				// Read the row from ResultSet and put the data into Object
				object.id = set.getInt("id");
				object.uid = set.getInt("uid");
				object.routeId = set.getInt("routeId");
				object.requestedOn = set.getString("requestedOn");
				object.approvedRejectedOn = set.getString("approvedRejectedOn");
				object.validTill = set.getString("validTill");
				object.status = set.getInt("status");
				
				objects.add(object);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return objects;
	}

}

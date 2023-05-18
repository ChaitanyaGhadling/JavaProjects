package com.amazon.buspassmanagement.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazon.buspassmanagement.model.Vehicle;

public class VehicleDAO implements DAO<Vehicle> {
	
	DB db = DB.getInstance();

	@Override
	public int insert(Vehicle object) {
		String sql = "INSERT INTO Vehicle (registrationNumber, totalSeats, filledSeats, routeId, type, vehicleStatus, startPickUpTime, startDropOffTime, adminId, driverID) "
				+ "VALUES ('"+object.registrationNumber+"', "+object.totalSeats+", "+object.filledSeats+", "+object.routeId+", "+object.type+", "+object.vehicleStatus+", '"+object.startPickUpTime+"', '"+object.startDropOffTime+"', "+object.adminId+", "+object.driverID+")";
		return db.executeSQL(sql);
	}

	@Override
	public int update(Vehicle object) {
		String sql = "UPDATE Vehicle set registrationNumber = '"+object.registrationNumber+"', totalSeats = "+object.totalSeats+", filledSeats = "+object.filledSeats+", routeId = "+object.routeId+", type = "+object.type+", vehicleStatus = "+object.vehicleStatus+", startPickUpTime = '"+object.startPickUpTime+"', startDropOffTime = '"+object.startDropOffTime+"', driverID = "+object.driverID+" WHERE id = "+object.id;
		return db.executeSQL(sql);
	}

	@Override
	public int delete(Vehicle object) {
		String sql = "DELETE from Vehicle WHERE id = "+object.id;
		return db.executeSQL(sql);
	}

	@Override
	public List<Vehicle> retrieve() {
		
		String sql = "SELECT * from Vehicle";
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<Vehicle> objects = new ArrayList<Vehicle>();
		
		try {
			while(set.next()) {
				
				Vehicle object = new Vehicle();
				
				// Read the row from ResultSet and put the data into Object
				object.id = set.getInt("id");
				object.registrationNumber = set.getString("registrationNumber");
				object.totalSeats = set.getInt("totalSeats");
				object.filledSeats = set.getInt("filledSeats");
				object.routeId = set.getInt("routeId");
				object.type = set.getInt("type");
				object.vehicleStatus = set.getInt("vehicleStatus");
				object.startPickUpTime = set.getString("startPickUpTime");
				object.startDropOffTime = set.getString("startDropOffTime");
				object.adminId = set.getInt("adminId");
				object.driverID = set.getInt("driverID");
				object.createdOn = set.getString("createdOn");
				
				objects.add(object);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return objects;
	}

	@Override
	public List<Vehicle> retrieve(String sql) {
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<Vehicle> objects = new ArrayList<Vehicle>();
		
		try {
			while(set.next()) {
				
				Vehicle object = new Vehicle();
				
				// Read the row from ResultSet and put the data into Object
				object.id = set.getInt("id");
				object.registrationNumber = set.getString("registrationNumber");
				object.totalSeats = set.getInt("totalSeats");
				object.filledSeats = set.getInt("filledSeats");
				object.routeId = set.getInt("routeId");
				object.type = set.getInt("type");
				object.vehicleStatus = set.getInt("vehicleStatus");
				object.startPickUpTime = set.getString("startPickUpTime");
				object.startDropOffTime = set.getString("startDropOffTime");
				object.adminId = set.getInt("adminId");
				object.driverID = set.getInt("driverID");
				object.createdOn = set.getString("createdOn");
				
				objects.add(object);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return objects;
	}

}

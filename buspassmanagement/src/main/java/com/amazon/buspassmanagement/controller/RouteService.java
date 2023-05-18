package com.amazon.buspassmanagement.controller;

import java.util.List;
import java.util.Scanner;

import com.amazon.buspassmanagement.BusPassSession;
import com.amazon.buspassmanagement.db.RouteDAO;
import com.amazon.buspassmanagement.db.StopDAO;
import com.amazon.buspassmanagement.db.VehicleDAO;
import com.amazon.buspassmanagement.model.Route;
import com.amazon.buspassmanagement.model.Stop;
import com.amazon.buspassmanagement.model.Vehicle;

// To handle Route, Stop and Vehicles :)
public class RouteService {

	RouteDAO routeDAO = new RouteDAO();
	StopDAO stopDAO = new StopDAO();
	VehicleDAO vehicleDAO = new VehicleDAO();
	
	// Create it as a Singleton 
	private static RouteService routeService = new RouteService();
	Scanner scanner = new Scanner(System.in);
	
	public static RouteService getInstance() {
		return routeService;
	}
	
	private RouteService() {
	
	}
	
	// Handler for the Route :)
	public void addRoute() {
		Route route = new Route();
		route.getDetails(false);
		
		// Add the admin ID Implicitly.
		route.adminId = BusPassSession.user.id;
		
		int result = routeDAO.insert(route);
		String message = (result > 0) ? "Route Added Successfully" : "Adding Route Failed. Try Again.."; 
		System.out.println(message);
	}
	
	public void deleteRoute() {
		Route route = new Route();
		System.out.println("Enter Route ID to be deleted: ");
		route.id = scanner.nextInt();
		String sql = "select * from vehicle where routeID="+route.id;
		List<Vehicle> objects = vehicleDAO.retrieve(sql);
		for(Vehicle object : objects) {
			vehicleDAO.delete(object);
		}
		sql = "select * from stop where routeID="+route.id;
		List<Stop> stopObjects = stopDAO.retrieve(sql);
		for(Stop stopObject : stopObjects) {
			stopDAO.delete(stopObject);
		}
		int result = routeDAO.delete(route);
		String message = (result > 0) ? "Route Deleted Successfully" : "Deleting Route Failed. Try Again.."; 
		System.out.println(message);
	}
	
	public void updateRoute() {
		Route route = new Route();
		route.getDetails(true);
		
		// Add the admin ID Implicitly.
		route.adminId = BusPassSession.user.id;
		
		int result = routeDAO.update(route);
		String message = (result > 0) ? "Route Updated Successfully" : "Updating Route Failed. Try Again.."; 
		System.out.println(message);
	}
	
	public void viewRoutes() {
		
		List<Route> objects = routeDAO.retrieve();
		for(Route object : objects) {
			object.prettyPrint();
		}
		
		System.out.println("------------------------");
		
		System.out.println("Do you wish to view the details for any particular Route");
		System.out.println("If Yes. Enter the Route ID or 0 to cancel: ");
		int routeId = scanner.nextInt();
		
		System.out.println("------------------------");
		
		Route route = null;
		
		for(Route object : objects) {
			if (object.id == routeId) {
				route = object;
			}
			
		}
		
		if(routeId != 0 && route != null) {
			
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			System.out.println("Listing Details for the Route : "+route.title);
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			System.out.println();
			
			System.out.println("~~~~~~~~~~~~~~~");
			System.out.println("STOPS on Route");
			System.out.println("~~~~~~~~~~~~~~~");
			String sql = "SELECT * from Stop where routeId = "+routeId;
			
			List<Stop> filteredStops = stopDAO.retrieve(sql);
			
			for(Stop object : filteredStops) {
				object.prettyPrint();
			}
			
			System.out.println();
			
			System.out.println("~~~~~~~~~~~~~~~~~");
			System.out.println("Vehicles on Route");
			System.out.println("~~~~~~~~~~~~~~~~~");
			sql = "SELECT * from Vehicle where routeId = "+routeId;
			
			List<Vehicle> filteredVechicles = vehicleDAO.retrieve(sql);
			
			for(Vehicle object : filteredVechicles) {
				object.prettyPrint();
			}
		}
		
	}
	
	// Handler for the Stop :)
	public void addStop() {
		Stop stop = new Stop();
		stop.getDetails(false);
		
		// Add the admin ID Implicitly.
		stop.adminId = BusPassSession.user.id;
		
		int result = stopDAO.insert(stop);
		String message = (result > 0) ? "Stop Added Successfully" : "Adding Stop Failed. Try Again.."; 
		System.out.println(message);
	}
	
	public void deleteStop() {
		Stop stop = new Stop();
		System.out.println("Enter Stop ID to be deleted: ");
		stop.id = scanner.nextInt();
		int result = stopDAO.delete(stop);
		String message = (result > 0) ? "Stop Deleted Successfully" : "Deleting Stop Failed. Try Again.."; 
		System.out.println(message);
	}
	
	public void updateStop() {
		Stop stop = new Stop();
		stop.getDetails(true);
		
		// Add the admin ID Implicitly.
		stop.adminId = BusPassSession.user.id;
		
		int result = stopDAO.update(stop);
		String message = (result > 0) ? "Stop Updated Successfully" : "Updating Stop Failed. Try Again.."; 
		System.out.println(message);
	}
	
	public void viewStops() {
		
		System.out.println("Enter Route ID to get All the Stops on Route");
		System.out.println("Or 0 for All Stops");
		System.out.println("Enter Route ID: ");
		
		int routeId = scanner.nextInt();
		
		List<Stop> objects = null;
		
		if(routeId == 0) {
			objects = stopDAO.retrieve();
		}else {
			String sql = "SELECT * from Stop where routeId = "+routeId;
			objects = stopDAO.retrieve(sql);
		}
		
		for(Stop object : objects) {
			object.prettyPrint();
		}
	}
	

	// Handler for the Vehicle :)
	public void addVehicle() {
		Vehicle vehicle = new Vehicle();
		vehicle.getDetails(false);
		
		// Add the admin ID Implicitly.
		vehicle.adminId = BusPassSession.user.id;
		
		int result = vehicleDAO.insert(vehicle);
		String message = (result > 0) ? "Vehicle Added Successfully" : "Adding Vehicle Failed. Try Again.."; 
		System.out.println(message);
	}
	
	public void deleteVehicle() {
		Vehicle vehicle = new Vehicle();
		System.out.println("Enter Vehicle ID to be deleted: ");
		vehicle.id = scanner.nextInt();
		int result = vehicleDAO.delete(vehicle);
		String message = (result > 0) ? "Vehicle Deleted Successfully" : "Deleting Vehicle Failed. Try Again.."; 
		System.out.println(message);
	}
	
	public void updateVehicle() {
		Vehicle vehicle = new Vehicle();
		vehicle.getDetails(true);
		
		// Add the admin ID Implicitly.
		vehicle.adminId = BusPassSession.user.id;
		
		int result = vehicleDAO.update(vehicle);
		String message = (result > 0) ? "Vehicle Updated Successfully" : "Updating Vehicle Failed. Try Again.."; 
		System.out.println(message);
	}
	
	public void viewVehicles() {
		
		System.out.println("Enter Route ID to get All the Vehicles on Route");
		System.out.println("Or 0 for All Vehicles");
		System.out.println("Enter Route ID: ");
		
		int routeId = scanner.nextInt();
		
		List<Vehicle> objects = null;
		
		if(routeId == 0) {
			objects = vehicleDAO.retrieve();
		}else {
			String sql = "SELECT * from Vehicle where routeId = "+routeId;
			objects = vehicleDAO.retrieve(sql);
		}
		
		for(Vehicle object : objects) {
			object.prettyPrint();
		}
	}
	
}

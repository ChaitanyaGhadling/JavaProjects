package com.amazon.buspassmanagement.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.amazon.buspassmanagement.BusPassSession;
import com.amazon.buspassmanagement.db.BusPassDAO;
import com.amazon.buspassmanagement.model.BusPass;
import com.amazon.buspassmanagement.controller.CustomException;

public class BusPassService {

	BusPassDAO passDAO = new BusPassDAO();
	
	// Create it as a Singleton 
	private static BusPassService passService = new BusPassService();
	Scanner scanner = new Scanner(System.in);
	
	public static BusPassService getInstance() {
		return passService;
	}
	
	private BusPassService() {
	
	}
	
	// Handler for the Bus Pass :)
	public void requestPass() {
		BusPass pass = new BusPass();
		pass.getDetails(false);
		
		// Add the User ID Implicitly.
		pass.uid = BusPassSession.user.id;
		
		// As initially record will be inserted by User where it is a request
		pass.status = 1; // initial status as requested :)

		int result;
		try {
			String sql = "select * from buspass where uid="+pass.uid+" and routeID="+pass.routeId+" and status = 0";
			String sql2 = "select * from buspass where uid="+pass.uid+" and routeID="+pass.routeId+" and status=4";
			List<BusPass> obj1 = passDAO.retrieve(sql);
			List<BusPass> obj2 = passDAO.retrieve(sql2);
			if(obj1.size()!=0) {
				throw new CustomException("Pass already requested");
			}
			else if(obj2.size()!=0){
				throw new CustomException("Pass suspended for this route");
			}
			else {
				result = passDAO.insert(pass);
			}
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			result = -1;
		}

		String message = (result > 0) ? "Pass Requested Successfully" : "Request for Pass Failed. Try Again..";
		System.out.println(message);
	}
	
	public void deletePass() {
		BusPass pass = new BusPass();
		System.out.println("Enter Pass ID to be deleted: ");
		pass.id = scanner.nextInt();
		int result = passDAO.delete(pass);
		String message = (result > 0) ? "Pass Deleted Successfully" : "Deleting Pass Failed. Try Again.."; 
		System.out.println(message);
	}
	
	/*
	 
	 	Extra Task:
	 	IFF : You wish to UpSkill :)
	 
	 	Scenario: Open the same application in 2 different terminals
	 	1 logged in by user
	 	another logged in by admin
	 	
	 	If admin, approves or rejects the pass -> User should be notified :)
	 	
	 	Reference Link
	 	https://github.com/ishantk/AmazonAtlas22/blob/master/Session8/src/com/amazon/atlas/casestudy/YoutubeApp.java
	 
	 */
	
	public void approveRejectPassRequest() {
		
		BusPass pass = new BusPass();

		System.out.println("Enter Pass ID: ");
		pass.id = scanner.nextInt();
		
		System.out.println("2: Approve");
		System.out.println("3: Cancel");
		System.out.println("Enter Approval Choice: ");
		pass.status = scanner.nextInt();

    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar calendar = Calendar.getInstance();
		Date date1 = calendar.getTime();
		pass.approvedRejectedOn = dateFormat.format(date1);
		
		if(pass.status == 2) {
			calendar.add(Calendar.YEAR, 1);
			Date date2 = calendar.getTime();
			pass.validTill = dateFormat.format(date2);
			int result = passDAO.update(pass);
			String message = (result > 0) ? "Pass Request Updated Successfully" : "Updating Pass Request Failed. Try Again..";
			System.out.println(message);
		}
		else if(pass.status == 3) {
			pass.validTill = pass.approvedRejectedOn;
			int result = passDAO.update(pass);
			String message = (result > 0) ? "Pass Request Updated Successfully" : "Updating Pass Request Failed. Try Again..";
			System.out.println(message);
		}
		else{
			System.out.println("Enter correct choice.");
		}
		

	}
	
	public void viewPassRequests() {
		
		System.out.println("Enter Route ID to get All the Pass Reqeuests for a Route");
		System.out.println("Or 0 for All Bus Pass Requests");
		System.out.println("Enter Route ID: ");
		
		int routeId = scanner.nextInt();
		
		List<BusPass> objects = null;
		
		if(routeId == 0) {
			objects = passDAO.retrieve();
		}else {
			String sql = "SELECT * from BusPass where routeId = "+routeId;
			objects = passDAO.retrieve(sql);
		}
		
		for(BusPass object : objects) {
			object.prettyPrint();
		}
	}
	
	public void viewPassRequestsByUser(int uid) {
		
		String sql = "SELECT * from BusPass where uid = "+uid;
		List<BusPass> objects = passDAO.retrieve(sql);
		
		for(BusPass object : objects) {
			object.prettyPrint();
		}
	}

	public void requestPassSuspension(int id) {
		System.out.println("Your existing pass");
		viewPassRequestsByUser(id);
		System.out.println("Enter the Pass ID for bus pass you want to suspend:");
		int pid = scanner.nextInt();
		String query = "select * from buspass where id = "+ pid;
		List<BusPass> objects = passDAO.retrieve(query);
		BusPass obj = objects.get(0);
		obj.status = 4;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date1 = calendar.getTime();
		obj.validTill = dateFormat.format(date1);
		int result = passDAO.update(obj);
		String message = (result > 0) ? "Pass Request Updated Successfully" : "Updating Pass Request Failed. Try Again..";
		System.out.println(message);

	}

	public void viewExpiredPass() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = calendar.getTime();
		String date1 = dateFormat.format(date);
		String sql = "select * from buspass where validTill <'"+date1+"'";
		List<BusPass> objects = passDAO.retrieve(sql);
		if(objects.size()!=0) {
			for (BusPass object : objects) {
				object.prettyPrint();
			}
		}
		else{
			System.out.println("No record found.");
		}
	}

	public void viewPassBetweenDate() {
		System.out.println("Enter the Start Date(yyyy-MM-dd)");
		String start_date = scanner.nextLine();
		System.out.println("Enter the End Date(yyyy-MM-dd)");
		String end_date = scanner.nextLine();
		String query = "select * from buspass where approvedRejectedOn between'"+start_date+"' and '"+ end_date+"'";
		List<BusPass> objects = passDAO.retrieve(query);
		for(int i=0; i< objects.size();i++){
			BusPass obj = objects.get(i);
			obj.prettyPrint();
		}
	}
}

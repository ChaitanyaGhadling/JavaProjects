package com.amazon.buspassmanagement.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazon.buspassmanagement.model.Feedback;

public class FeedbackDAO implements DAO<Feedback>{

	DB db = DB.getInstance();

	@Override
	public int insert(Feedback object) {
		String sql = "INSERT INTO Feedback (userId, title, description, type, raisedBy) "
				+ "VALUES ( "+object.userId+", '"+object.title+"', '"+object.description+"', "+object.type+", '"+object.raisedBy+"')";
		return db.executeSQL(sql);
	}

	// Since it is not requried.
	// Feedbacks cannot be altered.
	@Override
	public int update(Feedback object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Feedback object) {
		String sql = "DELETE from Feedback WHERE id = "+object.id;
		return db.executeSQL(sql);
	}

	@Override
	public List<Feedback> retrieve() {
		
		String sql = "SELECT * from Feedback";
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<Feedback> objects = new ArrayList<Feedback>();
		
		try {
			while(set.next()) {
				
				Feedback object = new Feedback();
				
				// Read the row from ResultSet and put the data into Object
				object.id = set.getInt("id");
				object.userId = set.getInt("userId");
				object.title = set.getString("title");
				object.description = set.getString("description");
				object.type = set.getInt("type");
				object.raisedBy = set.getString("raisedBy");
				object.createdOn = set.getString("createdOn");
				
				objects.add(object);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return objects;
	}

	@Override
	public List<Feedback> retrieve(String sql) {
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<Feedback> objects = new ArrayList<Feedback>();
		
		try {
			while(set.next()) {
				
				Feedback object = new Feedback();
				
				// Read the row from ResultSet and put the data into Object
				object.id = set.getInt("id");
				object.userId = set.getInt("userId");
				object.title = set.getString("title");
				object.description = set.getString("description");
				object.type = set.getInt("type");
				object.raisedBy = set.getString("raisedBy");
				object.createdOn = set.getString("createdOn");
				
				objects.add(object);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return objects;
	}

}

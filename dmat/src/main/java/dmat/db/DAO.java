package dmat.db;

import dmat.model.User;

import java.util.List;

// Database Access Object
// CRUD operations on a Table :)
public interface DAO<T> {
	
	int insert(T object);
	int update(T object);
    int delete(T object);
	List<T> retrieve();
	
	// To Retrieve Custom Data by passing select * query...
	List<T> retrieve(String sql);
}

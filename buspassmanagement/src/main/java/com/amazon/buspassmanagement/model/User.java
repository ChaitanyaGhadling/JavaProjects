package com.amazon.buspassmanagement.model;


/*

SQL Query for Table

MySQL:
create table User(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(256),
	phone VARCHAR(256),
	email VARCHAR(256) NOT NULL UNIQUE,
	password VARCHAR(256),
	address VARCHAR(256),
	department VARCHAR(256),
	type INT NOT NULL,
	createdOn DATETIME DEFAULT CURRENT_TIMESTAMP
);

MSSQL:
create table User(
	id INT IDENTITY(1,1),
	name NVARCHAR(50) not null,
	email NVARCHAR(30) UNIQUE,
	password NVARCHAR(30),
	address NVARCHAR(100),
	department NVARCHAR(30),
	type INT,
	createdOn DATETIME DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(id);
);

*/

public class User {
	
	// Attributes
	public int id;
	public String name;
	public String phone;
	public String email;
	public String password;
	public String address;
	public String department;
	public int type;
	public String createdOn;
	
	public User() {
		
	}
	
	public User(int id, String name, String phone, String email, String password, String address, String department,
			int type, String createdOn) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.address = address;
		this.department = department;
		this.type = type;
		this.createdOn = createdOn;
	}
	
	public void prettyPrint() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Name:\t\t"+name);
		System.out.println("Phone:\t\t"+phone);
		System.out.println("Email:\t\t"+email);
		System.out.println("Address:\t"+address);
		System.out.println("Department:\t"+department);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", password=" + password
				+ ", address=" + address + ", department=" + department + ", type=" + type + ", createdOn=" + createdOn
				+ "]";
	}
	
}

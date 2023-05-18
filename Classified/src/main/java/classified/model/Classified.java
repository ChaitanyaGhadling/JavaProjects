package classified.model;

import java.util.Scanner;

/*

MySQL:
create table Classified(
	id INT PRIMARY KEY AUTO_INCREMENT,
	status INT,
	postedByID INT,
	category VARCHAR(45),
	type VARCHAR(45),
	headline VARCHAR(100),
	name VARCHAR(50),
	brand VARCHAR(25),
	conditions VARCHAR(45),
	description VARCHAR(500),
	price FLOAT,
	lastUpdatedOn DATETIME DEFAULT CURRENT_TIMESTAMP
);

*/

public class Classified {

	// Attributes
	public int id;
	public int status;
	public int postedByID;
	public String category;
	public String type;
	public String headline;
	public String name;
	public String brand;
	public String conditions;
	public String description;
	public float price;

	public String lastUpdatedOn;

	public Classified() {

	}

	public Classified(int id, int status, int postedByID, String category, String type, String headline, String name, String brand, String conditions,
					  String description, Float price, String lastUpdatedOn) {
		this.id = id;
		this.status = status;
		this.postedByID = postedByID;
		this.category = category;
		this.type = type;
		this.headline = headline;
		this.name = name;
		this.brand = brand;
		this.conditions = conditions;
		this.description = description;
		this.price = price;
		this.lastUpdatedOn = lastUpdatedOn;
	}
	public void prettyPrint() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Classified ID:\t\t"+id);
		System.out.println("Status:\t\t"+status);
		System.out.println("Posted By ID:\t\t"+postedByID);
		System.out.println("Product category:\t\t"+category);
		System.out.println("Product Type:\t\t"+type);
		System.out.println("Headline:\t\t"+headline);
		System.out.println("Product Name:\t\t"+name);
		System.out.println("Brand:\t\t"+brand);
		System.out.println("Condition:\t\t"+conditions);
		System.out.println("Description:\t\t"+description);
		System.out.println("Price:\t"+price);
		System.out.println("Last Updated On:\t"+lastUpdatedOn);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
	}

	@Override
	public String toString() {
		return "Stop [id=" + id + ", status=" + status + ",postedByID=" + postedByID + ",category=" + category + ", type=" + type + ", headline=" + headline
				+ ", name=" + name + ", brand=" + brand + ", conditions=" + conditions + ", price=" + price + ", " +
				"description=" + description + "]";
	}
	
}

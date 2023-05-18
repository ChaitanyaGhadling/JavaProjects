package classified.model;
import java.util.Scanner;

/*

MySQL:
create table Order(
	id INT PRIMARY KEY AUTO_INCREMENT,
	classified_id INT,
	from_user_id INT,
	to_user_id INT,
	price FLOAT,
	status INT,
	lastUpdatedOn DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (classified_id) REFERENCES Classified(id),
    FOREIGN KEY (from_user_id) REFERENCES User(id),
    FOREIGN KEY (to_user_id) REFERENCES User(id)
);

*/

public class Order {

    // Attributes
    public int id;
    public int classified_id;
    public int from_user_id;
    public int to_user_id;
    public float proposed_price;
    public int status;
    public String lastUpdatedOn;
    String s_status;

    public Order() {

    }

    public Order(int id, int classified_id, int from_user_id, int to_user_id, Float proposed_price,
                 int status, String lastUpdatedOn) {
        this.id = id;
        this.classified_id = classified_id;
        this.from_user_id = from_user_id;
        this.to_user_id = to_user_id;
        this.proposed_price = proposed_price;
        this.status = status;
        this.lastUpdatedOn = lastUpdatedOn;
    }
    public void prettyPrint() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Order ID:\t\t"+id);
        System.out.println("Classified ID:\t\t"+classified_id);
        System.out.println("Seller ID:\t\t"+from_user_id);
        System.out.println("Buyer ID:\t\t"+to_user_id);
        System.out.println("Proposed Price:\t\t"+proposed_price);
        if(status == 0){
            s_status = "Requested for purchase";
        }
        else if(status==1){
            s_status = "Approved. Agreed to sell";
        }
        else if(status==2){
            s_status = "Declined. Not interested to sell.";
        }
        else{
            s_status = "Payment Processed. Product Sold";
        }
        System.out.println("Status:\t\t"+s_status);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
    }

    /*@Override
    public String toString() {
        return "Stop [id=" + id + ", category=" + category + ", type=" + type + ", headline=" + headline
                + ", name=" + name + ", brand=" + brand + ", conditions=" + conditions + ", price=" + price + ", " +
                "description=" + description + "]";
    }*/

}

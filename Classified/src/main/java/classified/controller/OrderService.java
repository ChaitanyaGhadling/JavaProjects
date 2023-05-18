package classified.controller;

import classified.ClassifiedsSession;
import classified.db.ClassifiedDAO;
import classified.db.OrderDAO;
import classified.model.Classified;
import classified.model.Order;
import classified.model.User;

import java.util.List;
import java.util.Scanner;

public class OrderService {
    private static OrderService service = new OrderService();
    OrderDAO dao = new OrderDAO();
    ClassifiedDAO cldao = new ClassifiedDAO();
    User user = ClassifiedsSession.user;
    Scanner scanner = new Scanner(System.in);

    private OrderService() {

    }

    public static OrderService getInstance() {
        return service;
    }

    public void requestPurchaseClassified() {
        System.out.println("Enter the Classified Id you are interested to purchase.");
        int clID = Integer.parseInt(scanner.nextLine());
        String sql = "select * from classified where id=" + clID;
        List<Classified> object = cldao.retrieve(sql);
        if(object.size()!=0) {
            Classified obj = object.get(0);
            System.out.println("Existing price for the product: $" + obj.price);
            System.out.println("Enter the price you want to buy the product for:");
            Float price = Float.parseFloat(scanner.nextLine());
            Order order = new Order();
            order.classified_id = clID;
            order.from_user_id = obj.postedByID;
            order.to_user_id = ClassifiedsSession.user.id;
            order.proposed_price = price;
            order.status = 0;
            int result = dao.insert(order);
            String message = (result > 0) ? "Order Placed Successfully" : "Failed. Try Again..";
            System.out.println(message);
        }
        else{
            System.out.println("Not found.");
        }

    }

    public void makePayment() {
        String sql = "select * from classifiedsdb.order where to_user_id=" + ClassifiedsSession.user.id+ " and status=1";
        List<Order> objects = dao.retrieve(sql);
        if(objects.size()!=0) {
            for (Order object : objects) {
                object.prettyPrint();
                System.out.println("Enter the Order ID you want to confirm Payment for.");
                int id = Integer.parseInt(scanner.nextLine());
                sql = "select * from classifiedsdb.order where id=" + id;
                List<Order> order = dao.retrieve(sql);
                Classified clObj = new Classified();
                if (order.size() != 0) {
                    Order obj = order.get(0);
                    sql = "select * from classified where id="+obj.classified_id;
                    List<Classified> clOrder = cldao.retrieve(sql);
                    if (clOrder.size() != 0) {
                        clObj = clOrder.get(0);
                    }
                    else{
                        System.out.println("Not found");
                    }
                    System.out.println("Press 1 if you want to confirm the Payment or anything else to cancel order .");
                    int ch = Integer.parseInt(scanner.nextLine());
                    if (ch == 1) {
                        obj.status = 3;
                        cldao.delete(clObj);
                    } else {
                        obj.status = 2;
                    }
                    int result = dao.update(obj);
                    String message = (result > 0) ? "Order Updated Successfully" : "Failed. Try Again..";
                    System.out.println(message);
                } else {
                    System.out.println("Not found");
                }
            }
        }
        else{
            System.out.println("Not found");
        }
    }

    public void confirmSale() {
        String sql = "select * from classifiedsdb.order where from_user_id=" + ClassifiedsSession.user.id + " and status=0";
        List<Order> objects = dao.retrieve(sql);
        if(objects.size()!=0) {
            for (Order object : objects) {
                object.prettyPrint();
                System.out.println("Enter the Order ID you want to confirm Sale.");
                int id = Integer.parseInt(scanner.nextLine());
                sql = "select * from classifiedsdb.order where id=" + id;
                List<Order> order = dao.retrieve(sql);
                if (order.size() != 0) {
                    Order obj = order.get(0);
                    System.out.println("Press 1 if you want to Accept Sale and anything else to Decline.");
                    int ch = Integer.parseInt(scanner.nextLine());
                    if (ch == 1) {
                        obj.status = 1;
                    } else {
                        obj.status = 2;
                    }
                    int result = dao.update(obj);
                    String message = (result > 0) ? "Order Updated Successfully" : "Failed. Try Again..";
                    System.out.println(message);

                } else {
                    System.out.println("Not found");
                }
            }
        }
        else{
            System.out.println("Not found");
        }
    }

    public void viewOrders() {
        String sql = "select * from classifiedsdb.order where from_user_id=" + ClassifiedsSession.user.id+" or to_user_id="+ClassifiedsSession.user.id;
        List<Order> objects = dao.retrieve(sql);
        if(objects.size()!=0) {
            for (Order object : objects) {
                object.prettyPrint();
            }
        }
        else{
            System.out.println("No Orders found.");
        }
    }
}

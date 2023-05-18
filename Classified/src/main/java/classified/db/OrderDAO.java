package classified.db;
import classified.model.Order;
import classified.model.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements DAO<Order>{

    DB db = DB.getInstance();

    @Override
    public int insert(Order object) {
        String sql = "INSERT INTO classifiedsdb.order (classified_id, from_user_id, to_user_id, proposed_price, status) VALUES " +
                "("+object.classified_id+", "+object.from_user_id+", "+object.to_user_id+", "+object.proposed_price+", " +
                ""+object.status+")";
        return db.executeSQL(sql);
    }

    @Override
    public int update(Order object) {
        String sql = "UPDATE classifiedsdb.Order set classified_id = '"+object.classified_id+"', from_user_id='"+object.from_user_id+"'," +
                " to_user_id='"+object.to_user_id+"', proposed_price='"+object.proposed_price+"',status='"+object.status+"'" +
                " WHERE id = '"+object.id+"'";
        return db.executeSQL(sql);
    }

    @Override
    public int delete(Order object) {
        String sql = "DELETE FROM Order WHERE id = '"+object.id+"'";
        return db.executeSQL(sql);
    }

    @Override
    public List<Order> retrieve() {

        String sql = "SELECT * from classifiedsdb.Order";

        ResultSet set = db.executeQuery(sql);

        ArrayList<Order> orders = new ArrayList<Order>();

        try {
            while(set.next()) {

                Order order = new Order();

                // Read the row from ResultSet and put the data into User Object
                order.id = set.getInt("id");
                order.classified_id = set.getInt("classified_id");
                order.from_user_id = set.getInt("from_user_id");
                order.to_user_id = set.getInt("to_user_id");
                order.proposed_price = set.getInt("proposed_price");
                order.status = set.getInt("status");
                order.lastUpdatedOn = set.getString("lastUpdatedOn");
                orders.add(order);
            }
        } catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }


        return orders;
    }

    @Override
    public List<Order> retrieve(String sql) {

        ResultSet set = db.executeQuery(sql);

        ArrayList<Order> orders = new ArrayList<Order>();

        try {
            while (set.next()) {

                Order order = new Order();

                // Read the row from ResultSet and put the data into User Object
                order.id = set.getInt("id");
                order.classified_id = set.getInt("classified_id");
                order.from_user_id = set.getInt("from_user_id");
                order.to_user_id = set.getInt("to_user_id");
                order.proposed_price = set.getInt("proposed_price");
                order.status = set.getInt("status");
                order.lastUpdatedOn = set.getString("lastUpdatedOn");
                orders.add(order);
            }
        } catch (Exception e) {
            System.err.println("Something Went Wrong: " + e);
        }


        return orders;
    }

}

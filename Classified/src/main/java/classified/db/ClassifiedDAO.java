package classified.db;

import classified.ClassifiedsSession;
import classified.model.Classified;
import classified.model.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassifiedDAO implements DAO<Classified>{

    DB db = DB.getInstance();


    @Override
    public int insert(Classified object) {
        String sql = "INSERT INTO Classified (status, postedByID, category, type, headline, name, brand, conditions, description, price) " +
                "VALUES ('"+object.status+"','"+ClassifiedsSession.user.id+"','"+object.category+"', '"+object.type+"', '"+object.headline+"', '"+object.name+"', " +
                "'"+object.brand+"', '"+object.conditions+"','"+object.description+"', "+object.price+")";
        return db.executeSQL(sql);
    }

    @Override
    public int update(Classified object) {
        String sql = "UPDATE Classified set status = '"+object.status+"',category = '"+object.category+"', type='"+object.type+"', " +
                "price='"+object.price+"' WHERE id = '"+object.id+"'";
        return db.executeSQL(sql);
    }

    @Override
    public int delete(Classified object) {
        String sql = "DELETE FROM Classified WHERE id = '"+object.id+"'";
        return db.executeSQL(sql);
    }

    @Override
    public List<Classified> retrieve() {

        String sql = "SELECT * from Classified where status=0";

        ResultSet set = db.executeQuery(sql);

        ArrayList<Classified> users = new ArrayList<Classified>();

        try {
            while(set.next()) {

                Classified classified = new Classified();

                // Read the row from ResultSet and put the data into User Object
                classified.id = set.getInt("id");
                classified.status = set.getInt("status");
                classified.postedByID = set.getInt("postedByID");
                classified.category = set.getString("category");
                classified.type = set.getString("type");
                classified.headline = set.getString("headline");
                classified.name = set.getString("name");
                classified.brand = set.getString("brand");
                classified.conditions = set.getString("conditions");
                classified.description = set.getString("description");
                classified.price = set.getFloat("price");
                classified.lastUpdatedOn = set.getString("lastUpdatedOn");

                users.add(classified);
            }
        } catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }


        return users;
    }

    @Override
    public List<Classified> retrieve(String sql) {

        ResultSet set = db.executeQuery(sql);

        ArrayList<Classified> users = new ArrayList<Classified>();

        try {
            while(set.next()) {

                Classified classified = new Classified();

                // Read the row from ResultSet and put the data into User Object
                classified.id = set.getInt("id");
                classified.status = set.getInt("status");
                classified.postedByID = set.getInt("postedByID");
                classified.category = set.getString("category");
                classified.type = set.getString("type");
                classified.headline = set.getString("headline");
                classified.name = set.getString("name");
                classified.brand = set.getString("brand");
                classified.conditions = set.getString("conditions");
                classified.description = set.getString("description");
                classified.price = set.getFloat("price");
                classified.lastUpdatedOn = set.getString("lastUpdatedOn");

                users.add(classified);
            }
        } catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }
        return users;
    }

}


package dmat.db;

import dmat.model.UserShares;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserSharesDAO implements DAO<UserShares>{

    DB db = DB.getInstance();

    @Override
    public int insert(UserShares object) {
        String sql = "INSERT INTO user_shares (account_no, company_id, current_total_price, total_shares) VALUES " +
                "('"+object.account_no+"', '"+object.company_id+"', '"+object.current_total_price+"', '"+object.total_shares+"')";
        return db.executeSQL(sql);
    }

    @Override
    public int update(UserShares object) {
        String sql = "UPDATE user_shares set current_total_price = '"+object.current_total_price+"', " +
                "total_shares='"+object.total_shares+"' WHERE account_no = "+object.account_no+" AND company_id" +
                " = "+object.company_id;
        return db.executeSQL(sql);
    }

    @Override
    public int delete(UserShares object) {
        String sql = "DELETE FROM user_shares WHERE account_no = '"+object.account_no+"AND company_id" +
                    "= '"+object.company_id+"'";
        return db.executeSQL(sql);
    }

    @Override
    public List<UserShares> retrieve() {

        String sql = "SELECT * from user_shares";

        ResultSet set = db.executeQuery(sql);

        ArrayList<UserShares> userShares = new ArrayList<UserShares>();

        try {
            while(set.next()) {

                UserShares share = new UserShares();

                // Read the row from ResultSet and put the data into User Object
                share.account_no = set.getInt("account_no");
                share.company_id = set.getInt("name");
                share.current_total_price = set.getFloat("email");
                share.total_shares = set.getFloat("password");
                userShares.add(share);
            }
        } catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }
        return userShares;
    }

    @Override
    public List<UserShares> retrieve(String sql) {

        ResultSet set = db.executeQuery(sql);

        ArrayList<UserShares> userShares = new ArrayList<UserShares>();

        try {
            while(set.next()) {

                UserShares share = new UserShares();

                // Read the row from ResultSet and put the data into User Object
                share.account_no = set.getInt("account_no");
                share.company_id = set.getInt("company_id");
                share.current_total_price = set.getFloat("current_total_price");
                share.total_shares = set.getFloat("total_shares");
                userShares.add(share);
            }
        } catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }


        return userShares;
    }

}

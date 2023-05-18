package dmat.db;

import dmat.model.Stock;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StockDAO implements DAO<Stock>{

    DB db = DB.getInstance();

    @Override
    public int insert(Stock object) {
        String sql = "INSERT INTO Stock (company_name, current_price_per_share, total_shares) VALUES " +
                "('"+object.company_name+"', '"+object.current_price_per_share+"', '"+object.total_shares+"')";
        return db.executeSQL(sql);
    }

    @Override
    public int update(Stock object) {
        String sql = "UPDATE Stock set current_price_per_share = '"+object.current_price_per_share+"', " +
                "lastUpdated='"+object.lastUpdated+"' WHERE company_id = '"+object.company_id+"'";
        return db.executeSQL(sql);
    }

    @Override
    public int delete(Stock object) {
        String sql = "DELETE FROM Stock WHERE company_id = '"+object.company_id+"'";
        return db.executeSQL(sql);
    }

    @Override
    public List<Stock> retrieve() {

        String sql = "SELECT * from Stock";

        ResultSet set = db.executeQuery(sql);

        ArrayList<Stock> stocks = new ArrayList<Stock>();

        try {
            while(set.next()) {

                Stock stock = new Stock();

                // Read the row from ResultSet and put the data into User Object
                stock.company_id = set.getInt("company_id");
                stock.company_name = set.getString("company_name");
                stock.current_price_per_share = set.getFloat("current_price_per_share");
                stock.lastUpdated = set.getString("lastUpdated");
                stock.total_shares = set.getInt("total_shares");
                stocks.add(stock);
            }
        } catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }
        return stocks;
    }

    @Override
    public List<Stock> retrieve(String sql) {

        ResultSet set = db.executeQuery(sql);

        ArrayList<Stock> stocks = new ArrayList<Stock>();

        try {
            while(set.next()) {

                Stock stock = new Stock();

                // Read the row from ResultSet and put the data into User Object
                stock.company_id = set.getInt("company_id");
                stock.company_name = set.getString("company_name");
                stock.current_price_per_share = set.getFloat("current_price_per_share");
                stock.lastUpdated = set.getString("lastUpdated");
                stock.total_shares = set.getInt("total_shares");
                stocks.add(stock);
            }
        } catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }


        return stocks;
    }

}

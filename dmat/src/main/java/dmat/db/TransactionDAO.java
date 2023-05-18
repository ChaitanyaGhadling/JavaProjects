package dmat.db;

import dmat.model.Stock;
import dmat.model.Transaction;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO implements DAO<Transaction>{

    DB db = DB.getInstance();

    @Override
    public int insert(Transaction object) {
        String sql = "INSERT INTO transaction (account_no, company_id, shares_bought_sold, transaction_price, " +
                "transaction_fee, transaction_type) VALUES ('"+object.account_no+"', '"+object.company_id+"'," +
                "'"+object.shares_bought_sold+"'," + "'"+object.transaction_price+"', '"+object.transaction_fee+"', " +
                "'"+object.transaction_type+"')";
        return db.executeSQL(sql);
    }
    @Override
    public int update(Transaction object) {
        System.out.println("No need");
        return 0;
    }


    @Override
    public int delete(Transaction object) {
        System.out.println("No need");
        return 0;
    }

    @Override
    public List<Transaction> retrieve() {

        String sql = "SELECT * from transaction";

        ResultSet set = db.executeQuery(sql);

        ArrayList<Transaction> transactions = new ArrayList<Transaction>();

        try {
            while(set.next()) {

                Transaction transaction = new Transaction();

                // Read the row from ResultSet and put the data into User Object
                transaction.transaction_id = set.getInt("transaction_id");
                transaction.account_no = set.getInt("account_no");
                transaction.company_id = set.getInt("company_id");
                transaction.shares_bought_sold = set.getFloat("shares_bought_sold");
                transaction.transaction_price = set.getFloat("transaction_price");
                transaction.transaction_date = set.getString("transaction_date");
                transaction.transaction_fee = set.getFloat("transaction_fee");
                transaction.transaction_type = set.getInt("transaction_type");
                transactions.add(transaction);
            }
        } catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }
        return transactions;
    }

    @Override
    public List<Transaction> retrieve(String sql) {

        ResultSet set = db.executeQuery(sql);

        ArrayList<Transaction> transactions = new ArrayList<Transaction>();

        try {
            while(set.next()) {

                Transaction transaction = new Transaction();

                // Read the row from ResultSet and put the data into User Object
                transaction.transaction_id = set.getInt("transaction_id");
                transaction.account_no = set.getInt("account_no");
                transaction.company_id = set.getInt("company_id");
                transaction.shares_bought_sold = set.getFloat("shares_bought_sold");
                transaction.transaction_price = set.getFloat("transaction_price");
                transaction.transaction_date = set.getString("transaction_date");
                transaction.transaction_fee = set.getFloat("transaction_fee");
                transaction.transaction_type = set.getInt("transaction_type");
                transactions.add(transaction);
            }
        } catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }


        return transactions;
    }

}

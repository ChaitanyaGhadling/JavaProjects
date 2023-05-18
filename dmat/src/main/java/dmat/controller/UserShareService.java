package dmat.controller;

import dmat.DMATSession;
import dmat.db.UserDAO;
import dmat.db.UserSharesDAO;
import dmat.model.Transaction;
import dmat.model.User;
import dmat.model.UserShares;

import java.util.List;
import java.util.Scanner;
public class UserShareService {
    private static UserShareService service = new UserShareService();
    UserSharesDAO ussharedao = new UserSharesDAO();
    UserDAO userdao = new UserDAO();
    Scanner scanner = new Scanner(System.in);

    public static UserShareService getInstance() {
        return service;
    }

    public void addShare(Transaction tb) {
        UserShares usshare = new UserShares();
        String sql = "select * from User where account_no ="+tb.account_no;
        List<User> users = userdao.retrieve(sql);
        User user = users.get(0);
        int result = 0;
        usshare.account_no = tb.account_no;
        user.account_no = tb.account_no;
        usshare.company_id = tb.company_id;
        String query = "select * from user_shares where account_no ="+usshare.account_no+" and company_id ="+
                usshare.company_id;
        List<UserShares> shares = ussharedao.retrieve(query);
        if(!shares.isEmpty()){
            UserShares obj = shares.get(0);
            System.out.println("Not Null");
            usshare.total_shares =obj.total_shares + tb.shares_bought_sold;
            usshare.current_total_price =obj.current_total_price + tb.transaction_price;
            result = ussharedao.update(usshare);
        }
        else{
            System.out.println("Null");
            usshare.total_shares =tb.shares_bought_sold;
            usshare.current_total_price = tb.transaction_price;
            result = ussharedao.insert(usshare);
        }
        user.balance= (int) (user.balance-tb.transaction_price-tb.transaction_fee);
        int result2 = userdao.update(user);
        String message = (result > 0) ? "UserShare Updated Successfully" : "Transaction Failed. Try Again..";
        System.out.println(message);
        message = (result2 > 0) ? "Balance Updated Successfully" : "Transaction Failed. Try Again..";
        System.out.println(message);


    }

    public void removeShare(Transaction ts) {
        UserShares usshare = new UserShares();
        String sql = "select * from User where account_no ="+ts.account_no;
        List<User> users = userdao.retrieve(sql);
        User user = users.get(0);
        usshare.account_no = ts.account_no;
        usshare.company_id = ts.company_id;
        String query = "select * from user_shares where account_no ="+usshare.account_no+" and company_id ="+
                usshare.company_id;
        List<UserShares> shares = ussharedao.retrieve(query);
        UserShares obj = shares.get(0);
        usshare.total_shares =obj.total_shares - ts.shares_bought_sold;
        usshare.current_total_price =obj.current_total_price - ts.transaction_price;
        int result = ussharedao.update(usshare);
        if (usshare.total_shares == 0){
            ussharedao.delete(usshare);
        }
        user.balance+= ts.transaction_price-ts.transaction_fee;
        int result2 = userdao.update(user);
        String message = (result > 0) ? "UserShare Updated Successfully" : "Transaction Failed. Try Again..";
        System.out.println(message);
        message = (result2 > 0) ? "Balance Updated Successfully" : "Transaction Failed. Try Again..";
        System.out.println(message);

    }

    public void viewAccount() {
        User user = new User();
        user.account_no = DMATSession.user.account_no;
        String query = "select * from user_shares where account_no = "+user.account_no;
        String query2 = "select * from user where account_no = "+user.account_no;
        List<User> usrs = userdao.retrieve(query2);
        User usr = usrs.get(0);
        System.out.println("UserName:"+usr.name);
        System.out.println("Account Number:"+usr.account_no);
        System.out.println("Balance:"+usr.balance);
        List<UserShares> objects = ussharedao.retrieve(query);
        for(int i=0; i< objects.size();i++){
            UserShares obj = objects.get(i);
            obj.prettyPrint();
        }
    }
}

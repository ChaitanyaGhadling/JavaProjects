package dmat.controller;

import dmat.db.StockDAO;
import dmat.db.TransactionDAO;
import dmat.db.UserDAO;
import dmat.db.UserSharesDAO;
import dmat.model.Stock;
import dmat.model.Transaction;
import dmat.model.User;
import dmat.model.UserShares;


import java.util.List;
import java.util.Scanner;


public class TransactionService {
    private static TransactionService service = new TransactionService();
    TransactionDAO transdao = new TransactionDAO();
    StockDAO stockdao = new StockDAO();
    UserDAO dao = new UserDAO();

    UserSharesDAO usrsharedao = new UserSharesDAO();
    Scanner scanner = new Scanner(System.in);

    public static TransactionService getInstance() {
        return service;
    }

    public Transaction buyStock(User user) {
        String sql = "select * from User where account_no ="+user.account_no;
        List<User> users = dao.retrieve(sql);
        user = users.get(0);
        Transaction transobj = new Transaction();
        System.out.println("Balance Amount, " + user.balance);
        System.out.println("Enter the company's ID you want to purchase stock of: ");
        transobj.company_id = Integer.parseInt(scanner.nextLine());
        String query = "select * from Stock where company_id ="+transobj.company_id;
        List<Stock> objects = stockdao.retrieve(query);
        Stock obj = objects.get(0);
        System.out.println("Existing Details:");
        obj.prettyPrint();
        System.out.println("1: Buy Stock with Share.");
        System.out.println("2: Buy Stock with Price.");
        System.out.println("Enter Your Choice");
        int ch = Integer.parseInt(scanner.nextLine());
        transobj.transaction_type = 1;
        transobj.account_no = user.account_no;
        if(ch==1){
            System.out.println("Enter the share amount you want to buy.");
            float amount = Float.parseFloat(scanner.nextLine());
            transobj.shares_bought_sold = amount;
            float transaction_charge = (float) (amount*obj.current_price_per_share*0.005);
            if(transaction_charge<100){
                transaction_charge = 100;
            }
            float stt = (float) (amount*0.001*obj.current_price_per_share);
            transobj.transaction_price = amount*obj.current_price_per_share;
            transobj.transaction_fee = transaction_charge+stt;
            if(transobj.transaction_price+transaction_charge+stt>user.balance){
                System.out.println("Cannot buy stock!!! Insufficient Funds.");
            }
            else{
                System.out.println("Shares to be bought: "+amount);
                System.out.println("Price for shares: "+transobj.transaction_price);
                System.out.println("Transaction Charge: "+transaction_charge);
                System.out.println("Securities Transfer Tax: "+stt);
                System.out.println("Do you want to confirm with the purchase?\n Enter 1 to confirm anything else to revert.");
                Integer conf = Integer.parseInt(scanner.nextLine());
                if(conf==1){
                    int result = transdao.insert(transobj);
                    String message = (result > 0) ? "Stock Bought Successfully" : "Purchase Failed. Try Again..";
                    System.out.println(message);
                }
                else{
                    System.out.println("Purchase Cancelled.");
                    transobj.transaction_price =0;
                }
            }
        }
        else if(ch ==2){
            System.out.println("Enter the price you want to buy.");
            float price = Float.parseFloat(scanner.nextLine());
            transobj.transaction_price = price;
            transobj.shares_bought_sold = price/obj.current_price_per_share;
            float transaction_charge = (float) (price*0.005);
            if(transaction_charge<100){
                transaction_charge = 100;
            }
            float stt = (float) (0.001*price);
            transobj.transaction_fee = transaction_charge+stt;
            if(transobj.transaction_price+transaction_charge+stt>user.balance){
                System.out.println("Cannot buy stock!!! Insufficient Funds.");
            }
            else{
                System.out.println("Shares to be bought: "+transobj.shares_bought_sold);
                System.out.println("Price for shares: "+transobj.transaction_price);
                System.out.println("Transaction Charge: "+transaction_charge);
                System.out.println("Securities Transfer Tax: "+stt);
                System.out.println("Do you want to confirm with the purchase?\n Enter 1 to confirm anything else to revert.");
                Integer conf = Integer.parseInt(scanner.nextLine());
                if(conf==1){
                    int result = transdao.insert(transobj);
                    String message = (result > 0) ? "Stock Bought Successfully" : "Purchase Failed. Try Again..";
                    System.out.println(message);
                }
                else{
                    System.out.println("Purchase Cancelled.");
                    transobj.transaction_price =0;
                }
            }
        }
        else{
            System.out.println("Invalid Choice.");
        }
        return transobj;
    }
    public Transaction sellStock(User user) {
        Transaction transobj = new Transaction();
        System.out.println("Enter the company's ID you want to sell stock of: ");
        transobj.company_id = Integer.parseInt(scanner.nextLine());
        String query = "select * from Stock where company_id ="+transobj.company_id;
        List<Stock> objects = stockdao.retrieve(query);
        Stock obj = objects.get(0);
        query = "select * from User_shares where company_id ="+transobj.company_id+" and account_no ="+user.account_no;
        List<UserShares> usobjects = usrsharedao.retrieve(query);
        UserShares usobj = usobjects.get(0);
        System.out.println("Existing Details:");
        obj.prettyPrint();
        System.out.println("1: Sell Stock with Share.");
        System.out.println("2: Sell Stock with Price.");
        System.out.println("Enter Your Choice");
        int ch = Integer.parseInt(scanner.nextLine());
        transobj.transaction_type = 2;
        transobj.account_no = user.account_no;
        if(ch==1){
            System.out.println("Enter the share amount you want to sell.");
            float amount = Float.parseFloat(scanner.nextLine());
            transobj.shares_bought_sold = amount;
            float transaction_charge = (float) (amount*obj.current_price_per_share*0.005);
            if(transaction_charge<100){
                transaction_charge = 100;
            }
            float stt = (float) (amount*0.001*obj.current_price_per_share);
            transobj.transaction_price = amount*obj.current_price_per_share;
            transobj.transaction_fee = transaction_charge+stt;
            if(amount>usobj.total_shares){
                System.out.println("Cannot sell stock!!! Insufficient Shares.");
            }
            else{
                System.out.println("Shares to be sold: "+amount);
                System.out.println("Price for shares: "+transobj.transaction_price);
                System.out.println("Transaction Charge: "+transaction_charge);
                System.out.println("Securities Transfer Tax: "+stt);
                System.out.println("Do you want to confirm with the purchase?\n Enter 1 to confirm anything else to revert.");
                Integer conf = Integer.parseInt(scanner.nextLine());
                if(conf==1){
                    int result = transdao.insert(transobj);
                    String message = (result > 0) ? "Stock Sold Successfully" : "Sale Failed. Try Again..";
                    System.out.println(message);
                }
                else{
                    System.out.println("Sale Cancelled.");
                    transobj.transaction_price =0;
                }
            }
        }
        else if(ch ==2){
            System.out.println("Enter the price you want to sell.");
            float price = Float.parseFloat(scanner.nextLine());
            transobj.transaction_price = price;
            transobj.shares_bought_sold = price/obj.current_price_per_share;
            float transaction_charge = (float) (price*0.005);
            if(transaction_charge<100){
                transaction_charge = 100;
            }
            float stt = (float) (0.001*price);
            transobj.transaction_fee = transaction_charge+stt;
            if(price>usobj.total_shares*usobj.current_total_price){
                System.out.println("Cannot sell stock!!! Insufficient Shares.");
            }
            else{
                System.out.println("Shares to be sold: "+transobj.shares_bought_sold);
                System.out.println("Price for shares: "+transobj.transaction_price);
                System.out.println("Transaction Charge: "+transaction_charge);
                System.out.println("Securities Transfer Tax: "+stt);
                System.out.println("Do you want to confirm with the purchase?\n Enter 1 to confirm anything else to revert.");
                Integer conf = Integer.parseInt(scanner.nextLine());
                if(conf==1){
                    int result = transdao.insert(transobj);
                    String message = (result > 0) ? "Stock Sold Successfully" : "Sale Failed. Try Again..";
                    System.out.println(message);
                }
                else{
                    System.out.println("Sale Cancelled.");
                    transobj.transaction_price =0;
                }
            }
        }
        else{
            System.out.println("Invalid Choice.");
        }
        return transobj;
    }
    public void viewTransaction(User user) {
        System.out.println("Enter the Start Date(yyyy-MM-dd)");
        String start_date = scanner.nextLine();
        System.out.println("Enter the End Date(yyyy-MM-dd)");
        String end_date = scanner.nextLine();
        String query = "select * from transaction where account_no="+user.account_no+" and transaction_date between '"+
                start_date+"' and '"+ end_date+"'";
        List<Transaction> objects = transdao.retrieve(query);
        for(int i=0; i< objects.size();i++){
            Transaction obj = objects.get(i);
            obj.prettyPrint();
        }
    }
}

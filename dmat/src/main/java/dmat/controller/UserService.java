package dmat.controller;

import dmat.DMATSession;
import dmat.db.UserDAO;
import dmat.model.Transaction;
import dmat.model.User;

import java.util.List;
import java.util.Scanner;

public class UserService {
    private static UserService service = new UserService();
    UserDAO dao = new UserDAO();
    Scanner scanner = new Scanner(System.in);

    public static UserService getInstance() {
        return service;
    }

    public void addMoney(){
        User user = new User();
        user.account_no = DMATSession.user.account_no;
        String query = "select * from user where account_no = "+ user.account_no;
        List<User> objects = dao.retrieve(query);
        User obj = objects.get(0);
        System.out.println("Current Balance Available: "+obj.balance+"$");
        System.out.println("Enter the amount you want to add:");
        int amount = Integer.parseInt(scanner.nextLine());
        obj.balance+=amount;
        int result = dao.update(obj);
        String message = (result > 0) ? "Amount Added Successfully" : "Transaction Failed. Try Again..";
        System.out.println(message);
    }

    public void removeMoney() {
        User user = new User();
        user.account_no = DMATSession.user.account_no;
        int result = 0;
        String query = "select * from user where account_no = "+ user.account_no;
        List<User> objects = dao.retrieve(query);
        User obj = objects.get(0);
        System.out.println("Current Balance Available: "+obj.balance+"$");
        while(true){
            System.out.println("Enter the amount you want to withdraw:");
            int amount = Integer.parseInt(scanner.nextLine());
            if(amount>obj.balance){
                System.out.println("Cannot withdraw more than available balance.\n Try Again or Enter 0 to exit.");
            }
            else{
                obj.balance-=amount;
                result = dao.update(obj);
                break;
            }
            String message = (result > 0) ? "Amount Withdrawn Successfully" : "Transaction Failed. Try Again..";
            System.out.println(message);
        }

    }

}

package dmat;

import dmat.controller.UserService;
import dmat.model.Stock;
import dmat.model.Transaction;
import dmat.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;

public class LoginMenu extends Menu {
    private static LoginMenu menu = new LoginMenu();

    public static LoginMenu getInstance() {

        return menu;
    }

    public void showMenu() {

        User user = new User();
        boolean result = false;

        System.out.println("Enter Your Email:");
        user.email = scanner.nextLine();

        System.out.println("Enter Your Password:");
        user.password = scanner.nextLine();

        try {
            // Encoded to Hash i.e. SHA-256 so as to match correctly
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(user.password.getBytes(StandardCharsets.UTF_8));
            user.password = Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            System.err.println("Something Went Wrong: " + e);
        }
        result = auth.loginUser(user);
        System.out.println(result);
        if (result) {

            // Link the User to the Session User :)
            DMATSession.user = user;

            System.out.println("^^^^^^^^^^^^^^^^^^^");
            System.out.println("Welcome to User App");
            System.out.println("Hello, " + user.name);
            System.out.println("Balance, " + user.balance);
            System.out.println("Its: " + new Date());
            System.out.println("^^^^^^^^^^^^^^^^^^^");

            boolean quit = false;

            while (true) {
                System.out.println("0: Exit");
                System.out.println("1: Display Account Details");
                System.out.println("2: Deposit Money");
                System.out.println("3: Withdraw money");
                System.out.println("4: Buy Transaction");
                System.out.println("5: Sell Transaction");
                System.out.println("6: View Transaction Report.");
                System.out.println("7: Add/Update Stock");
                System.out.println("Select an Option");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        usshare.viewAccount();
                        break;

                    case 2:
                        usr.addMoney();
                        System.out.println("****************************");
                        break;

                    case 3:
                        usr.removeMoney();
                        break;

                    case 4:
                        Transaction tb = trans.buyStock(user);
                        if(tb.transaction_fee!=0) {
                            usshare.addShare(tb);
                            break;
                        }
                    case 5:
                        System.out.println("Balance Amount, " + user.balance);
                        Transaction ts = trans.sellStock(user);
                        if(ts.transaction_fee!=0) {
                            usshare.removeShare(ts);
                            break;
                        }

                    case 6:
                        trans.viewTransaction(user);
                        break;

                    case 7:
                        Stock stock = new Stock();
                        System.out.println("1: Add new Stock.");
                        System.out.println("2: Update Price of a Stock.");
                        System.out.println("3: Go Back.");
                        int ch = Integer.parseInt(scanner.nextLine());
                        switch (ch){
                            case 1:
                                System.out.println("Enter the company's name:");
                                stock.company_name = scanner.nextLine();
                                System.out.println("Enter the current share price:");
                                stock.current_price_per_share = scanner.nextFloat();
                                System.out.println("Enter the shares available of the company:");
                                stock.total_shares = Integer.parseInt(scanner.nextLine());
                                stoc.addStock(stock);
                                break;
                            case 2:
                                System.out.println("Enter the company ID for company whose stock price you want to update");
                                int company_id = Integer.parseInt(scanner.nextLine());
                                stoc.updateStock(company_id);
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Invalid Choice.");
                        }
                        break;


                    case 0:
                        System.out.println("Thank You for Using User App !!");
                        quit = true;
                        break;

                    default:
                        System.err.println("Invalid Choice...");
                        break;
                }

                if (quit) {
                    break;
                }

            }
        } else {
            System.err.println("Authentication Failed..");
        }
    }
}

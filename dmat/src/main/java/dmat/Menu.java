package dmat;

import java.util.Scanner;

import dmat.controller.*;
import dmat.db.DB;


public class Menu {
    AuthenticationService auth = AuthenticationService.getInstance();
    StockService stoc = StockService.getInstance();
    TransactionService trans = TransactionService.getInstance();
    UserShareService usshare = UserShareService.getInstance();

    UserService usr = UserService.getInstance();
    Scanner scanner = new Scanner(System.in);
    void showMainMenu() {

        // Initial Menu for the Application
        while(true) {
            System.out.println("1: Create an Account");
            System.out.println("2: Login");
            System.out.println("3: Quit");

            System.out.println("Select an Option");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 3) {
                System.out.println("Thank You For using DMAT Management App");
                // Close the DataBase Connection, when user has exited the application :)
                DB db = DB.getInstance();
                db.closeConnection();
                scanner.close();
                break;
            }

            try {
                MenuFactory.getMenu(choice).showMenu();
            } catch (Exception e) {
                System.err.println("[Menu] [Exception] Invalid Choice...");
            }



        }
    }

    public void showMenu() {

        System.out.println("Showing the Menu...");
    }

}
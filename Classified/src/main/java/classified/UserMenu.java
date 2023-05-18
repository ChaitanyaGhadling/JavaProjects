package classified;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;

import classified.model.User;
public class UserMenu extends Menu{
    private static UserMenu menu = new UserMenu();

    public static UserMenu getInstance() {

        return menu;
    }

    private UserMenu() {
    }
    public void showMenu() {

        System.out.println("Navigating to User Menu...");

        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("3: Cancel");

        System.out.println("Enter Your Choice: ");
        int initialChoice = Integer.parseInt(scanner.nextLine());

        boolean result = false;

        User user = new User();


        if(initialChoice == 1) {

            System.out.println("Enter Your Name:");
            user.name = scanner.nextLine();

            System.out.println("Enter Your Phone:");
            user.phone = scanner.nextLine();

            System.out.println("Enter Your Email:");
            user.email = scanner.nextLine();

            System.out.println("Enter Your Password:");
            user.password = scanner.nextLine();

            try {
                // Hash the Password of User :)
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(user.password.getBytes(StandardCharsets.UTF_8));
                user.password = Base64.getEncoder().encodeToString(hash);
            }catch (Exception e) {
                System.err.println("Something Went Wrong: "+e);
            }

            System.out.println("Enter Your Address:");
            user.address = scanner.nextLine();

            System.out.println("Enter Your Department:");
            user.department = scanner.nextLine();

            // As we know, user is registering :)
            user.type = 2;
            user.status =1;

            result = auth.registerUser(user);

        }else if(initialChoice == 2) {

            System.out.println("Enter Your Email:");
            user.email = scanner.nextLine();

            System.out.println("Enter Your Password:");
            user.password = scanner.nextLine();

            try {
                // Encoded to Hash i.e. SHA-256 so as to match correctly
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(user.password.getBytes(StandardCharsets.UTF_8));
                user.password = Base64.getEncoder().encodeToString(hash);
            }catch (Exception e) {
                System.err.println("Something Went Wrong: "+e);
            }

            result = auth.loginUser(user);

        }else if(initialChoice == 3) {
            System.out.println("Thank You for Using Classifieds App");
        }else {
            System.err.println("Invalid Choice...");
            System.out.println("Thank You for Using Classifieds App");
        }


        if(result) {

            // Link the User to the Session User :)
            ClassifiedsSession.user = user;

            System.out.println("^^^^^^^^^^^^^^^^^^^");
            System.out.println("Welcome to User App");
            System.out.println("Hello, " + user.name);
            System.out.println("Its: " + new Date());
            System.out.println("^^^^^^^^^^^^^^^^^^^");
            System.out.println(user.status);
            if (user.status == 0) {
                System.out.println("Account Inactivated. Contact Admin for re-activating.");
            } else {
                boolean quit = false;

                while (true) {

                    System.out.println("1: Manage your Profile");
                    System.out.println("2: Post a Classified");
                    System.out.println("3: View Classifieds");
                    System.out.println("4: Request Purchase of a classified");
                    System.out.println("5: Confirm a Sale");
                    System.out.println("6: View your orders");
                    System.out.println("7: Pay for a Purchase");
                    System.out.println("8: Quit User App");
                    System.out.println("Select an Option");

                    int choice = Integer.parseInt(scanner.nextLine());

                    switch (choice) {
                        case 1:
                            System.out.println("My Profile");
                            user.prettyPrint();

                            System.out.println("Do you wish to update Profile (1: update 0: cancel)");

                            choice = Integer.parseInt(scanner.nextLine());


                            if (choice == 1) {
                                System.out.println("Enter Your Name:");
                                String name = scanner.nextLine();
                                if (!name.isEmpty()) {
                                    user.name = name;
                                }

                                System.out.println("Enter Your Phone:");
                                String phone = scanner.nextLine();
                                if (!phone.isEmpty()) {
                                    user.phone = phone;
                                }

                                System.out.println("Enter Your Password:");
                                String password = scanner.nextLine();
                                try {
                                    // Encoded to Hash i.e. SHA-256 so as to match correctly
                                    MessageDigest digest = MessageDigest.getInstance("SHA-256");
                                    byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
                                    password = Base64.getEncoder().encodeToString(hash);
                                }catch (Exception e) {
                                    System.err.println("Something Went Wrong: "+e);
                                }
                                if (!password.isEmpty()) {
                                    user.password = password;
                                }

                                System.out.println("Enter Your Address:");
                                String address = scanner.nextLine();
                                if (!address.isEmpty()) {
                                    user.address = address;
                                }
                                System.out.println("Enter Your Department:");
                                String department = scanner.nextLine();
                                if (!department.isEmpty()) {
                                    user.department = department;
                                }

                                if (auth.updateUser(user)) {
                                    System.out.println("Profile Updated Successfully");
                                } else {
                                    System.err.println("Profile Update Failed...");
                                }

                            }
                            break;

                        case 2:
                            classService.addClassified();
                            break;

                        case 3:
                            System.out.println("1: View all Classifieds");
                            System.out.println("2: Search Classified by ID");
                            System.out.println("3: Search Your Posted Classified");
                            System.out.println("Enter Your Choice: ");
                            int ch = Integer.parseInt(scanner.nextLine());

                            if (ch == 1) {
                                classService.viewClassified();
                            } else if (ch == 2) {
                                System.out.println("Enter ID of Classified you want to view: ");
                                int id = Integer.parseInt(scanner.nextLine());
                                classService.viewClassified(id);
                            } else if (ch == 3) {
                                classService.viewYourClassified();
                            } else {
                                System.err.println("Invalid Choice..");
                            }
                            break;

                        case 4:
                            orderService.requestPurchaseClassified();
                            break;
                        case 5:
                            orderService.confirmSale();
                            break;
                        case 6:
                            orderService.viewOrders();
                            System.out.println("");
                            break;
                        case 7:

                            orderService.makePayment();
                            break;

                        case 8:
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
            }
        }
        else {
            System.err.println("Authentication Failed..");
        }
    }


}

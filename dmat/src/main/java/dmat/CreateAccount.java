package dmat;

import dmat.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class CreateAccount extends Menu{
    private static CreateAccount menu = new CreateAccount();

    public static CreateAccount getInstance() {

        return menu;
    }
    public void showMenu() {

        boolean result = false;
        User user = new User();

        System.out.println("Creating a new account...");
        System.out.println("Enter Your Name:");
        user.name = scanner.nextLine();

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
        result = auth.registerUser(user);
    }
}

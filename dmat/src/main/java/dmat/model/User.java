package dmat.model;

public class User {
    public int account_no;
    public String name;
    public String email;
    public int balance;
    public String password;
    public User(){

    }

    public User(int account_no, String name, String email, int balance, String password) {
        this.account_no = account_no;
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }
    public void prettyPrint() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Name:\t\t"+name);
        System.out.println("Account Number:\t\t"+account_no);
        System.out.println("Email:\t\t"+email);
        System.out.println("Balance Amount:\t"+balance);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
    }
    @Override
    public String toString() {
        return "User [account_no=" + account_no + ", name=" + name + ", email=" + email + ", password=" + password
                + ", balance=" + balance + "]";
    }
}

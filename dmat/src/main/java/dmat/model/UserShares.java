package dmat.model;

public class UserShares {
    public int account_no;
    public int company_id;
    public float current_total_price;
    public float total_shares;


    public UserShares(){

    }

    public UserShares(int account_no, int company_id, float current_total_price, float
            total_shares) {
        this.account_no = account_no;
        this.company_id = company_id;
        this.current_total_price = current_total_price;
        this.total_shares = total_shares;

    }
    public void prettyPrint() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Account Number:\t\t"+account_no);
        System.out.println("Company ID:\t\t"+company_id);
        System.out.println("Stock Amount:\t\t"+current_total_price);
        System.out.println("Transaction Price:\t\t"+total_shares);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
    }
    @Override
    public String toString() {
        return "Transaction [account_no=" + account_no + ",company_id=" + company_id +
                ",current_total_price=" + current_total_price + ",Total Shares =" + total_shares + "]";
    }
}
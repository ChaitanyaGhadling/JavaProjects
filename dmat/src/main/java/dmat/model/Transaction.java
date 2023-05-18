package dmat.model;

public class Transaction {
    public int transaction_id;
    public int account_no;
    public int company_id;
    public float shares_bought_sold;
    public float transaction_price;
    public String transaction_date;
    public float transaction_fee;
    public int transaction_type;

    public Transaction(){

    }

    public Transaction(int transaction_id, int account_no, int company_id, float shares_bought_sold, float
            transaction_price, String transaction_date, float transaction_fee, int transaction_type) {
        this.transaction_id = transaction_id;
        this.account_no = account_no;
        this.company_id = company_id;
        this.shares_bought_sold = shares_bought_sold;
        this.transaction_price = transaction_price;
        this.transaction_date = transaction_date;
        this.transaction_fee = transaction_fee;
        this.transaction_type = transaction_type;
    }
    public void prettyPrint() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Transaction ID:\t\t"+transaction_id);
        System.out.println("Account Number:\t\t"+account_no);
        System.out.println("Company ID:\t\t"+company_id);
        System.out.println("Stock Amount:\t\t"+shares_bought_sold);
        System.out.println("Transaction Price:\t\t"+transaction_price);
        System.out.println("Transaction Date:\t\t"+transaction_date);
        System.out.println("Transaction Fee:\t"+transaction_fee);
        System.out.println("Transaction Type:\t"+transaction_type);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
    }
    @Override
    public String toString() {
        return "Transaction [transaction_id=" + transaction_id + ", account_no=" + account_no + ",company_id=" +
                company_id + ",shares_bought_sold=" + shares_bought_sold + ",transaction_price=" + transaction_price + ", " +
                "transaction_date=" + transaction_date + ", transaction_fee=" + transaction_fee + ", " +
                "transaction_type=" + transaction_type + "]";
    }
}


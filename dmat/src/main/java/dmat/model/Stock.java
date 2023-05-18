package dmat.model;

public class Stock {
    public int company_id;
    public String company_name;
    public float current_price_per_share;
    public String lastUpdated;
    public int total_shares;

    public Stock(){

    }

    public Stock(int company_id, String company_name, float current_price_per_share, String lastUpdated, int total_shares) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.current_price_per_share = current_price_per_share;
        this.lastUpdated = lastUpdated;
        this.total_shares = total_shares;
    }
    public void prettyPrint() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Company Name:\t\t"+company_name);
        System.out.println("Company ID:\t\t"+company_id);
        System.out.println("Current Price:\t\t"+current_price_per_share);
        System.out.println("Last Updated Date:\t"+lastUpdated);
        System.out.println("Total Shares in market:\t"+total_shares);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
    }
    @Override
    public String toString() {
        return "Stock [company_id=" + company_id + ", company_name=" + company_name + ", current_price_per_share=" +
                current_price_per_share + ", lastUpdated=" + lastUpdated + ", total_shares=" + total_shares + "]";
    }
}

package dmat.controller;

import dmat.db.StockDAO;
import dmat.model.Stock;
import dmat.model.User;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StockService {
    Scanner scanner = new Scanner(System.in);
    private static StockService service = new StockService();
    StockDAO dao = new StockDAO();

    public static StockService getInstance() {
        return service;
    }

    public void addStock(Stock stock) {
        int result = dao.insert(stock);
        String message = (result > 0) ? "Stock Added Successfully" : "Adding Stock Failed. Try Again..";
        System.out.println(message);
    }

    public void updateStock(int companyId) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date date1 = calendar.getTime();
        String query = "select * from Stock where company_id ="+companyId;
        List<Stock> objects = dao.retrieve(query);
        Stock obj = objects.get(0);
        System.out.println("Existing Details:");
        obj.prettyPrint();
        System.out.println("Enter the new Stock Price:");
        obj.current_price_per_share = Float.parseFloat(scanner.nextLine());
        obj.lastUpdated = dateFormat.format(date1);
        int result = dao.update(obj);
        String message = (result > 0) ? "Stock Updated Successfully" : "Updating Stock Failed. Try Again..";
        System.out.println(message);
    }
}

package classified.controller;

import classified.ClassifiedsSession;
import classified.db.ClassifiedDAO;
import classified.db.OrderDAO;
import classified.model.Classified;
import classified.model.User;

import java.util.List;
import java.util.Scanner;

public class ClassifiedService {
    private static ClassifiedService service = new ClassifiedService();
    ClassifiedDAO dao = new ClassifiedDAO();
    Scanner scanner = new Scanner(System.in);

    private ClassifiedService(){

    }

    public static ClassifiedService getInstance() {
        return service;
    }

    public void addClassified() {
        Classified classified = new Classified();


        System.out.println("Enter Classifieds Details....");

        System.out.println("Enter Product Category:");
        classified.category = scanner.nextLine();

        System.out.println("Enter Product Type:");
        classified.type = scanner.nextLine();

        System.out.println("Enter Product headline:");
        classified.headline = scanner.nextLine();

        System.out.println("Enter Product Name:");
        classified.name = scanner.nextLine();

        System.out.println("Enter Product Brand:");
        classified.brand = scanner.nextLine();

        System.out.println("Enter Product Condition:");
        classified.conditions = scanner.nextLine();

        System.out.println("Enter Product Description:");
        classified.description = scanner.nextLine();

        System.out.println("Enter Product Price:");
        classified.price = Float.parseFloat(scanner.nextLine());
        if(ClassifiedsSession.user.type ==2){
            classified.status = 1;
        }
        else{
            classified.status = 0;
        }
        int result = dao.insert(classified);
        String message = (result > 0) ? "Classified Added Successfully" : "Adding Classified Failed. Try Again..";
        System.out.println(message);
    }

    public void removeClassified() {
        Classified classified = new Classified();
        System.out.println("Enter Classified ID to be deleted: ");
        classified.id = Integer.parseInt(scanner.nextLine());
        int result = dao.delete(classified);
        String message = (result > 0) ? "Classified Deleted Successfully" : "Deleting Classified Failed. Try Again..";
        System.out.println(message);
    }

    public void viewClassified() {
        List<Classified> objects = dao.retrieve();
        if(objects.size()!=0) {
            for (Classified object : objects) {
                object.prettyPrint();
            }
        }
        else{
            System.out.println("Not found");
        }
        System.out.println("------------------------");

    }

    public void viewClassified(int id) {
        String sql  = "select * from classified where id="+id;
        List<Classified> object = dao.retrieve(sql);
        if(object.size()!=0) {
            Classified obj = object.get(0);
            obj.prettyPrint();
        }
        else{
            System.out.println("Not found");
        }

    }

    public void UpdateType(int id) {
        viewClassified(id);
        String sql  = "select * from classified where id="+id;
        List<Classified> object = dao.retrieve(sql);
        if(object.size()!=0) {
            Classified obj = object.get(0);
            System.out.println("1: Change Type.");
            System.out.println("2: Change Category.");
            System.out.println("3: No change.");
            int ch = Integer.parseInt(scanner.nextLine());
            if (ch == 1) {
                System.out.println("Enter New type for classified.");
                obj.type = scanner.nextLine();
                int result = dao.update(obj);
                String message = (result > 0) ? "Classified Updated Successfully" : "Updating Classified Failed. Try Again..";
                System.out.println(message);
            } else if (ch == 2) {
                System.out.println("Enter New category for classified.");
                obj.category = scanner.nextLine();
                int result = dao.update(obj);
                String message = (result > 0) ? "Classified Updated Successfully" : "Updating Classified Failed. Try Again..";
                System.out.println(message);
            } else if (ch == 3) {
                System.out.println("No change requested.");
            } else {
                System.out.println("Invalid Choice.");
            }
        }
        else{
            System.out.println("Not found.");
        }

    }
    public void approveRejectClassified() {
        String sql = "select * from classified where status=0";
        System.out.println("*** Pending Classifieds ****");
        List<Classified> objects = dao.retrieve(sql);
        for(Classified object : objects) {
            object.prettyPrint();
        }
        if(objects.size() == 0){
            System.out.println("No Classified Pending.");
        }
        else{
            System.out.println("Enter the Id of classifieds you want to change");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("Press 1 to Approve and anything else to Reject.");
            int ch = Integer.parseInt(scanner.nextLine());
            sql = "select * from classifieds where id="+id;
            List<Classified> object = dao.retrieve(sql);
            Classified obj = object.get(0);
            if(ch==1){
                obj.status = 1;
            }
            else{
                obj.status = 2;
            }
            int result = dao.update(obj);
            String message = (result > 0) ? "Classified Status Changed" : "Failed. Try Again..";
            System.out.println(message);
        }

    }


    public void viewYourClassified() {
        String sql  = "select * from classified where postedByID="+ClassifiedsSession.user.id;
        List<Classified> objects = dao.retrieve(sql);
        if(objects.size()!=0) {
            for (Classified object : objects) {
                object.prettyPrint();
            }
        }
        else{
            System.out.println("Not found");
        }
        System.out.println("------------------------");
    }
}

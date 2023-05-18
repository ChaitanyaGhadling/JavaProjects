package dmat;
import dmat.db.DB;
public class App {
    public static void main( String[] args ){

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println( "Welcome to DMAT Management Application" );
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        Menu menu = new Menu();
        //Menu menu = null;

        // if(args.length > 0) {
        DB.FILEPATH = args[0];
        //  }
        menu.showMainMenu();

    }
}

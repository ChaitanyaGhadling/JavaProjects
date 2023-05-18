package classified;

import classified.db.DB;

public class App {
	
    public static void main( String[] args ){
       
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println( "Welcome to Amazon Classifieds Application" );
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    	
    	Menu menu = new Menu();
        //Menu menu = null;
        
       // if(args.length > 0) {
        DB.FILEPATH = args[0];
      //  }

        
        menu.showMainMenu();
		
    }
    
}

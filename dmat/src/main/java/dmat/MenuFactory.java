package dmat;

public class MenuFactory {

	public static Menu getMenu(int type) {
				
		if(type == 1) {
			return CreateAccount.getInstance();
		}else if (type == 2) {
			return LoginMenu.getInstance();
		}
		return null;
	}
	
}

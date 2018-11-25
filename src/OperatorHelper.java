import java.util.Scanner;

public class OperatorHelper {

	static public void addItem(Scanner scanner) {
		
	}

	/*
	  			+ "1. title\n"
				+ "2. author name\n"
				+ "3. price\n" 
				+ "4. quantity\n"
	 */
	static public void editItem(Scanner scanner, int choice) {
		
		Document d = SearchHelper.searchInventory();
		

	}

	static public void removeItem(Scanner scanner) {
		
		Document d = SearchHelper.searchInventory();
		if(d == null)
			return;
		
		System.out.println("Would you like to delete this document? [Y/N]");
		System.out.println("Choice:");
		
		String choice = scanner.nextLine();

		while ( !(choice.equals("Y") || choice.equals("y") || choice.equals("N") || choice.equals("n")) ) {
			System.out.println("invalid menu selection, please try again");
			System.out.println("Choice:");
			choice = scanner.nextLine();
		}
			
		if (choice.equals("Y") || choice.equals("y") ) {
			System.out.println("The document will be removed");
			Database.getInstance().removeDocument(d);
		}
		else if (choice.equals("N") || choice.equals("n") ) {
			System.out.println("The document will not be deleted");
		}
		else {
			System.out.println("invalid menu selection, no action will be taken");
		}

	}

}

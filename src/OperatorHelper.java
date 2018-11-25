import java.util.Scanner;

public class OperatorHelper {


	static public void addItem(Scanner scanner) {
		
		scanner.nextLine();
		
		System.out.println("Please enter the title: ");
		String title = scanner.nextLine();

		System.out.println("Please enter the author name: ");
		String aName = scanner.nextLine();
		
		System.out.println("Please enter the contents: ");
		String contents = scanner.nextLine();
		
		System.out.println("Please enter the ISBN: ");
		String ISBN = scanner.nextLine();
		
		System.out.println("Please enter the price: ");
		while(!scanner.hasNextDouble()) {}
		double price = scanner.nextDouble();
		
		System.out.println("Please enter the doucment type: "
				+ "1: Book\n"
				+ "2: Magazine\n"
				+ "3: Journal\n");
		while(!scanner.hasNextInt()) {}
		int type = scanner.nextInt();
		Database d = Database.getInstance();
		
		if(type == 1) {
			System.out.println("Book added to system");	
			d.addDocument(new Book(title, aName, contents, ISBN, price));
		}
		else if(type == 2) {
			System.out.println("Magazine added to system");	
			d.addDocument(new Magazine(title, aName, contents, ISBN, price));
		}
		else if(type == 3){
			System.out.println("Journal added to system");	
			d.addDocument(new Journal(title, aName, contents, ISBN, price));
		}
		else {
			System.out.println("Ivalid menue selection, document not added");
		}
	}

	/*
	  			+ "1. title\n"
				+ "2. author name\n"
				+ "3. price\n" 
				+ "4. quantity\n"
	 */
	static public void editItem(Scanner scanner, int choice) {
		
		switch(choice){
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		default:
			System.out.println("\nNot a valid input.\n");
		}
		

	}

	static public void removeItem(Scanner scanner) {
		
		Document d = SearchHelper.searchInventory();
		if(d == null)
			return;
		
		scanner.nextLine();
		
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

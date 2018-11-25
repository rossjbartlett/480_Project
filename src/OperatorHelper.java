import java.util.InputMismatchException;
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
		double price = readDouble(scanner);
		
		System.out.println("Please enter the doucment type:\n "
				+ "1: Book\n"
				+ "2: Magazine\n"
				+ "3: Journal\n"
				+ "Enter your choice:\n");
		int type = readInt(scanner);
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


	static public void editItem(Scanner scanner, int choice) {
		
		scanner.nextLine();

		Document d = SearchHelper.searchInventory();
		
		switch(choice){
		case 1:
			System.out.println("Please enter the new title:");
			String title = scanner.nextLine();
			d.setTitle(title);
			break;
		case 2:
			System.out.println("Please enter the new author name:");
			String authorName = scanner.nextLine();
			d.setAuthorName(authorName);
			break;
		case 3:
			System.out.println("Please enter the new price:");
			double price = readDouble(scanner);
			d.setPrice(price);
			break;
		case 4:
			System.out.println("Please enter the new quantity:");
			int quantity = readInt(scanner);
			d.setQuantity(quantity);
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
	

	private static double readDouble(Scanner scanner) {
		try {
			return scanner.nextDouble();
		}catch(InputMismatchException e){
			System.out.println("Number must be a double, please try again");
			scanner.nextLine();
			return readDouble(scanner);
		}
	}
	

	private static int readInt(Scanner scanner) {
		try {
			return scanner.nextInt();
		}catch(InputMismatchException e){
			System.out.println("Number must be a integer, please try again");
			scanner.nextLine();
			return readInt(scanner);
		}
	}
}

import java.util.Scanner;

public class Client implements Constants{

	Database database;
	Scanner scanner;
	AccountHolder currentUser;

	public Client() {
		database = Database.getInstance();
		scanner = new Scanner(System.in);
		currentUser = null;
	}

	public void registerAccount(OrdinaryBuyer b) {
		//make a new account that is a copy of the info, but is RegisteredBuyer
		//remove the old account in the DB
		//add to DB
		RegisteredBuyer newAccount = new RegisteredBuyer(b.getName(), b.getUsername(), b.getPassword(), b.getAddress(), b.getPhoneNumber(), database.getPromoListObject());
		//newAccount is auto added as an Observer to the PromoList in the constructor
		boolean removed = database.removeAccount(b);
		if(removed) {
			database.addAccount(newAccount);
			currentUser = newAccount;
		}
		else {
			System.out.println("Error in registerAccount()...");
		}
	}

	public void unregisterAccount(RegisteredBuyer b) {
		//make a new account that is a copy of the info, but is OrdinaryBuyer
		//remove the old account in the DB
		//add to DB
		OrdinaryBuyer newAccount = new OrdinaryBuyer(b.getName(), b.getUsername(), b.getPassword(), b.getAddress(), b.getPhoneNumber());
		database.getPromoListObject().detach(b);
		boolean removed = database.removeAccount(b);
		if(removed) {
			database.addAccount(newAccount);
			currentUser = newAccount;
		}
		else {
			System.out.println("Error in unregisterAccount()...");
		}
	}


	private void initDemoDB() {
		//insert some test users and documents
		OrdinaryBuyer rossOB = new OrdinaryBuyer("Ross Bartlett", "rossb1", "rb123", "504 Maple Road", "403-321-9876");
		Operator antoineOP = new Operator("Antoine Bizon", "antoineb1", "ab123", "123 Happy Lane", "403-123-4567");
		RegisteredBuyer dylanRB = new RegisteredBuyer("Dylan Gordon", "dylang1", "dg123", "420 Green Street", "587-456-1234", database.getPromoListObject());

		database.addAccount(rossOB);
		database.addAccount(antoineOP);
		database.addAccount(dylanRB);

		Book book = new Book("Hamlet", "Shakespeare", "To be, or not to be, that is the question:\n" + 
				"Whether 'tis nobler in the mind to suffer\n" + 
				"The slings and arrows of outrageous fortune,\n" + 
				"Or to take arms against a sea of troubles\n" + 
				"And by opposing end them. To die—to sleep,\n" + 
				"No more; and by a sleep to say we end\n" + 
				"The heart-ache and the thousand natural shocks\n" + 
				"That flesh is heir to: 'tis a consummation\n" + 
				"Devoutly to be wish'd. To die, to sleep;\n" + 
				"To sleep, perchance to dream—ay, there's the rub:\n" + 
				"For in that sleep of death what dreams may come,\n" + 
				"When we have shuffled off this mortal coil,\n" + 
				"Must give us pause.\n", "ABC-123-XYZ-456", 12.99,2);

		Magazine magazine = new Magazine("Time Magazine", "Macleans", 
				"In this edition of Time Magazine, we look at the changing landscape of the economy under Trump.\n", 
				"654-RJG-123-ASA-112", 3.50,3);

		Journal journal = new Journal("Tom's Autobiography", "Tom Wilson", 
				"I am Tom Wilson - hockey player and proud father. I like hot dogs and the movie Up.\n", 
				"342-WEQ-521-BDA-542", 9.75,2);

		database.addDocument(book);
		database.addDocument(magazine);
		database.addDocument(journal);
		
		database.addToPromoList(magazine);
	}

	private void pressEnter() {
		//make menu wait till enter button is pressed
		System.out.println("<<Press Enter to continue>>");
		//scanner.nextLine();
		scanner.nextLine();
	}

	private void displayInventory() {
		System.out.println("Current Inventory:");
		for(Document d : database.getInventory()) {
			System.out.println("\t"+d.headerString());
		}
		System.out.println();
	}
	
	private void displayPromoList() {
		System.out.println("Current PromoList:");
		for(Document d : database.getPromoListObject().getPromolist()) {
			System.out.println("\t"+d.headerString());
		}
		System.out.println();
	}
	
	
	private int getMenuSelection() {
		int choice = -1;
		if(scanner.hasNextInt()) {
			choice = scanner.nextInt();
		}
		scanner.nextLine();
		return choice;
	}

	private int getOrdinaryBuyerMenuChoice(){
		System.out.println("Please select one of the following operations:\n"
				+ "1. Display Inventory\n"
				+ "2. Search Inventory\n" 
				+ "3. Place Order\n"
				+ "4. Register Account\n"
				+ "5. Quit\n"
				+ "Enter your choice:\n");
		return getMenuSelection();
	}
	
	

	private int runOrdinaryBuyerMenu() {
		int quit = 0;
		switch(getOrdinaryBuyerMenuChoice()){
		case 1:
			displayInventory();
			pressEnter();
			break;
		case 2:
			SearchHelper.search(scanner);
			pressEnter();
			break;
		case 3:
			ClientPlaceOrderHelper.placeOrder();
			pressEnter();
			break;
		case 4:
			registerAccount((OrdinaryBuyer) currentUser);
			// currentUser is now changed to an object of RegisteredBuyer
			System.out.println("Registration Successful.");
			pressEnter();
			break;
		case 5:
			quit = 1;
			break;
		default:
			System.out.println("\nNot a valid input.\n");
			pressEnter();
		}
		return quit;
	}


	private int getRegisteredBuyerMenuChoice(){
		System.out.println("Please select one of the following operations:\n"
				+ "1. Display Inventory\n"
				+ "2. Display PromoList\n"
				+ "3. Search Inventory\n" 
				+ "4. Place Order\n"
				+ "5. Unregister Account\n"
				+ "6. Quit\n"
				+ "Enter your choice:\n");
		return getMenuSelection();
	}

	private int runRegisteredBuyerMenu() {
		int quit = 0;
		switch(getRegisteredBuyerMenuChoice()){
		case 1:
			displayInventory();
			pressEnter();
			break;
		case 2:
			displayPromoList();
			pressEnter();
			break;
		case 3:
			SearchHelper.search(scanner);
			pressEnter();
			break;
		case 4:
			ClientPlaceOrderHelper.placeOrder();
			pressEnter();
			break;
		case 5:
			unregisterAccount((RegisteredBuyer) currentUser);
			// currentUser is now changed to an object of OrdinaryBuyer
			System.out.println("Unregistration Successful.");
			pressEnter();
			break;
		case 6:
			quit = 1;
			break;
		default:
			System.out.println("\nNot a valid input.\n");
			pressEnter();
		}
		return quit;
	}

	private int getOperatorMenuChoice(){
		System.out.println("Please select one of the following operations:\n"
				+ "1. Add Item \n"
				+ "2. Edit Item\n"
				+ "3. Remove Item\n" 
				+ "4. View Inventory\n"
				+ "5. View PromoList\n"
				+ "6. Search Inventory\n"
				+ "7. Quit\n"
				+ "Enter your choice:\n");
		return getMenuSelection();
	}
	

	private int getEditChoice(){
		System.out.println("What field would you like to edit:\n"
				+ "1. title\n"
				+ "2. author name\n"
				+ "3. price\n" 
				+ "4. quantity\n"
				+ "Enter your choice:\n");
		return getMenuSelection();
	}
	
	private int runOperatorMenu() {
		int quit = 0;
		switch(getOperatorMenuChoice()){
		case 1:
			OperatorHelper.addItem(scanner);
			pressEnter();
			break;
		case 2:
			OperatorHelper.editItem(scanner, getEditChoice());
			pressEnter();
			break;
		case 3:
			OperatorHelper.removeItem(scanner);
			pressEnter();
			break;
		case 4:
			displayInventory();
			pressEnter();
			break;
		case 5:
			displayPromoList();
			pressEnter();
			break;
		case 6:
			SearchHelper.search(scanner);
			pressEnter();
			break;
		case 7:
			quit = 1;
			break;
		default:
			System.out.println("\nNot a valid input.\n");
			pressEnter();
		}
		return quit;
	}

	private void runLoginMenu() {
		System.out.println("Please enter your login information.");
		while(currentUser==null) {
			System.out.print("Username: ");
			String username = scanner.nextLine();
			System.out.print("Password: ");
			String password = scanner.nextLine();
			currentUser = database.login(username, password);
			if(currentUser==null) {
				System.out.println("Invalid authentication. Try again.");
			}
		}
		System.out.println("Welcome, "+currentUser.getName()+".\n");
	}

	public static void main(String[] args) {

		Client demo = new Client();

		demo.initDemoDB();//insert some test users and documents

		demo.runLoginMenu();

		int quit = 0;
		while(quit==0) { 
			switch(demo.currentUser.getType()) {
			case OPERATOR_TYPE:
				quit = demo.runOperatorMenu();
				break;
			case ORDINARY_BUYER_TYPE:
				quit = demo.runOrdinaryBuyerMenu();
				break;
			case REGISTERED_BUYER_TYPE:
				quit = demo.runRegisteredBuyerMenu();
				break;
			default:
				System.err.println("Error currentUser type.");
				System.exit(1);
			} // end switch
		}
		System.out.println("\nProgram terminated!\n");


	}//end main

}

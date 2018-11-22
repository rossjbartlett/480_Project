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
				"Must give us pause.\n", "ABC-123-XYZ-456", 12.99);

		Magazine magazine = new Magazine("Time Magazine", "Macleans", 
				"In this edition of Time Magazine, we look at the changing landscape of the economy under Trump.\n", 
				"654-RJG-123-ASA-112", 3.50);

		Journal journal = new Journal("Tom's Autobiography", "Tom Wilson", 
				"I am Tom Wilson - hockey player and proud father. I like hot dogs and the movie Up.\n", 
				"342-WEQ-521-BDA-542", 9.75);

		database.addDocument(book);
		database.addDocument(magazine);
		database.addDocument(journal);
		
		database.addToPromoList(magazine);
	}

	private void pressEnter() {
		//make menu wait till enter button is pressed
		System.out.println("<<Press Enter to continue>>");
		scanner.nextLine();
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
	
	private void searchInventory()
	{
		System.out.println("How would you like to search the inventory?\n"
				+ "1. Search by title\n"
				+ "2. Search by author\n"
				+ "3. Search by ISBN\n"
				+ "4. Search by price\n"
				+ "Enter your choice:");
		
		switch(getMenuSelection())
		{
		case 1:
			System.out.println("Searching by title:");
			searchByTitle();
			break;
		case 2:
			System.out.println("Searching by Author:");
			searchByAuthor();
			break;
		case 3:
			System.out.println("Searching by ISBN:");
			searchByISBN();
			break;
		case 4:
			System.out.println("Searching by price:");
			searchByPrice();
			break;
		default:
			System.out.println("\n Not a valid input\n");
			pressEnter();
		}
		System.out.println();
	}
	
	private void searchByTitle()
	{
		System.out.print("Document Title: ");
		String title = null;
		while(title == null) {
			title = scanner.nextLine();
			title = scanner.nextLine();
		}
		for(Document d: database.getInventory())
		{
			if(d.title.toLowerCase().contains(title.toLowerCase()))
			{
				System.out.println("\n" + d.headerString());
			}
		}
	}
	
	private void searchByAuthor()
	{
		System.out.print("Document Author: ");
		String author = null;
		while(author == null) {
			author = scanner.nextLine();
			author = scanner.nextLine();
		}
		for(Document d: database.getInventory())
		{
			if(d.authorName.toLowerCase().contains(author.toLowerCase()))
			{
				System.out.println("\n" + d.headerString());
			}
		}
	}
	private void searchByISBN()
	{
		System.out.print("Document ISBN: ");
		String isbn = null;
		while(isbn == null) {
			isbn = scanner.nextLine();
			isbn = scanner.nextLine();
		}
		for(Document d: database.getInventory())
		{
			if(d.ISBN.equals(isbn))
			{
				System.out.println("\n" + d.headerString());
			}
		}
	}
	private void searchByPrice()
	{
		System.out.print("Document Price: ");
		double price = 0;
		while(price == 0) {
			price = scanner.nextDouble();
		}
		for(Document d: database.getInventory())
		{
			if(d.price == price)
			{
				System.out.println("\n" + d.headerString());
			}
		}
	}
	
	private void placeOrder()
	{
		searchInventory();
		
		System.out.print("Please enter the title of the document you would like to order: ");
		String title = scanner.nextLine();
		System.out.print("\nPlease enter the author of the document you would like to order: ");
		String author = scanner.nextLine();
		
		for(Document d: database.getInventory())
		{
			if(d.title.equals(title) && d.authorName.equals(author))
			{
				if(d.quantity > 0)
				{
					boolean validOrder= false;
					int numOfCopies = -1;
					while(!validOrder)
					{
						System.out.print("\nWe have " + d.quantity + " copies left, how many would you like to order? ");
						numOfCopies = -1;
						while(numOfCopies == -1)
							numOfCopies = scanner.nextInt();
						if(numOfCopies > d.quantity)
						{
							System.out.print("\nSorry we do not have that many copies available, would you like to try again? [Y/N] ");
							scanner.nextLine();
							String response = scanner.nextLine();
							response = response.toLowerCase();
							if(response.equals("n"))
							{
								return;
							}
						}
						else
						{
							validOrder = true;
						}
					}
					
					if(validOrder)
					{
						enterShippingInfo();
						makePayment(d, numOfCopies);
						
						Document a = d;
						a.quantity = a.quantity - numOfCopies;
						database.updateDocument(d, a);
					}
				}
				else
				{
					System.out.println("Unfortunately we are all out of our copies of " + title);
				}
			}
		}
		
		System.out.println("\n author: " + author + " title: " + title);
		
	}
	
	private void enterShippingInfo()
	{
		System.out.print("\nWhere would you like this sent to?\nCity: ");
		scanner.nextLine();
		String city = scanner.nextLine();
		System.out.print("Street: ");
		String street = scanner.nextLine();
		System.out.print("House/apartment number: ");
		String houseNumber = scanner.nextLine();	// Assumed you could have aprtment '900a', so i used string instead of int
		System.out.print("Province: ");
		String province = scanner.nextLine();
		
		System.out.println("Your item will be shipped to " + houseNumber + " " + street + ", " + city + " " + province);
	}
	
	private void makePayment(Document doc, int numOfCopies)
	{
		
	}
	
	
	private int getMenuSelection() {
		if(scanner.hasNextInt()) return scanner.nextInt();
		else return -1; // they input a non-int
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
			searchInventory();
			pressEnter();
			break;
		case 3:
			placeOrder();
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
			searchInventory();
			pressEnter();
			break;
		case 4:
			placeOrder();
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
				//quit = demo.runOperatorMenu(); // TODO 
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

import java.util.Scanner;

public class Demo implements Constants{
	
	 Database database;
	 Scanner scanner;
	 Account user;
	 
	 public Demo() {
		database = new Database();
		scanner = new Scanner(System.in);
		user = null;
	}
	
	private void initDemoDB() {
		//insert some test users and documents
		OrdinaryBuyer rossOB = new OrdinaryBuyer(ORDINARY_BUYER_TYPE, "Ross Bartlett", "rossb1", "rb123", "504 Maple Road", "403-321-9876");
		Operator antoineOP = new Operator(OPERATOR_TYPE, "Antoine Bizon", "antoineb1", "ab123", "123 Happy Lane", "403-123-4567");
		RegisteredBuyer dylanRB = new RegisteredBuyer(REGISTERED_BUYER_TYPE, "Dylan Gordon", "dylang1", "dg123", "420 Green Street", "587-456-1234", database.getPromoList());

		database.addAccount(rossOB.getAccount());
		database.addAccount(antoineOP.getAccount());
		database.addAccount(dylanRB.getAccount());
		
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
	}
	
	private void pressEnter() {
		//make menu wait till enter button is pressed
		scanner.nextLine();
		scanner.nextLine();
	}
	
	private void displayInventory() {
		System.out.println("Current Inventory:");
		for(Document d : database.inventory) {
			System.out.println("\t"+d.headerString());
		}
		System.out.println();
	}
	
	private int getOrdinaryBuyerMenuChoice(){
	    System.out.println("Please select one of the following operations:\n"
	    		+ "1. Display Inventory\n"
	    		+ "2. Search Inventory\n" 
	    		+ "3. Place Order\n"
	    		+ "4. Register Account\n"
	    		+ "5. Quit\n"
	    		+ "Enter your choice:\n");
	    return scanner.nextInt();
	}
	
	private void runOrdinaryBuyerMenu() {
		int quit = 0;
		while(true) {
	        switch(getOrdinaryBuyerMenuChoice()){
	            case 1:
	                displayInventory();
	                pressEnter();
	                break;
	            case 2:
//	                searchInventory();
	                pressEnter();
	                break;
	            case 3:
//	                placeOrder();
	                pressEnter();
	                break;
	            case 4:
//	                registerAccount();
	            		//how to then make them switch to the registeredBuer menu?
	                pressEnter();
	                break;
	            case 5:
	                System.out.println("\nProgram terminated!\n");
	                quit = 1;
	                break;
	            default:
	                System.out.println("\nNot a valid input.\n");
	                pressEnter();
	        }
	        if(quit == 1) break;
	    }
	}
	
	
	
	
	
	private void loginMenu() {
		System.out.println("Please enter your login information.");
		while(user==null) {
		    System.out.print("Username: ");
		    String username = scanner.nextLine();
		    System.out.print("Password: ");
		    String password = scanner.nextLine();
		    user = database.login(username, password);
		    if(user==null) {
		    		System.out.println("Invalid authentication. Try again.");
		    }
		}
		System.out.println("Welcome, "+user.getName()+".\n");
	}

	public static void main(String[] args) {
		
		Demo demo = new Demo();
		
		
		demo.initDemoDB();//insert some test users and documents
		
		demo.loginMenu();
		
		//TODO: make the menu for each user type; use the example menu() above
		
		switch(demo.user.getType()) {
			case OPERATOR_TYPE:
	            //demo.runOperatorMenu();
	            break;
	        case ORDINARY_BUYER_TYPE:
	        		demo.runOrdinaryBuyerMenu();
	            break;
	        case REGISTERED_BUYER_TYPE:
	        		//demo.runRegisteredBuyerMenu();
	            break;
	        default:
	        		System.err.println("Error user type.");
	            System.exit(1);
		} // end switch
		
	}//end main

}

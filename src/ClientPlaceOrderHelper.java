import java.util.ArrayList;
import java.util.Scanner;

public class ClientPlaceOrderHelper {
	
	static private Document placeOrderSearch(Scanner scanner)
	{
		Database database = Database.getInstance();
		ArrayList<Document> searchResults = new ArrayList<>();
		System.out.print("What would you like to search for?   ");
		String search = null;
		while(search == null) {
			search = scanner.nextLine();
		}
		
		System.out.println("\nPlease select one of the following items");
		int i = 0;
		for(Document d: database.getInventory())
		{
			if(d.title.toLowerCase().contains(search.toLowerCase()))
			{
				System.out.println("\n[" + i + "] " + d.headerString());
				searchResults.add(d);
			}
			else if(d.authorName.toLowerCase().contains(search.toLowerCase()))
			{
				System.out.println("\n[" + i + "] " + d.headerString());
				searchResults.add(d);
			}
			else if(d.ISBN.toLowerCase().contains(search.toLowerCase()))
			{
				System.out.println("\n[" + i + "] " + d.headerString());
				searchResults.add(d);
			}
			i++;
		}
		int item = -1;
		if(scanner.hasNextInt())
		{
			item = scanner.nextInt();
			scanner.nextLine();
		}
		
		if(item == -1 || item > i - 1)
		{
			return null;
		}
		else
		{
			return searchResults.get(item);
		}
		
	}
	

	static public void placeOrder()
	{
		//SearchHelper.searchInventory();
		Scanner scanner = new Scanner(System.in);
		Database database = Database.getInstance();
		
		Document d = ClientPlaceOrderHelper.placeOrderSearch(scanner);
		boolean validOrder = false;
		
		if(d != null)
		{
			int numOfCopies = -1;
			while(!validOrder)
			{
				System.out.print("\nWe have " + d.quantity + " copies left, how many would you like to order? ");
				numOfCopies = -1;
				while(numOfCopies == -1) {
					numOfCopies = scanner.nextInt();
					scanner.nextLine(); // consume '\n'
				}
				if(numOfCopies > d.quantity)
				{
					System.out.print("\nSorry we do not have that many copies available, would you like to try again? [Y/N] ");
					//scanner.nextLine();
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
				boolean purchased = makePayment(d, numOfCopies, scanner);
				if(purchased)
				{
					enterShippingInfo(scanner);
					Document a = d;
					int newQuantity = a.quantity - numOfCopies;
					if(newQuantity>0) {
						a.quantity = newQuantity;
						database.updateDocument(d, a);
					}
					else {
						database.removeDocument(d);
					}
				}
			}
		}
		else
		{
			System.out.println("Unfortunately we could not find that book, please try again");
		}
	}

	private static void enterShippingInfo(Scanner scanner)
	{
		System.out.print("\nWhere would you like this sent to?\nCity: ");
		String city = scanner.nextLine();
		System.out.print("Street: ");
		String street = scanner.nextLine();
		System.out.print("House/apartment number: ");
		String houseNumber = scanner.nextLine();	// Assumed you could have apartment '900a', so i used string instead of int
		System.out.print("Province: ");
		String province = scanner.nextLine();

		System.out.println("Your item will be shipped to " + houseNumber + " " + street + ", " + city + " " + province);
	}

	private static boolean makePayment(Document doc, int numOfCopies, Scanner scanner)
	{
			System.out.printf("The total for your order comes to $%.2f\n", doc.price*numOfCopies);
			System.out.println("Please enter your credit card number (We only accept Visa and Mastercard): ");
			String creditCard = scanner.nextLine();
			creditCard = creditCard.replace("-",  "");
			creditCard = creditCard.replace(" ",  "");
			
			if(creditCard.length() == 16)
			{
				System.out.println("Card accepted, placing order now...");
				return true;
			}
			else
			{
				System.out.println("Card not accepted, must be 16 digits. Cancelling order.");
				return false;
			}

	}


}

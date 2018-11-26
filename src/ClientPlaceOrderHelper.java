import java.util.ArrayList;
import java.util.Scanner;

public class ClientPlaceOrderHelper {
	
	
	

	static public void placeOrder()
	{
		//SearchHelper.searchInventory();
		Scanner scanner = new Scanner(System.in);
		Database database = Database.getInstance();
		
		Document d = SearchHelper.placeOrderSearch(scanner);
		boolean validOrder = false;
		
		if(d != null)
		{
			int numOfCopies = -1;
			while(!validOrder)
			{
				System.out.print("\nWe have " + d.quantity + " copies left, how many would you like to order? ");
				numOfCopies = -1;
				while(numOfCopies == -1) {
					if(scanner.hasNextInt()) numOfCopies = scanner.nextInt();
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
			System.out.println("No matching items, please try again.");
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

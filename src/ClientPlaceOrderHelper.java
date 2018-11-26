import java.util.Scanner;

public class ClientPlaceOrderHelper {

	static public void placeOrder()
	{
		SearchHelper.searchInventory();
		Scanner scanner = new Scanner(System.in);
		Database database = Database.getInstance();

		System.out.print("Please enter the title of the document you would like to order:  ");
		String title = scanner.nextLine();
		System.out.print("\nPlease enter the author of the document you would like to order: ");
		String author = scanner.nextLine();
		boolean foundDoc = false;
		for(Document d: database.getInventory())
		{
			if(d.title.toLowerCase().equals(title.toLowerCase()) && d.authorName.toLowerCase().equals(author.toLowerCase()))
			{
				foundDoc = true;
				if(d.quantity > 0)
				{
					boolean validOrder= false;
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
						boolean purchased = makePayment(d, numOfCopies, scanner);
						if(purchased)
						{
							enterShippingInfo(scanner);
							Document a = d;
							a.quantity = a.quantity - numOfCopies;
							database.updateDocument(d, a);
						}
					}
				}
				else
				{
					System.out.println("Unfortunately we are all out of our copies of " + title);
				}
			}
		}

		if(!foundDoc)
			System.out.println("Unfortunately we could not find that book, please try again");

	}

	private static void enterShippingInfo(Scanner scanner)
	{
		System.out.print("\nWhere would you like this sent to?\nCity: ");
		scanner.nextLine();
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
		while(true) {
			System.out.printf("The total for your order comes to $%.2f\n", doc.price*numOfCopies);
			System.out.println("Please enter your credit card number (We only accept Visa and Mastercard): ");
			String creditCard = scanner.nextLine();
			creditCard = creditCard.replace("-",  "");
			creditCard = creditCard.replace(" ",  "");
			int cardNum = Integer.parseInt(creditCard);

			int counter = 1;
			int sum = 0;

			for(int i = 0; i < 16; i++)
			{
				if(counter == 0)
				{
					counter = 1;

					int tmp = cardNum %10;
					tmp *= 2;
					if(tmp > 9)
					{
						tmp = (tmp%10) + (tmp/10);
					}

					sum += tmp;

				}
				else
				{
					counter = 0;
					sum += cardNum%10;
				}
				cardNum /= 10;
			}

			if(sum %10 == 0)
			{
				System.out.println("Card accepting, ordering your document now...");
				return true;
			}
			else
			{
				System.out.println("Not a valid credit card, please try again");
				//repeat the card input process
			}
		}

	}


}

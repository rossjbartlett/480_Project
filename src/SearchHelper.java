import java.util.ArrayList;
import java.util.Scanner;

public class SearchHelper {
	
	static public Document searchInventory(Scanner scanner) {
		Database database = Database.getInstance();
		//Scanner scanner = new Scanner(System.in);
		
		System.out.print("What would you like to search for?   ");
		String search = null;
		while(search == null) {
			search = scanner.nextLine();
		}
		boolean foundItem = false;
		for(Document d: database.getInventory())
		{
			if(d.title.toLowerCase().contains(search.toLowerCase()))
			{
				System.out.println("\n" + d.headerString());
				foundItem = true;
			}
			else if(d.authorName.toLowerCase().contains(search.toLowerCase()))
			{
				System.out.println("\n" + d.headerString());
				foundItem = true;
			}
			else if(d.ISBN.toLowerCase().contains(search.toLowerCase()))
			{
				System.out.println("\n" + d.headerString());
				foundItem = true;
			}
			
			if(foundItem) {
				return d;
			}
		}
		
		if(!foundItem)
		{
			System.out.println("Sorry we couldn't find the item you were looking for.");
		}
		return null;
	}
	
	static public void search(Scanner scanner)
	{
		Database database = Database.getInstance();
		ArrayList<Document> searchResults = new ArrayList<>();
		System.out.print("What would you like to search for? ");
		String search = null;
		while(search == null) {
			search = scanner.nextLine();
		}
		
		//int i = 0;
		for(Document d: database.getInventory())
		{
			if(d.title.toLowerCase().contains(search.toLowerCase()))
			{
//				System.out.println("\n[" + i + "] " + d.headerString());
				searchResults.add(d);
			}
			else if(d.authorName.toLowerCase().contains(search.toLowerCase()))
			{
//				System.out.println("\n[" + i + "] " + d.headerString());
				searchResults.add(d);
			}
			else if(d.ISBN.toLowerCase().contains(search.toLowerCase()))
			{
				searchResults.add(d);
			}
//			i++;
		}
		if(searchResults.isEmpty()) {
			//No items found
			return;
		}
		
		for(int i = 0 ; i < searchResults.size(); i++) {
			System.out.println("\n[" + i + "] " + searchResults.get(i).headerString());

		}
		
	}

	static public Document placeOrderSearch(Scanner scanner)
	{
		Database database = Database.getInstance();
		ArrayList<Document> searchResults = new ArrayList<>();
		System.out.print("What would you like to search for? ");
		String search = null;
		while(search == null) {
			search = scanner.nextLine();
		}
		
		//int i = 0;
		for(Document d: database.getInventory())
		{
			if(d.title.toLowerCase().contains(search.toLowerCase()))
			{
//				System.out.println("\n[" + i + "] " + d.headerString());
				searchResults.add(d);
			}
			else if(d.authorName.toLowerCase().contains(search.toLowerCase()))
			{
//				System.out.println("\n[" + i + "] " + d.headerString());
				searchResults.add(d);
			}
			else if(d.ISBN.toLowerCase().contains(search.toLowerCase()))
			{
				searchResults.add(d);
			}
//			i++;
		}
		if(searchResults.isEmpty()) {
			//No items found
			return null;
		}
		
		System.out.println("\nPlease select one of the following items");
		for(int i = 0 ; i < searchResults.size(); i++) {
			System.out.println("\n[" + i + "] " + searchResults.get(i).headerString());

		}
		
		int item = -1;
		if(scanner.hasNextInt())
		{
			item = scanner.nextInt();
			scanner.nextLine();
		}
		
		if(item < 0 || item >= searchResults.size())
		{
			return null;
		}
		else
		{
			return searchResults.get(item);
		}
		
	}
	
	
}

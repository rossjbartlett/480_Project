import java.util.Scanner;

public class SearchHelper {
	
	static public Document searchInventory() {
		Database database = Database.getInstance();
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("What would you like to search for?   ");
		String search = null;
		while(search == null) {
			//search = scanner.nextLine();
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

}

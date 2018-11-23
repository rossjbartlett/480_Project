import java.util.Scanner;

public class OperatorHelper {

	static public void addItem() {
		
	}

	static public void editItem() {
		
		Database database = Database.getInstance();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter the title of the document you would like to order:  ");
		String title = scanner.nextLine();
		System.out.print("\nPlease enter the author of the document you would like to order: ");
		String author = scanner.nextLine();
		for(Document d: database.getInventory())
		{
			if(d.title.toLowerCase().equals(title.toLowerCase()) && d.authorName.toLowerCase().equals(author.toLowerCase()))
			{
				
			}
		}
	}

	static public void removeItem() {
		
	}

}

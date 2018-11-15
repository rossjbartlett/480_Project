
public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Database database = new Database();
		
		//make some test users and documents
		
		OrdinaryBuyer rossOB = new OrdinaryBuyer("Ross Bartlett", "rossb1", "rjb123", "504 Maple Road", "403-321-9876");
		Operator antoineOP = new Operator("Antoine Bizon", "antoineb1", "ab123", "123 Happy Lane", "403-123-4567");
		RegisteredBuyer dylanRB = new RegisteredBuyer("Dylan Gordon", "dylang1", "dg123", "420 Green Street", "587-456-1234");

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
		

	}

}

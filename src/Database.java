import java.util.ArrayList;
import java.util.ListIterator;

public class Database {
	ArrayList<Account> accounts;
	ArrayList<Document> inventory;
	
	public Database() {
		// TODO Auto-generated constructor stub
		accounts = new ArrayList<>();
		inventory = new ArrayList<>();
	}
	
	public void addAccount(Account a) {
		accounts.add(a);
	}
	
	public void addDocument(Document a) {
		inventory.add(a);
	}
	public void removeDocument(Document a) {
		inventory.remove(a);
	}
	public void updateDocument(Document a, Document b) {
		//replace a with b
		ListIterator<Document> iterator = inventory.listIterator();
		while (iterator.hasNext()) {
		     Document next = iterator.next();
		     if (next == a) {
		         iterator.set(b);
		     }
		 }
	}
	
	public Account login(String username, String pw) {
		//checks authentication, returns the account if valid or null
		ListIterator<Account> iterator = accounts.listIterator();
		while (iterator.hasNext()) {
		     Account next = iterator.next();
		     if (next.getUsername().equals(username) && next.getPassword().equals(pw)) {
		         return next;
		     }
		 }
		return null;
	}
}

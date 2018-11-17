import java.util.ArrayList;
import java.util.ListIterator;

public class Database {
	private ArrayList<AccountHolder> accounts;
	private ArrayList<Document> inventory;
	private PromoList promoList;

	
	public Database() {
		accounts = new ArrayList<>();
		inventory = new ArrayList<>();
		promoList = new PromoList();
	}
	
	public void addAccount(AccountHolder a) {
		accounts.add(a);
	}
	public boolean removeAccount(AccountHolder a) {
		//returns true if successful remove 
		return accounts.remove(a);
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
	
	public AccountHolder login(String username, String pw) {
		//checks authentication, returns the account if valid or null
		ListIterator<AccountHolder> iterator = accounts.listIterator();
		while (iterator.hasNext()) {
			AccountHolder next = iterator.next();
		     if (next.getUsername().equals(username) && next.getPassword().equals(pw)) {
		         return next;
		     }
		 }
		return null;
	}
	
	public ArrayList<Document> getInventory() {
		return inventory;
	}
	
	public void addToPromoList(Document a) {
		promoList.addDocument(a);
	}
	
	public PromoList getPromoListObject() {
		return promoList;
	}
}

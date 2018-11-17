import java.util.ArrayList;

public class RegisteredBuyer extends AccountHolder implements Observer, Constants{

	Subject subject;
	ArrayList<Document> promolist;
	
	
	public RegisteredBuyer(String name, String username, String password, String address, String phoneNumber, Subject sub) {
		super(REGISTERED_BUYER_TYPE, name, username, password, address, phoneNumber);
		promolist = new ArrayList<>();
        subject=sub;
        subject.attach(this);
	}
	
	public void unsubscribe() {
		//account.setType(ORDINARY_BUYER_TYPE);
		subject.detach(this);
		//TODO
		//how do we actually make it an Ordinary Buyer instance now? 
	}

	@Override
	public void update(ArrayList<Document> pl) {
		promolist = pl;
		//display();
	}
}

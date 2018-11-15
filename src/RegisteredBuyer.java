
public class RegisteredBuyer extends AccountHolder implements Constants{

	public RegisteredBuyer(int type, String name, String username, String password, String address, String phoneNumber) {
		super(type, name, username, password, address, phoneNumber);
	}
	
	public void unsubscribe() {
		account.setType(ORDINARY_BUYER_TYPE);
	}
}

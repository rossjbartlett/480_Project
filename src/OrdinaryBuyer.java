
public class OrdinaryBuyer extends AccountHolder implements Constants{
	public OrdinaryBuyer(int type, String name, String username, String password, String address, String phoneNumber) {
		super(type, name, username, password, address, phoneNumber);
	}
	
	public void subscribe() {
		account.setType(REGISTERED_BUYER_TYPE);
	}
}

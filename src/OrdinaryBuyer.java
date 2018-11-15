
public class OrdinaryBuyer extends AccountHolder implements Constants{
	public OrdinaryBuyer(int type, String name, String username, String password, String address, String phoneNumber) {
		super(type, name, username, password, address, phoneNumber);
	}
	
	public void subscribe() {
		account.setType(REGISTERED_BUYER_TYPE);
		//TODO
		//how do we make it an actual registered buyer that is an observer to the promo list?
		//perhaps need to make it a Strategy that can change...
	}
}

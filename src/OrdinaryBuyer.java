
public class OrdinaryBuyer extends AccountHolder implements Constants{
	
	public OrdinaryBuyer(String name, String username, String password, String address, String phoneNumber) {
		super(ORDINARY_BUYER_TYPE, name, username, password, address, phoneNumber);
	}
	
}

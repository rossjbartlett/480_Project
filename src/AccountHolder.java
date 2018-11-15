
public abstract class AccountHolder {
	protected Account account;
	
	public AccountHolder(int type, String name, String username, String password, String address, String phoneNumber) {
		account = new Account(type, name, username, password, address, phoneNumber);
	}
	
	public Account getAccount() { return account;}
}

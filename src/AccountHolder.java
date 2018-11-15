
public abstract class AccountHolder {
	private Account account;
	
	public AccountHolder(String name, String username, String password, String address, String phoneNumber) {
		account = new Account(name, username, password, address, phoneNumber);
	}
	
	public Account getAccount() { return account;}
}

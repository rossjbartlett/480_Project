
public abstract class AccountHolder {
	protected String name; 
	protected String username;
	protected String password;
	protected String address;
	protected String phoneNumber;
	protected int accountType; // 0 for operator, 1 for ordinary buyer, 2 for registered buyer
	
	public AccountHolder(int accType, String name, String username, String password, String address, String phoneNumber) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.accountType = accType;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}
	
	public int getType() {
		return accountType;
	}
	
	public void setType(int t) {
		accountType= t;
	}
	
}

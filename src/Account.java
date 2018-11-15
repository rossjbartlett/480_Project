
public class Account {
	private String name;
	private String username;
	private String password;
	private String address;
	private String phoneNumber;
	private Integer type; // 0 for operator, 1 for ordinary buyer, 2 for registered buyer
	
	public Account(int type, String name, String username, String password, String address, String phoneNumber) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.type = type;
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
		return type;
	}
	

	
	
	
}

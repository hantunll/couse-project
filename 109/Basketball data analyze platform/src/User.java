
public class User {
	private String userID;
	private String password;
	private String identity;
	private String department;
	
	public User(String userID, String password, String identity , String department) {
		//Checking identity and department to manage the authority 
		this.userID = userID;
		this.password = password;
		this.identity = identity;
		this.department = department;
	}
	
	public String getID() {
		return userID;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getIdentity() {
		return identity;
	}

	public String getDepartment() {
		return department;
	}

}

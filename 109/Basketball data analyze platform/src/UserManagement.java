import java.sql.SQLException;

public class UserManagement {
	private ExecSQL exec;
	private User user;
	public UserManagement()throws SQLException {
		this.exec = new ExecSQL();
	}
	public boolean addUser(String userID, String password, String identity , String department) throws SQLException {
		return exec.addUser(new User(userID, password, identity , department));
	}
	
	public boolean userExist(String ID) {
		user = exec.findUser(ID);
		boolean result = false;
		if(user != null) {
			result = true;
		}
		return result;
	}
	
	public boolean passwordCorrect(String ID , String password) {
		boolean result = false;
		if(userExist(ID) && user.getPassword().equals(password)) {
			result = true;
		}
		return result;
	}
	
	public User getUser() {
		return user;
	}
}

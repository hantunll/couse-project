
public class Game {
	private String time;
	private String place;
	private String host;
	private String guest;
	
	/*
	 *  for the playergamepanel , let the createcombobox() get the department
	 *	create a new constructor
	 */
	public Game(String host) {
		this.host = host;
	}

	public Game(String time , String place , String host , String guest) {
		this.time = time;
		this.place = place;
		this.host = host;
		this.guest = guest;
	}

	public String getTime() {
		return time;
	}

	public String getPlace() {
		return place;
	}

	public String getHost() {
		return host;
	}

	public String getGuest() {
		return guest;
	}

}

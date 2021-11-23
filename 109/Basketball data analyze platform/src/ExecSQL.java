import java.sql.*;
import java.util.ArrayList;

public class ExecSQL {
	static String url;
	static String username;
	static String password;
	static Connection conn;
	public ExecSQL() {
		String server = "jdbc:mysql://140.119.19.73:9306/";
		String database = "TG16";
		this.url = server + database;
		this.username = "TG16";
		this.password = "vyQibe";
		initializing();
	}
	private void initializing() {
		try {
			this.conn = DriverManager.getConnection(url,username, password);
			createUserTable();
			createPlayerTable();
			createGameTable();
			createPlayerGameTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean findTable(String tableName) {
		boolean result = false;
		try {
			conn.createStatement();
			String findTable = " SHOW TABLES LIKE ?";
			PreparedStatement ps = conn.prepareStatement(findTable);
			ps.setString(1, tableName);
			result = ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean createUserTable() {
		int result = 1;
		try {
			conn.createStatement();
			String createTable = " CREATE TABLE IF NOT EXISTS User" 
					+ "(ID VARCHAR(12) NOT NULL,"
					+ "Password VARCHAR(10) NOT NULL,"
					+ "Identity VARCHAR(10) NOT NULL,"
					+ "Department VARCHAR(50) NOT NULL,"
					+ "PRIMARY KEY (ID))";
			PreparedStatement createTableStat = conn.prepareStatement(createTable);
			result = createTableStat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(result == 0)
			return true;
		else
			return false;
	}

	public boolean createPlayerTable() {
		int result = 1;
		try {
			conn.createStatement();
			String createTable = " CREATE TABLE IF NOT EXISTS Player" 
					+ "(ID INT(5) NOT NULL,"
					+ "Name VARCHAR(50) NOT NULL,"
					+ "Department VARCHAR(50) NOT NULL,"
					+ "Position VARCHAR(2) NOT NULL,"
					+ "Number INT(3) NOT NULL,"
					+ "PRIMARY KEY (ID))";
			PreparedStatement createTableStat = conn.prepareStatement(createTable);
			result = createTableStat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(result == 0)
			return true;
		else
			return false;
	}

	public boolean createGameTable() {
		int result = 1;
		try {
			conn.createStatement();
			String createTable = " CREATE TABLE IF NOT EXISTS Game" 
					+ "(ID INT AUTO_INCREMENT,"
					+ "Time DATE,"
					+ "Place VARCHAR(50) NOT NULL,"
					+ "Host VARCHAR(50) NOT NULL,"
					+ "Guest VARCHAR(50) NOT NULL,"
					+ "PRIMARY KEY (ID))";
			PreparedStatement createTableStat = conn.prepareStatement(createTable);
			result = createTableStat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(result == 0)
			return true;
		else
			return false;
	}

	public boolean createPlayerGameTable() {
		int result = 1;
		try {
			conn.createStatement();
			String createTable = " CREATE TABLE IF NOT EXISTS PlayerGame" 
					+ "(ID INT AUTO_INCREMENT,"
					+ "GameID INT(10) NOT NULL,"
					+ "PlayerNumber INT(10) NOT NULL,"
					+ "2PA INT(1) NOT NULL,"
					+ "2PM INT(1) NOT NULL,"
					+ "3PA INT(1) NOT NULL,"
					+ "3PM INT(1) NOT NULL,"
					+ "FairPA INT(1) NOT NULL,"
					+ "FairPM INT(1) NOT NULL,"
					+ "TotalPoint INT(5) NOT NULL,"
					+ "OffReb INT(1) NOT NULL,"
					+ "DefReb INT(1) NOT NULL,"
					+ "Assist INT(1) NOT NULL,"
					+ "Steal INT(1) NOT NULL,"
					+ "Block INT(1) NOT NULL,"
					+ "Turnover INT(1) NOT NULL,"
					+ "Foul INT(1) NOT NULL,"
					+ "TwoPointRate DOUBLE(10,2) NOT NULL,"
					+ "ThreePointRate DOUBLE(10,2) NOT NULL,"
					+ "FairRate DOUBLE(10,2) NOT NULL,"
					+ "EEF INT(5) NOT NULL,"
					+ "PRIMARY KEY (ID))";
			PreparedStatement createTableStat = conn.prepareStatement(createTable);
			result = createTableStat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(result == 0)
			return true;
		else
			return false;
	}
	//UserManagement
	public User findUser(String ID) {
		User user = null;
		try {
			conn.createStatement();
			String findUser = "SELECT * FROM User WHERE ID = ?";
			PreparedStatement findUserStat = conn.prepareStatement(findUser);
			findUserStat.setString(1,ID);
			ResultSet result1 = findUserStat.executeQuery();
			while(result1.next()) {
				String id = result1.getNString("ID");
				String password = result1.getNString("Password");
				String identity = result1.getNString("Identity");
				String department = result1.getNString("Department");
				user = new User(id , password , identity , department);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	public boolean addUser(User user) throws SQLException{
		int result;
		conn.createStatement();
		PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO User VALUES(?,?,?,?)");
		preparedStatement.setString(1, user.getID());
		preparedStatement.setString(2, user.getPassword());
		preparedStatement.setString(3 , user.getIdentity());
		preparedStatement.setString(4 , user.getDepartment());
		result = preparedStatement.executeUpdate();
		if(result == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	//PlayerManagement
	public boolean addPlayer(Player player) throws SQLException {
		int result;
		boolean results = false;
		conn.createStatement();
		PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Player VALUES(?,?,?,?,?)");
		preparedStatement.setString(2, player.getName());
		preparedStatement.setString(3, player.getDepName());
		int a = Integer.parseInt(String.valueOf(player.getDepNumber(player.getDepName())) + String.valueOf(player.getNum()));
		preparedStatement.setInt(1,a);
		preparedStatement.setString(4, player.getPosition());
		preparedStatement.setInt(5, player.getNum());
		result = preparedStatement.executeUpdate();
		if(result == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean deletPlayer(Player player) throws SQLException{
		int result;
		Statement stat;
		stat = conn.createStatement();
		String a = String.valueOf(player.getDepNumber(player.getDepName())) + String.valueOf(player.getNum());
		String query = "DELETE FROM `Player` WHERE `ID` = "+a;
		result =  stat.executeUpdate(query);
		if(result == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean modifyPlayer(Player player) throws SQLException {
		Statement stat;
		int result;
		stat = conn.createStatement();
		String query =" UPDATE `Player` SET `Number`= ?,`Position`= ? WHERE `Name` = ? AND `Department` = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setInt(1, player.getNum());
		preparedStatement.setString(2,player.getPosition());
		preparedStatement.setString(3,player.getName());
		preparedStatement.setString(4,player.getDepName());
		result = preparedStatement.executeUpdate();
		if(result == 1) {
			return true;
		}
		else {
			return false;
		}

	}

	public ArrayList<String> getPlayersID(String department) throws SQLException , NumberFormatException {
		conn.createStatement();
		ArrayList<String> playersNumberList = new ArrayList<String>();
		//SELECT `ID` FROM `Player` WHERE `Department` = 'Management Information Systems'
		String query = "SELECT `ID` FROM `Player` WHERE `Department` = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setString(1,department);

		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			int id = rs.getInt("ID");
			playersNumberList.add(Integer.toString(id));
		}
		return playersNumberList;
	}

	//GameManagement
	public boolean addGame(Game game) throws SQLException {
		Statement stat;
		int result;
		stat = conn.createStatement();
		PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Game VALUES(default,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1,game.getTime());
		preparedStatement.setString(2,game.getPlace());
		preparedStatement.setString(3,game.getHost());
		preparedStatement.setString(4,game.getGuest());
		result = preparedStatement.executeUpdate();
		ResultSet tableKeys = preparedStatement.getGeneratedKeys();
		tableKeys.next();
		if(result == 1)
			return true;
		else
			return false;
	}

	public boolean updateDatabase(PlayerGame game) throws SQLException {
		try {
			int result;
			conn.createStatement();
			int twoPoint = game.gettwoPM()*2;
			int threePoint = game.getthreePM()*3;
			int fairPoint = game.getfairPM()*1;
			int totalPoint = twoPoint + threePoint + fairPoint;
			int rebound = game.getdefReb() + game.getoffReb();
			int totalMadeCount = game.gettwoPM() + game.getthreePM() + game.getfairPM();
			int totalAttendCount = game.getTwoPA() + game.getthreePA() + game.getfairPA();
			double EEF = totalPoint + rebound + game.getassist() + game.getsteal() + game.getblock() - (totalAttendCount - totalMadeCount) + game.getturnover();
			//得分、籃板、助攻、抄截及阻攻5項數據加總，減掉投籃、罰球失手次數和失誤次數總和
			double twoPointRate, threePointRate, FairRate;
			if(game.gettwoPM()<game.getTwoPA() && (game.getTwoPA()!=0)) {
				twoPointRate = (game.gettwoPM() / game.getTwoPA())*100;
				twoPointRate = Math.round(twoPointRate*100.0)/100.0;
			}else {
				twoPointRate = 0;
			}
			if(game.getthreePM()<game.getthreePA() && (game.getthreePA()!=0)) {
				threePointRate = (game.getthreePM() / game.getthreePA())*100;
				threePointRate = Math.round(twoPointRate*100.0)/100.0;
			}else {
				threePointRate = 0;
			}
			if(game.getfairPM()<game.getfairPA() && (game.getfairPA()!=0)) {
				FairRate = (game.getfairPM() / game.getfairPA())*100;
				FairRate = Math.round(twoPointRate*100.0)/100.0;
			}else {
				FairRate = 0;
			}
			
			PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO PlayerGame VALUES(default,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1,game.getGameID());
			preparedStatement.setInt(2,game.getPlayernum());
			preparedStatement.setInt(3,game.getTwoPA());
			preparedStatement.setInt(4,game.gettwoPM());
			preparedStatement.setInt(5,game.getthreePA());
			preparedStatement.setInt(6,game.getthreePM());
			preparedStatement.setInt(7,game.getfairPA());
			preparedStatement.setInt(8,game.getfairPM());
			preparedStatement.setInt(9,totalPoint);
			preparedStatement.setInt(10,game.getoffReb());
			preparedStatement.setInt(11,game.getdefReb());
			preparedStatement.setInt(12,game.getassist());
			preparedStatement.setInt(13,game.getsteal());
			preparedStatement.setInt(14,game.getblock());
			preparedStatement.setInt(15,game.getturnover());
			preparedStatement.setInt(16,game.getfoul());
			preparedStatement.setDouble(17,twoPointRate);
			preparedStatement.setDouble(18,threePointRate);
			preparedStatement.setDouble(19,FairRate);
			preparedStatement.setDouble(20,EEF);
			

			result = preparedStatement.executeUpdate();
			ResultSet tableKeys = preparedStatement.getGeneratedKeys();
			tableKeys.next();
			if(result == 1)
				return true;
			else
				return false;
		}catch (Exception e){
			e.printStackTrace();
			System.out.print("ERROR");
			return false;
		}

	}
	/*
	 * use time host guest to find the game ID
	 */
	public int findGameID(Game game) throws SQLException {
		conn.createStatement();
		String query = "SELECT * FROM `Game` WHERE Time = ? AND Host = ? AND Guest = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setString(1,game.getTime());
		preparedStatement.setString(2,game.getHost());
		preparedStatement.setString(3,game.getGuest());
		ResultSet result1 = preparedStatement.executeQuery();
		int gameID = 0;
		while(result1.next()) {
			gameID = result1.getInt("ID");
		}
		return gameID;
	}
	public boolean findGame(Game game) throws SQLException {
		conn.createStatement();
		String query = "SELECT * FROM Game WHERE Time = ? AND Place = ? AND Host = ? AND Guest = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setString(1,game.getTime());
		preparedStatement.setString(2,game.getPlace());
		preparedStatement.setString(3,game.getHost());
		preparedStatement.setString(4,game.getGuest());
		ResultSet result1 = preparedStatement.executeQuery();
		return result1.next();
	}
	public ResultSet addSchduleGame() throws SQLException {
		ResultSet result = null;
		Statement stat = conn.createStatement();
		String query = "SELECT * FROM Game";
		boolean hasResultSet = stat.execute(query);
		if(hasResultSet){
			result = stat.getResultSet(); 
		}
		return result;
	}
	//GameDataManagement
	public ResultSet queryGame(String time, String team) throws SQLException {
		ResultSet result = null;
		conn.createStatement();
		String query = "SELECT * FROM Game WHERE Time = ? AND Host = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setString(1,time);
		preparedStatement.setString(2,team);
		boolean r = preparedStatement.execute();
		if(r)
			result = preparedStatement.getResultSet();
		return result;
	}
	public ResultSet queryGameData(String time, String team) throws SQLException{
		ResultSet queryGame = queryGame(time, team);
		ResultSet result = null;
		if(queryGame != null) {
			if(queryGame.next()) {
				conn.createStatement();
				String query = "SELECT PlayerNumber,2PA,2PM,TwoPointRate,3PA,3PM,ThreePointRate,FairPM,FairPA,FairRate,OffReb,DefReb,Assist,Steal,Block,Turnover,Foul,EEF "
						+ "FROM PlayerGame WHERE GameID = ?";
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				preparedStatement.setInt(1,queryGame.getInt("ID"));
				boolean r = preparedStatement.execute();
				if(r)
					result = preparedStatement.getResultSet();
			}
		}
		return result;
	}
	public ResultSet queryPlayer(String team , int number) throws SQLException {
		ResultSet result = null;
		conn.createStatement();
		String query = "SELECT * FROM Player WHERE Department = ? AND Number = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setString(1,team);
		preparedStatement.setInt(2,number);
		boolean r = preparedStatement.execute();
		if(r)
			result = preparedStatement.getResultSet();
		return result;
	}
	public ResultSet queryPlayerData(String team , int number) throws SQLException{
		ResultSet queryPlayer = queryPlayer(team , number);
		ResultSet result = null;
		if(queryPlayer != null) {
			if(queryPlayer.next()) {
				conn.createStatement();
				String query = "SELECT GameID,2PA,2PM,TwoPointRate,3PA,3PM,ThreePointRate,FairPM,FairPA,FairRate,OffReb,DefReb,Assist,Steal,Block,Turnover,Foul,EEF "
						+ "FROM PlayerGame WHERE PlayerNumber = ?";
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				preparedStatement.setInt(1,queryPlayer.getInt("ID"));
				boolean r = preparedStatement.execute();
				if(r)
					result = preparedStatement.getResultSet();
			}
		}
		return result;
	}
	public int findPlayerID(String team , int number) throws SQLException {
		conn.createStatement();
		String query = "SELECT ID FROM Player WHERE Department = ? AND Number = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setString(1,team);
		preparedStatement.setInt(2,number);
		ResultSet result1 = preparedStatement.executeQuery();
		int playerID = 0;
		while(result1.next()) {
			playerID = result1.getInt("ID");
		}
		return playerID;
	}
	public int findGameID(String time, String team) throws SQLException {
		conn.createStatement();
		String query = "SELECT ID FROM Game WHERE Time = ? AND Host = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setString(1,time);
		preparedStatement.setString(2,team);
		ResultSet result1 = preparedStatement.executeQuery();
		int gameID = 0;
		while(result1.next()) {
			gameID = result1.getInt("ID");
		}
		return gameID;
	}
	public ResultSet queryPlayerInGameData(String team, String time ,int number) throws SQLException {
		int gameID = findGameID(time, team);
		int playerNumber = findPlayerID(team, number);
		conn.createStatement();
		String query = "SELECT PlayerNumber,2PA,2PM,TwoPointRate,3PA,3PM,ThreePointRate,FairPM,FairPA,FairRate,OffReb,DefReb,Assist,Steal,Block,Turnover,Foul,EEF FROM PlayerGame WHERE GameID LIKE ? AND PlayerNumber LIKE ?";
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setInt(1,gameID);
		preparedStatement.setInt(2,playerNumber);
		ResultSet result1 = preparedStatement.executeQuery();
		return result1;
	}
	public String findPlayerName(String team,int number) throws SQLException {
		conn.createStatement();

		String query = "SELECT Name FROM Player WHERE Department = ? AND Number = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setString(1,team);
		preparedStatement.setInt(2,number);
		ResultSet result1 = preparedStatement.executeQuery();
		String name = "";
		while(result1.next()) {
			name = result1.getString("Name");
		}
		return name;
	}
	public String findPlayerDepartment(String team,int number) throws SQLException {
		conn.createStatement();
		String query = "SELECT Department FROM Player WHERE Department = ? AND Number = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setString(1,team);
		preparedStatement.setInt(2,number);
		ResultSet result1 = preparedStatement.executeQuery();
		String department = "";
		while(result1.next()) {
			department = result1.getString("Department");
		}
		return department;
	}
	public String findTimeInGame(String team,String time) throws SQLException {
		conn.createStatement();
		String query = "SELECT Time FROM Game WHERE Host = ? AND Time = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setString(1,team);
		preparedStatement.setString(2,time);
		ResultSet result1 = preparedStatement.executeQuery();
		String date = "";
		while(result1.next()) {
			date = result1.getString("Time");
		}
		return date;
	}
	public String findGamePlace(String team,String time) throws SQLException {
		conn.createStatement();
		String query = "SELECT Place FROM Game WHERE Host = ? AND Time = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setString(1,team);
		preparedStatement.setString(2,time);
		ResultSet result1 = preparedStatement.executeQuery();
		String place = "";
		while(result1.next()) {
			place = result1.getString("Place");
		}
		return place;
	}
	public String findGameHost(String team,String time) throws SQLException {
		conn.createStatement();
		String query = "SELECT Host FROM Game WHERE Host = ? AND Time = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setString(1,team);
		preparedStatement.setString(2,time);
		ResultSet result1 = preparedStatement.executeQuery();
		String host = "";
		while(result1.next()) {
			host = result1.getString("Host");
		}
		return host;
	}
	public String findGameGuest(String team,String time) throws SQLException {
		conn.createStatement();
		String query = "SELECT Guest FROM Game WHERE Host = ? AND Time = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setString(1,team);
		preparedStatement.setString(2,time);
		ResultSet result1 = preparedStatement.executeQuery();
		String guest = "";
		while(result1.next()) {
			guest = result1.getString("Guest");
		}
		return guest;
	}

}
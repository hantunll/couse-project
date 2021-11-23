import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GameManagement {
	private Game game;
	private PlayerGame playerGame;
	private ExecSQL gameSQL;
	
	public GameManagement() throws SQLException {
		gameSQL = new ExecSQL();
	}
	public boolean addGame(Game game) throws SQLException {
		return gameSQL.addGame(game);
	}
	public boolean findGame(Game game) throws SQLException {
		return gameSQL.findGame(game);
	}
	
	// 
	public boolean updateDatabase
	(
			int gameID , int playerNumber , int twoPA , int twoPM , int threePA ,
			int threePM , int fairPA , int fairPM , int offReb , int defReb , int assist , 
			int steal , int block , int turnover , int foul
	) 
			throws SQLException 
	{
			playerGame = new PlayerGame(gameID , playerNumber , twoPA , twoPM , threePA ,
					 threePM , fairPA , fairPM , offReb , defReb , assist ,  steal ,
					 block ,  turnover ,  foul);
			return gameSQL.updateDatabase(playerGame);
	}
	/*
	 * find a game ID
	 */
	public int findGameID(String time , String place , String host , String guest) throws SQLException {
		game = new Game(time , place , host , guest);
		return gameSQL.findGameID(game);
	}
	public ArrayList<Game> getScheduleGame() throws SQLException{
		ArrayList<Game> games = new ArrayList<Game>();
		ResultSet result = gameSQL.addSchduleGame();
		while(result.next()) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String time = dateFormat.format(result.getDate(2));  
			String place = result.getString(3);
			String host = result.getString(4);
			String guest = result.getString(5);
			games.add(new Game(time, place , host , guest));
		}
		return games;
		
	}
	

}

import java.sql.*;
 
public class GameDataManagement {
	private ResultSet tableData;
	private ExecSQL querySQL;
	
	public GameDataManagement() {
		querySQL = new ExecSQL();
	}
	public boolean queryPlayer(String team,int number) throws SQLException{
		tableData = querySQL.queryPlayerData(team, number);
		if(tableData != null)
			return true;
		else
			return false;
	}
	public boolean queryGame(String team,String time) throws SQLException{
		tableData = querySQL.queryGameData(time, team);
		if(tableData != null)
			return true;
		else
			return false;
	}
	public boolean queryPlayerInGameData(String team, String time ,int number) throws SQLException{
		tableData = querySQL.queryPlayerInGameData(team, time, number);
		return true;
	}
	public ResultSet getResult() {
		return tableData;
	}
	
}

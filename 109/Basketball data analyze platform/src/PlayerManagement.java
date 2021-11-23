import java.util.ArrayList;
import java.sql.*;


public class PlayerManagement {
	private ExecSQL pmSQL;
	
	public PlayerManagement() throws SQLException {
		pmSQL = new ExecSQL();
	}

	public boolean addPlayer(String name, int num, String position, String depName) throws SQLException {
//		depName = SchedulePanel.depNametoLong(depName);
		return pmSQL.addPlayer(new Player(name,depName,position,num));
	}
	
	public boolean deletePlayer(String name, int num, String position, String depName) throws SQLException{
//		depName = SchedulePanel.depNametoLong(depName);
		return pmSQL.deletPlayer(new Player(name,depName,position,num));
	}
	
	public boolean modifyPlayer(String name, int num, String position, String depName) throws SQLException{
//		depName = SchedulePanel.depNametoLong(depName);
		return pmSQL.modifyPlayer(new Player(name,depName,position,num));
	}
	
	public ArrayList<String> getPlayerNum(String department) throws SQLException{
		return pmSQL.getPlayersID(department);
	}
	
}


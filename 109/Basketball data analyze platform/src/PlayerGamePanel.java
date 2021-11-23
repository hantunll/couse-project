import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JComboBox;

public class PlayerGamePanel extends JPanel{
	static final GridLayout LAYOUT = new GridLayout(1,14);
	
	private PlayerManagement player;
	private String department;
	private JComboBox playerCombo;
	private JPanel twoPM;
	private JPanel twoPA;
	private JPanel threePM;
	private JPanel threePA;
	private JPanel fairPM;
	private JPanel fairPA;
	private JPanel offReb;
	private JPanel defReb;
	private JPanel assist;
	private JPanel steal;
	private JPanel block;
	private JPanel turnover;
	private JPanel foul;
	
	//storage the label value of each button which is sequently e.g. 2PA,2PM...
	private  ArrayList <Integer> labelValue;
	
	public PlayerGamePanel(String department) throws SQLException {
		playerCombo = new JComboBox();
		
		this.department = department;
		setLayout(LAYOUT);
		
		createScorePanel();

		createCombox();
		add(playerCombo);
		
		JPanel[] panels = {twoPA , twoPM ,   threePA ,threePM , fairPA ,  fairPM , offReb , defReb , assist , steal , block , turnover , foul};
		for(int i = 0 ; i < panels.length ; i++) {
			add(panels[i]);
		}
		
	}
	public int getScoreLabelValue(ScorePanel panel) {
		return Integer.parseInt( panel.getScoreValue() );
	}
	
	public ArrayList <Integer> getValue(){
		JPanel[] panels = {twoPA , twoPM ,   threePA ,threePM , fairPA ,  fairPM , offReb , defReb , assist , steal , block , turnover , foul};
		labelValue = new ArrayList <Integer>();
		for(int i = 0 ; i < panels.length ; i++) {
			labelValue.add(getScoreLabelValue( (ScorePanel) (panels[i]) ));
		}
		return labelValue ;
	}
	
	public String getPlayerNum(String department) {
		String playerNum = (String) playerCombo.getSelectedItem();
		int depNum = Player.getDepNumber(department);
		if(!playerNum.equals("null")) {
			playerNum = String.valueOf(depNum)+playerNum;
		}
		return playerNum;
	}
	
	public void createScorePanel() {
		twoPA = new ScorePanel();
		twoPM = new ScorePanel();
		threePA = new ScorePanel();
		threePM = new ScorePanel();
		fairPA = new ScorePanel();
		fairPM = new ScorePanel();
		offReb = new ScorePanel();
		defReb = new ScorePanel();
		assist = new ScorePanel();
		steal = new ScorePanel();
		block = new ScorePanel();
		turnover = new ScorePanel();
		foul = new ScorePanel();
		
	}
	
	public void createCombox() throws SQLException {
		player = new PlayerManagement();
		playerCombo.setPreferredSize(new Dimension(70,30));
		ArrayList<String> playerNumber = new ArrayList<String>();
		playerNumber = player.getPlayerNum(GamePanel.host); // is a string arraylist
		playerCombo.addItem("null");
		for(int i=0 ; i < playerNumber.size() ; i++) {
			playerCombo.addItem(playerNumber.get(i).substring(3));
		}
		
		
	}
	
}

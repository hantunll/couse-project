import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GameEventFrame extends JFrame{
	
	private Dimension compSize = new Dimension(30,20);
	private Dimension panelSize = new Dimension(1000,20);
	private Font font16 = new Font("Avenir Next", Font.PLAIN, 16);
	
	private JButton submitButton; //new
	private JPanel panel;
	private String host;
	private JPanel dataPanel;
	private JLabel lblGameID , txtGameID , lblTime , txtTime , lblPlace , txtPlace , lblHost , txtHost , lblGuest , txtGuest , lblTotalPoint , txtTotalPoint;
	private String department;
	private JPanel gameRecordPanel;
		private JPanel titlePanel;
		private JPanel player1Panel;
		private JPanel player2Panel;
		private JPanel player3Panel;
		private JPanel player4Panel;
		private JPanel player5Panel;
		private JPanel player6Panel;
		private JPanel player7Panel;
		private JPanel player8Panel;
		private JPanel player9Panel;
		private JPanel player10Panel;
	
	/*
	 * An arrayList to storage a player's performance data
	 */
	private ArrayList <Integer> player1LabelValue = new ArrayList <Integer>();
	private ArrayList <Integer> player2LabelValue = new ArrayList <Integer>();
	private ArrayList <Integer> player3LabelValue = new ArrayList <Integer>();
	private ArrayList <Integer> player4LabelValue = new ArrayList <Integer>();
	private ArrayList <Integer> player5LabelValue = new ArrayList <Integer>();
	private ArrayList <Integer> player6LabelValue = new ArrayList <Integer>();
	private ArrayList <Integer> player7LabelValue = new ArrayList <Integer>();
	private ArrayList <Integer> player8LabelValue = new ArrayList <Integer>();
	private ArrayList <Integer> player9LabelValue = new ArrayList <Integer>();
	private ArrayList <Integer> player10LabelValue = new ArrayList <Integer>();
		
	private int player1number,player2number,player3number,player4number,player5number,
	player6number,player7number,player8number,player9number,player10number;
	
	/*
	 * use GameManagement class to use SQL
	 */
	private GameManagement recordGameManagement;
		
	
	public GameEventFrame() throws SQLException {
		recordGameManagement = new GameManagement();
				
		
		setTitle("New Game");
		setVisible(true);
		setSize(1000, 500);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
		
		createDataPanel();
		panel.add(dataPanel);
		createSubmitButton(recordGameManagement);  // create submit button
		panel.add(submitButton);// add submit button to panel
		add(panel);
		
		player1Panel = new PlayerGamePanel(host);
		player2Panel = new PlayerGamePanel(department);
		player3Panel = new PlayerGamePanel(department);
		player4Panel = new PlayerGamePanel(department);
		player5Panel = new PlayerGamePanel(department);
		player6Panel = new PlayerGamePanel(department);
		player7Panel = new PlayerGamePanel(department);
		player8Panel = new PlayerGamePanel(department);
		player9Panel = new PlayerGamePanel(department);
		player10Panel = new PlayerGamePanel(department);
		
		createGameRecordPanel();
		add(gameRecordPanel);
	}
	
	//one arraylist storage one players data
	public ArrayList <Integer> getPlayer1LabelValue() throws NumberFormatException{
		player1LabelValue = ((PlayerGamePanel) player1Panel).getValue();
		String playerNumGetText = ((PlayerGamePanel) player1Panel).getPlayerNum(getHost().getText());
		if(playerNumGetText.equals("null")) {
			player1LabelValue.add(1000); // if the player number didnt input,add an integer to the last of the arraylist
		}else {
			player1number = Integer.valueOf(((PlayerGamePanel) player1Panel).getPlayerNum(getHost().getText()));
			player1LabelValue.add(player1number);  // add the player number to the end of the arraylist ( get(14) )
		}
		return player1LabelValue;
	}
	
	public ArrayList <Integer> getPlayer2LabelValue() {
		player2LabelValue = ((PlayerGamePanel) player2Panel).getValue();
		String playerNumGetText = ((PlayerGamePanel) player2Panel).getPlayerNum(getHost().getText());
		if(playerNumGetText.equals("null")) {
			player2LabelValue.add(1000); // if the player number didnt input,add an integer to the last of the arraylist
		}else {
			player2number = Integer.valueOf(((PlayerGamePanel) player2Panel).getPlayerNum(getHost().getText()));
			player2LabelValue.add(player2number);  
		}
		return player2LabelValue;
	}
	
	public ArrayList <Integer> getPlayer3LabelValue() {
		player3LabelValue = ((PlayerGamePanel) player3Panel).getValue();
		String playerNumGetText = ((PlayerGamePanel) player3Panel).getPlayerNum(getHost().getText());
		if(playerNumGetText.equals("null")) {
			player3LabelValue.add(1000); // if the player number didnt input,add an integer to the last of the arraylist
		}else {
			player3number = Integer.valueOf(((PlayerGamePanel) player3Panel).getPlayerNum(getHost().getText()));
			player3LabelValue.add(player3number);  
		}
		return player3LabelValue;
	}
	
	public ArrayList <Integer> getPlayer4LabelValue() {
		player4LabelValue = ((PlayerGamePanel) player4Panel).getValue();
		String playerNumGetText = ((PlayerGamePanel) player4Panel).getPlayerNum(getHost().getText());
		if(playerNumGetText.equals("null")) {
			player4LabelValue.add(1000); // if the player number didnt input,add an integer to the last of the arraylist
		}else {
			player4number = Integer.valueOf(((PlayerGamePanel) player4Panel).getPlayerNum(getHost().getText()));
			player4LabelValue.add(player4number);  
		}
		return player4LabelValue;
	}
	
	public ArrayList <Integer> getPlayer5LabelValue() {
		player5LabelValue = ((PlayerGamePanel) player5Panel).getValue();
		String playerNumGetText = ((PlayerGamePanel) player5Panel).getPlayerNum(getHost().getText());
		if(playerNumGetText.equals("null")) {
			player5LabelValue.add(1000); // if the player number didnt input,add an integer to the last of the arraylist
		}else {
			player5number = Integer.valueOf(((PlayerGamePanel) player5Panel).getPlayerNum(getHost().getText()));
			player5LabelValue.add(player5number);  
		}
		return player5LabelValue;
	}
	
	public ArrayList <Integer> getPlayer6LabelValue() {
		player6LabelValue = ((PlayerGamePanel) player6Panel).getValue();
		String playerNumGetText = ((PlayerGamePanel) player6Panel).getPlayerNum(getHost().getText());
		if(playerNumGetText.equals("null")) {
			player6LabelValue.add(1000); // if the player number didnt input,add an integer to the last of the arraylist
		}else {
			player6number = Integer.valueOf(((PlayerGamePanel) player6Panel).getPlayerNum(getHost().getText()));
			player6LabelValue.add(player6number);  
		}
		return player6LabelValue;
	}
	
	public ArrayList <Integer> getPlayer7LabelValue() {
		player7LabelValue = ((PlayerGamePanel) player7Panel).getValue();
		String playerNumGetText = ((PlayerGamePanel) player7Panel).getPlayerNum(getHost().getText());
		if(playerNumGetText.equals("null")) {
			player7LabelValue.add(1000); // if the player number didnt input,add an integer to the last of the arraylist
		}else {
			player7number = Integer.valueOf(((PlayerGamePanel) player7Panel).getPlayerNum(getHost().getText()));
			player7LabelValue.add(player7number);  
		}
		return player7LabelValue;
	}
	
	public ArrayList <Integer> getPlayer8LabelValue() {
		player8LabelValue = ((PlayerGamePanel) player8Panel).getValue();
		String playerNumGetText = ((PlayerGamePanel) player8Panel).getPlayerNum(getHost().getText());
		if(playerNumGetText.equals("null")) {
			player8LabelValue.add(1000); // if the player number didnt input,add an integer to the last of the arraylist
		}else {
			player8number = Integer.valueOf(((PlayerGamePanel) player8Panel).getPlayerNum(getHost().getText()));
			player8LabelValue.add(player8number);  
		}
		return player8LabelValue;
	}
	
	public ArrayList <Integer> getPlayer9LabelValue() {
		player9LabelValue = ((PlayerGamePanel) player9Panel).getValue();
		String playerNumGetText = ((PlayerGamePanel) player9Panel).getPlayerNum(getHost().getText());
		if(playerNumGetText.equals("null")) {
			player9LabelValue.add(1000); // if the player number didnt input,add an integer to the last of the arraylist
		}else {
			player9number = Integer.valueOf(((PlayerGamePanel) player9Panel).getPlayerNum(getHost().getText()));
			player9LabelValue.add(player9number);  
		}
		return player9LabelValue;
	}
	
	public ArrayList <Integer> getPlayer10LabelValue() {
		player10LabelValue = ((PlayerGamePanel) player10Panel).getValue();
		String playerNumGetText = ((PlayerGamePanel) player10Panel).getPlayerNum(getHost().getText());
		if(playerNumGetText.equals("null")) {
			player10LabelValue.add(1000); // if the player number didnt input,add an integer to the last of the arraylist
		}else {
			player10number = Integer.valueOf(((PlayerGamePanel) player10Panel).getPlayerNum(getHost().getText()));
			player10LabelValue.add(player10number); 
		}
		return player10LabelValue;
	}
	
	
	
	public void createSubmitButton(GameManagement recordGameManagement) {
		submitButton = new JButton("Submit");
		
		class ClickListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(null, 
				        "Are you finish and do you want to submit the result?", "Submit Message Box",
				        JOptionPane.YES_NO_OPTION);

				    if (confirmed == JOptionPane.YES_OPTION) {
				    	try {
							getPlayer1LabelValue();
							getPlayer2LabelValue();
							getPlayer3LabelValue();
							getPlayer4LabelValue();
							getPlayer5LabelValue();
							getPlayer6LabelValue();
							getPlayer7LabelValue();
							getPlayer8LabelValue();
							getPlayer9LabelValue();
							getPlayer10LabelValue();
							
						
							
							int gameID = recordGameManagement.findGameID(getTime().getText(), getPlace().getText(), getHost().getText(), getGuest().getText());
							
							//player1LabelValue.get(13) is the player number
							if(player1LabelValue.get(13) != 1000) {
								System.out.print(player1LabelValue.get(13));
								recordGameManagement.updateDatabase(gameID,player1LabelValue.get(13), player1LabelValue.get(0), player1LabelValue.get(1), player1LabelValue.get(2), 
										player1LabelValue.get(3), player1LabelValue.get(4), player1LabelValue.get(5), player1LabelValue.get(6), player1LabelValue.get(7),
										player1LabelValue.get(8), player1LabelValue.get(9), player1LabelValue.get(10), player1LabelValue.get(11),player1LabelValue.get(12));
							}else {
								System.out.print("");
							}
							if(player2LabelValue.get(13) != 1000) {
								recordGameManagement.updateDatabase(gameID,player2LabelValue.get(13), player2LabelValue.get(0), player2LabelValue.get(1), player2LabelValue.get(2), 
										player2LabelValue.get(3), player2LabelValue.get(4), player2LabelValue.get(5), player2LabelValue.get(6), player2LabelValue.get(7),
										player2LabelValue.get(8), player2LabelValue.get(9), player2LabelValue.get(10), player2LabelValue.get(11),player2LabelValue.get(12));
							}else {
								System.out.print("");
							}
							if(player3LabelValue.get(13) != 1000) {
								recordGameManagement.updateDatabase(gameID,player3LabelValue.get(13), player3LabelValue.get(0), player3LabelValue.get(1), player3LabelValue.get(2), 
										player3LabelValue.get(3), player3LabelValue.get(4), player3LabelValue.get(5), player3LabelValue.get(6), player3LabelValue.get(7),
										player3LabelValue.get(8), player3LabelValue.get(9), player3LabelValue.get(10), player3LabelValue.get(11),player3LabelValue.get(12));
							}else {
								System.out.print("");
							}
							if(player4LabelValue.get(13) != 1000) {
								recordGameManagement.updateDatabase(gameID,player4LabelValue.get(13), player4LabelValue.get(0), player4LabelValue.get(1), player4LabelValue.get(2), 
										player4LabelValue.get(3), player4LabelValue.get(4), player4LabelValue.get(5), player4LabelValue.get(6), player4LabelValue.get(7),
										player4LabelValue.get(8), player4LabelValue.get(9), player4LabelValue.get(10), player4LabelValue.get(11),player4LabelValue.get(12));
							}else {
								System.out.print("");
							}
							if(player5LabelValue.get(13) != 1000) {
								recordGameManagement.updateDatabase(gameID,player5LabelValue.get(13), player5LabelValue.get(0), player5LabelValue.get(1), player5LabelValue.get(2), 
										player5LabelValue.get(3), player5LabelValue.get(4), player5LabelValue.get(5), player5LabelValue.get(6), player5LabelValue.get(7),
										player5LabelValue.get(8), player5LabelValue.get(9), player5LabelValue.get(10), player5LabelValue.get(11),player5LabelValue.get(12));
							}else {
								System.out.print("");
							}
							if(player6LabelValue.get(13) != 1000) {
								recordGameManagement.updateDatabase(gameID,player6LabelValue.get(13), player6LabelValue.get(0), player6LabelValue.get(1), player6LabelValue.get(2), 
										player6LabelValue.get(3), player6LabelValue.get(4), player6LabelValue.get(5), player6LabelValue.get(6), player6LabelValue.get(7),
										player6LabelValue.get(8), player6LabelValue.get(9), player6LabelValue.get(10), player6LabelValue.get(11),player6LabelValue.get(12));
							}else {
								System.out.print("");
							}
							if(player7LabelValue.get(13) != 1000) {
								recordGameManagement.updateDatabase(gameID,player7LabelValue.get(13), player7LabelValue.get(0), player7LabelValue.get(1), player7LabelValue.get(2), 
										player7LabelValue.get(3), player7LabelValue.get(4), player7LabelValue.get(5), player7LabelValue.get(6), player7LabelValue.get(7),
										player7LabelValue.get(8), player7LabelValue.get(9), player7LabelValue.get(10), player7LabelValue.get(11),player7LabelValue.get(12));
							}else {
								System.out.print("");
							}
							if(player8LabelValue.get(13) != 1000) {
								recordGameManagement.updateDatabase(gameID,player8LabelValue.get(13), player8LabelValue.get(0), player8LabelValue.get(1), player8LabelValue.get(2), 
										player8LabelValue.get(3), player8LabelValue.get(4), player8LabelValue.get(5), player8LabelValue.get(6), player8LabelValue.get(7),
										player8LabelValue.get(8), player8LabelValue.get(9), player8LabelValue.get(10), player8LabelValue.get(11),player8LabelValue.get(12));
							}else {
								System.out.print("");
							}
							if(player9LabelValue.get(13) != 1000) {
								recordGameManagement.updateDatabase(gameID,player9LabelValue.get(13), player9LabelValue.get(0), player9LabelValue.get(1), player9LabelValue.get(2), 
										player9LabelValue.get(3), player9LabelValue.get(4), player9LabelValue.get(5), player9LabelValue.get(6), player9LabelValue.get(7),
										player9LabelValue.get(8), player9LabelValue.get(9), player9LabelValue.get(10), player9LabelValue.get(11),player9LabelValue.get(12));
							}else {
								System.out.print("");
							}
							if(player10LabelValue.get(13) != 1000) {
								recordGameManagement.updateDatabase(gameID,player10LabelValue.get(13), player10LabelValue.get(0), player10LabelValue.get(1), player10LabelValue.get(2), 
										player10LabelValue.get(3), player10LabelValue.get(4), player10LabelValue.get(5), player10LabelValue.get(6), player10LabelValue.get(7),
										player10LabelValue.get(8), player10LabelValue.get(9), player10LabelValue.get(10), player10LabelValue.get(11),player10LabelValue.get(12));
							}else {
								System.out.print("");
							}

							
						}catch (SQLException e1) {
							e1.printStackTrace();
						}
				    }
			}
			
		}
		ActionListener listener = new ClickListener();
		submitButton.addActionListener(listener);
		
	}
	
	public void createDataPanel() {
		GridBagLayout layout = new GridBagLayout();
		dataPanel = new JPanel();
		dataPanel.setLayout(layout);
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,5,5);
		
		c.gridx = 0;
		c.gridy = 0;
		lblGameID = new JLabel("GameID");
		dataPanel.add(lblGameID,c);
		
		c.gridx++;
		txtGameID = new JLabel("");
		dataPanel.add(txtGameID,c);
		
		c.gridx++;
		lblTime = new JLabel("Time");
		dataPanel.add(lblTime,c);
		
		c.gridx++;
		txtTime = new JLabel("");
		dataPanel.add(txtTime,c);
		
		c.gridx++;
		lblPlace = new JLabel("Place");
		dataPanel.add(lblPlace,c);
		
		c.gridx++;
		txtPlace = new JLabel("");
		dataPanel.add(txtPlace,c);
		
		c.gridx++;
		lblHost = new JLabel("Host");
		dataPanel.add(lblHost,c);
		
		c.gridx++;
		txtHost = new JLabel("");
		dataPanel.add(txtHost,c);
		
		c.gridx++;
		lblGuest = new JLabel("Guest");
		dataPanel.add(lblGuest,c);
		
		c.gridx++;
		txtGuest = new JLabel("");
		dataPanel.add(txtGuest,c);
		
		c.gridx++;
		lblTotalPoint = new JLabel("Total Point");
		dataPanel.add(lblTotalPoint,c);
		
		c.gridx++;
		txtTotalPoint = new JLabel("");
		dataPanel.add(txtTotalPoint,c);
		
		setDataPanelGUI();
		department = txtHost.getText();
		
		
	}
	public void setDataPanelGUI() {
		JLabel[] labels = {lblGameID , lblTime , lblPlace , lblHost , lblGuest , lblTotalPoint};
		JLabel[] txt = {txtGameID , txtTime , txtPlace , txtHost , txtGuest , txtTotalPoint};
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		
		for(int i = 0 ; i < labels.length ; i++) {
			labels[i].setFont(font16);
			txt[i].setFont(font16);
			txt[i].setBorder(border);
		}
	}
	
	public void createColumnTitlePanel() {
		titlePanel = new JPanel();
		titlePanel.setLayout(new GridLayout(1,14));
		String[] title = {"Player","2PA","2PM","3PA","3PM","FA","FM","OffReb","DefReb","Assist","Steal","Block","Turnover","Foul"};
		for(int i = 0 ; i< title.length ; i++) {
			JLabel label = new JLabel(title[i]);
			label.setPreferredSize(compSize);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setFont(font16);
			titlePanel.add(label);
		}
		gameRecordPanel.add(titlePanel);
	}
	

	public void createGameRecordPanel() throws SQLException {
		gameRecordPanel = new JPanel();
		gameRecordPanel.setLayout(new GridLayout(11,1));
		
		titlePanel = new JPanel();
		titlePanel.setPreferredSize(panelSize);
		createColumnTitlePanel();
		
		gameRecordPanel.add(titlePanel);
		gameRecordPanel.add(player1Panel);
		gameRecordPanel.add(player2Panel);
		gameRecordPanel.add(player3Panel);
		gameRecordPanel.add(player4Panel);
		gameRecordPanel.add(player5Panel);
		gameRecordPanel.add(player6Panel);
		gameRecordPanel.add(player7Panel);
		gameRecordPanel.add(player8Panel);
		gameRecordPanel.add(player9Panel);
		gameRecordPanel.add(player10Panel);
		
		
	}
	
	public JLabel getTime() {
		return txtTime;
	}

	public JLabel getPlace() {
		return txtPlace;
	}

	public JLabel getHost() {
		return txtHost;
	}

	public JLabel getGuest() {
		return txtGuest;
	}
	
	public JLabel getGameID() {
		return txtGameID;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	
}

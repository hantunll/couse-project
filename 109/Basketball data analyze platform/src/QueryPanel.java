import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class QueryPanel extends JPanel {

	//Input Panel
	private JPanel inputPanel;
	private GameDataManagement gameDataManagement;
	private JLabel lblTeam , lblTime ,lblPlayerNum;	
	private JComboBox<String> teamCombo , functionCombo;
	private JTextField txtTime , txtPlayerNum;
	private JButton submitButton;

	//OutputPanel
	private JPanel outputPanel;
	private CardLayout outputPanelLayout;
	private JPanel gamePanel , playerPanel , playerInGamePanel;
	private JScrollPane playerScrollPane , gameScrollPane , playerInGameScrollPane;
	//query table
	private JTable table1 , table2 , table3;
	private DefaultTableModel model1 , model2 , model3;  

	//query data
	private JLabel playerName, playerTeam;
	private JLabel gameTime , gamePlace , gameHost , gameGuest;
	private JLabel pgPlayer , pgTeam, pgGuest ,pgTime , pgPlace;
	private Insets insets = new Insets(5,5,5,5);
	private ExecSQL querySQL;
	private JPanel instructionPanel;
	private JLabel titleLabel;
	private JTextArea instructionTxt;

	public QueryPanel() {
		querySQL = new ExecSQL();
		gameDataManagement = new GameDataManagement();
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		createInputPanel();
		initPlayerPanel();
		initGamePanel();
		initPlayerInGamePanel();
		createOutputPanel();
		c.gridx = 0;	c.gridy = 0;	c.insets = new Insets(20,30,20,30);
		c.fill = GridBagConstraints.BOTH;
		add(inputPanel, c);
		c.gridx = 1;
		c.weightx = 1;
		add(outputPanel,c );
	}
	public void createInputPanel() {
		inputPanel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		inputPanel.setLayout(layout);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10,0,10,0);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		createInstructionPanel();
		inputPanel.add(instructionPanel , c);

		functionCombo = new JComboBox<String>();
		functionCombo.addItem("Game");
		functionCombo.addItem("Player");
		functionCombo.addItem("Player In Game");
		c.gridx = 1;
		c.gridy = 1;
		inputPanel.add(functionCombo , c);

		lblTime = new JLabel("Time: ");
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		inputPanel.add(lblTime,c);
		
		lblTeam = new JLabel("Team: ");
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		inputPanel.add(lblTeam,c);

		lblPlayerNum = new JLabel("Number: ");
		c.gridx = 1;
		c.gridy = 4;
		inputPanel.add(lblPlayerNum , c);

		txtTime = new JTextField(" ");
		c.gridx = 2;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		inputPanel.add(txtTime , c);

		teamCombo = new JComboBox();
		PlayerManagementPanel.createDepComboBox(teamCombo);
		c.gridx = 2;
		c.gridy = 3;
		inputPanel.add(teamCombo , c);
		
		txtPlayerNum = new JTextField(" ");
		c.gridx = 2;
		c.gridy = 4;
		inputPanel.add(txtPlayerNum , c);

		createSubmitBtn(gameDataManagement);
		c.gridx = 2;
		c.gridy = 5;
		c.gridwidth = 2;
		inputPanel.add(submitButton,c);
		
		setInputPanelGUI();
	}
	public void createInstructionPanel(){
		String word = "Here is where you can find all data\n"
					+ "(1)Game: Input 'Time' and 'Team' to find all records in the game.\n"
					+ "(2)Player: Input 'Team' and 'Number' to find all record of this player.\n"
					+ "(3)Player In Game: Input ALL 3 FIELDS to find specific player in the game.\n"
					+ "* The time format should be yyyy-MM-dd";

		instructionPanel = new JPanel();
		instructionPanel.setLayout(new BorderLayout(20,20));
		titleLabel = new JLabel("Query");
		instructionTxt = new JTextArea(word);

		instructionPanel.add(titleLabel,BorderLayout.WEST);
		instructionPanel.add(instructionTxt,BorderLayout.CENTER);
	}
	public void setInputPanelGUI() {
		String font = "Avenir Next";
		Font font14 = new Font(font, Font.PLAIN, 14);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		titleLabel.setFont(new Font(font, Font.BOLD, 14));
		JLabel[] lbl= {lblTime , lblTeam ,lblPlayerNum};
		for(int i = 0 ; i < lbl.length ; i++)
			lbl[i].setFont(font14);
		JTextField[] txt = {txtTime, txtPlayerNum};
		for(int i = 0 ; i < txt.length ; i++) {
			txt[i].setFont(font14);
			txt[i].setBorder(border);
			txt[i].setColumns(10);
		}
		JComboBox[] combo = {teamCombo , functionCombo};
		for(int i = 0 ; i < combo.length ; i++)
			combo[i].setFont(font14);
		instructionPanel.setBackground(new Color(239,239,239));
		instructionPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		instructionTxt.setFont(new Font(font, Font.PLAIN, 12));
		instructionTxt.setBackground(null);
		instructionTxt.setEditable(false);
		instructionTxt.setRows(10);	
		instructionTxt.setColumns(5);
		
		submitButton.setBackground(new Color(42,75,98));
		submitButton.setForeground(Color.WHITE);
		submitButton.setFont(new Font(font, Font.BOLD, 14));
		submitButton.setBorder(null);
		submitButton.setContentAreaFilled(false);
		submitButton.setOpaque(true);
	}
	public void createOutputPanel() {
		outputPanel = new JPanel();
		outputPanelLayout = new CardLayout(0,0);
		outputPanel.setLayout(outputPanelLayout);
		outputPanel.add(gamePanel , "Game");
		outputPanel.add(playerPanel , "Player");
		outputPanel.add(playerInGamePanel, "Player In Game");
	}
	public void createSubmitBtn(GameDataManagement gameDataManagement) {
		submitButton = new JButton("Submit");
		class ClickListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String functionSelec = (String) functionCombo.getSelectedItem();
					String playerNum = txtPlayerNum.getText();
					String time = txtTime.getText();
					String team = teamCombo.getSelectedItem().toString();
					team = SchedulePanel.depNametoLong(team);
					if(functionSelec.equals("Player")) {
						if(gameDataManagement.queryPlayer(String.valueOf(team),Integer.parseInt(playerNum))) {
							String name = querySQL.findPlayerName(team,Integer.parseInt(playerNum));
							String department = querySQL.findPlayerDepartment(team,Integer.parseInt(playerNum));
							playerName.setText(name);
							playerTeam.setText(department);
							
							outputPanelLayout.show(outputPanel, "Player");
							showResult(model1);
						}
						else {
							JOptionPane.showMessageDialog(null,"No result. Please input the correct information.");
						}
					}
					else if(functionSelec.equals("Game")) {
						if(gameDataManagement.queryGame(String.valueOf(team),String.valueOf(time))) {
							String date = querySQL.findTimeInGame(team,time);
							String place = querySQL.findGamePlace(team,time);
							String host = querySQL.findGameHost(team,time);
							String guest = querySQL.findGameGuest(team,time);
							gameTime.setText(date);
							gamePlace.setText(place);
							gameHost.setText(host);
							gameGuest.setText(guest);
							
							outputPanelLayout.show(outputPanel, "Game");
							showResult(model2);
						}
						else {
							JOptionPane.showMessageDialog(null,"No result. Please input the correct information.");
						}
					}
					else if(functionSelec.equals("Player In Game")) {
						if(gameDataManagement.queryPlayerInGameData(String.valueOf(team),String.valueOf(time),Integer.parseInt(playerNum))) {
							String name = querySQL.findPlayerName(team,Integer.parseInt(playerNum));
							String department = querySQL.findPlayerDepartment(team,Integer.parseInt(playerNum));
							String date = querySQL.findTimeInGame(team,time);
							String place = querySQL.findGamePlace(team,time);
							String guest = querySQL.findGameGuest(team,time);
							pgPlayer.setText(name);
							pgTeam.setText(department);
							pgTime.setText(date);
							pgPlace.setText(place);
							pgGuest.setText(guest);
							
							outputPanelLayout.show(outputPanel, "Player In Game");
							showResult(model3);
						}
						else {
							JOptionPane.showMessageDialog(null,"No result. Please input the correct information.");
						}
					}		
				}
				catch(NumberFormatException e2) {
					JOptionPane.showMessageDialog(null,"Please enter the relevant information correctly");
				}catch (Exception e1) {
					e1.printStackTrace();
				}finally {
					txtPlayerNum.setText("");
					txtTime.setText("");
					teamCombo.setSelectedIndex(1);
				}
			}
		}
		ActionListener listener = new ClickListener();
		submitButton.addActionListener(listener);
		add(submitButton);
	}
	
	public void initPlayerPanel(){
		table1 = new JTable();
		model1 = new DefaultTableModel();
		table1.setModel(model1);
		setTableGUI(table1);
		playerPanel = new JPanel();
		playerPanel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0,5,0,5);
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		JLabel player = new JLabel("Player");
		playerPanel.add(player,c);
		
		c.gridx = 0;
		c.gridy = 1;
		JLabel team = new JLabel("Team");
		playerPanel.add(team,c);

		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 0;
		playerName = new JLabel();
		playerPanel.add(playerName,c);
		
		c.gridx = 1;
		c.gridy = 1;
		playerTeam = new JLabel();
		playerPanel.add(playerTeam,c);
		JLabel[] titles = {player , team};
		JLabel[] inputs = {playerTeam , playerName};
		for(int i = 0 ; i < titles.length ; i++) {
			titles[i].setFont(new Font("Avenir Next", Font.BOLD, 14));
			inputs[i].setFont(new Font("Avenir Next", Font.PLAIN, 14));
		}
		table1.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		table1.getTableHeader().setFont(table1.getFont());
		
		playerScrollPane = new JScrollPane(table1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.ipadx = 300;
		c.weightx = 1;
		c.weighty = 1;
		playerPanel.add(playerScrollPane,c);
	}
	public void initGamePanel(){
		table2 = new JTable();
		model2 = new DefaultTableModel();
		table2.setModel(model2);
		setTableGUI(table2);
		
		gamePanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0,5,0,5);
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 1;
		
		c.gridx = 0;
		c.gridy = 0;
		JLabel time = new JLabel("Time");
		gamePanel.add(time,c);

		c.gridx = 1;
		c.gridy = 0;
		gameTime = new JLabel();
		gamePanel.add(gameTime,c);

		c.gridx = 0;
		c.gridy = 1;
		JLabel place = new JLabel("Place");
		gamePanel.add(place,c);

		c.gridx = 1;
		c.gridy = 1;
		gamePlace= new JLabel();
		gamePanel.add(gamePlace,c);

		c.gridx = 0;
		c.gridy = 2;
		JLabel host = new JLabel("Host");
		gamePanel.add(host,c);

		c.gridx = 1;
		c.gridy = 2;
		gameHost = new JLabel();
		gamePanel.add(gameHost,c);

		c.gridx = 0;
		c.gridy = 3;
		JLabel guest = new JLabel("Guest");
		gamePanel.add(guest,c);

		c.gridx = 1;
		c.gridy = 3;
		gameGuest = new JLabel();
		gamePanel.add(gameGuest,c);
		
		JLabel[] titles = {time , place , host , guest};
		JLabel[] inputs = {gameTime , gamePlace , gameHost , gameGuest};
		table2.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		table2.getTableHeader().setFont(table2.getFont());
		for(int i = 0 ; i < titles.length ; i++) {
			titles[i].setFont(new Font("Avenir Next", Font.BOLD, 14));
			inputs[i].setFont(new Font("Avenir Next", Font.PLAIN, 14));
		}
		gameScrollPane = new JScrollPane(table2,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		c.ipadx = 300;
		c.weightx = 1;
		c.weighty = 1;
		gamePanel.add(gameScrollPane,c);
	}
	public void initPlayerInGamePanel() {
		table3 = new JTable();
		model3 = new DefaultTableModel();
		table3.setModel(model3);
		setTableGUI(table3);

		GridBagLayout layout = new GridBagLayout();
		playerInGamePanel = new JPanel();
		playerInGamePanel.setLayout(layout);

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0,5,0,5);
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 1;
		
		c.gridx = 0;
		c.gridy = 0;
		JLabel player = new JLabel("Player");
		playerInGamePanel.add(player,c);

		c.gridx = 1; 
		c.gridy = 0;
		pgPlayer = new JLabel();
		playerInGamePanel.add(pgPlayer,c);

		c.gridx = 0;
		c.gridy = 1;
		JLabel team = new JLabel("Team");
		playerInGamePanel.add(team,c);

		c.gridx = 1;
		c.gridy = 1;
		pgTeam = new JLabel();
		playerInGamePanel.add(pgTeam,c);

		c.gridx = 0;
		c.gridy = 2;
		JLabel guest = new JLabel("Guest");
		playerInGamePanel.add(guest,c);

		c.gridx = 1;
		c.gridy = 2;
		pgGuest = new JLabel();
		playerInGamePanel.add(pgGuest,c);

		c.gridx = 0;
		c.gridy = 3;
		JLabel time = new JLabel("Time");
		playerInGamePanel.add(time,c);

		c.gridx = 1;
		c.gridy = 3;
		pgTime = new JLabel();
		playerInGamePanel.add(pgTime,c);

		c.gridx = 0;
		c.gridy = 4;
		JLabel place = new JLabel("Place");
		playerInGamePanel.add(place,c);

		c.gridx = 1;
		c.gridy = 4;
		pgPlace = new JLabel();
		playerInGamePanel.add(pgPlace,c);
		
		JLabel[] titles = {player , team , guest , time , place};
		JLabel[] inputs = {pgPlayer , pgTeam , pgGuest , pgTime , pgPlace};
		table3.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		table3.getTableHeader().setFont(table3.getFont());
		for(int i = 0 ; i < titles.length ; i++) {
			titles[i].setFont(new Font("Avenir Next", Font.BOLD, 14));
			inputs[i].setFont(new Font("Avenir Next", Font.PLAIN, 14));
		}
		playerInGameScrollPane = new JScrollPane(table3,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		c.ipadx = 300;
		c.weightx = 1;
		c.weighty = 1;
		playerInGamePanel.add(playerInGameScrollPane,c);
		}
	
	public void setTableGUI(JTable table) {
		for(int i = 0 ; i < table.getColumnCount() ; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(50);
		}
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
	}
	public void showResult(DefaultTableModel model) throws SQLException {
		for(int i = 0 ; i < model.getRowCount() ; i++) {
			model.removeRow(i);
		}
		ResultSet result = gameDataManagement.getResult();
		ResultSetMetaData metaData = result.getMetaData();
		int columnCount = metaData.getColumnCount();
		if(model.getColumnCount()==0) {
			for(int i = 1 ; i <= columnCount ; i++){
				model.addColumn(metaData.getColumnName(i));
			}
		}
		String[] row=new String[columnCount];
		while(result.next()){
			for(int i = 0 ; i < columnCount ; i++)
				row[i]=result.getString(i+1);
			model.addRow(row);
		}
		
		result.close();
	}
}

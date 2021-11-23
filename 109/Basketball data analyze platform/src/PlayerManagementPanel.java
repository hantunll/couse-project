import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;

public class PlayerManagementPanel extends JPanel {
	static final GridBagLayout LAYOUT = new GridBagLayout();

	private JPanel instructionPanel, functionPanel , submitPanel;
	private JComboBox functionComboBox , positionComboBox , depComboBox;
	private JLabel titleLabel , depLabel , nameLabel , positionLabel , numberLabel;
	private JTextField nameTextField,playerNumberTextField; 
	private JTextArea instructionTxt, textArea;
	private JButton submitButton;
	private PlayerManagement playerManagement;


	public PlayerManagementPanel() throws SQLException {
		setLayout(LAYOUT);
		GridBagConstraints c = new GridBagConstraints(0,0,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(15,10,0,10),0,0);
		playerManagement = new PlayerManagement();
		createInstructionPanel();
		createFunctionPanel();
		createSubmitPanel();
		add(instructionPanel,c);
		c.gridy = 1;
		c.weighty = 1;
		add(functionPanel,c);
		c.gridy = 2;
		c.weighty = 0.1;
		c.insets = new Insets(0,10,0,10);
		add(submitPanel,c);
		createSubmitBtn(playerManagement);
		setGUI();
	}
	public void createInstructionPanel(){
		String word = "Hi! Manager! Manage your player's information here!\nChoose your function(Add, Delete, Modify) and input the corresponding information.";

		instructionPanel = new JPanel();
		instructionPanel.setLayout(new BorderLayout(20,20));
		titleLabel = new JLabel("Player Management");
		instructionTxt = new JTextArea(word);

		instructionPanel.add(titleLabel,BorderLayout.WEST);
		instructionPanel.add(instructionTxt,BorderLayout.CENTER);
	}
	public void createFunctionPanel() {
		functionPanel = new JPanel();
		functionPanel.setLayout(new GridBagLayout());

		functionComboBox = new JComboBox();
		createFunctionComboBox();
		GridBagConstraints cFunctionCombo = new GridBagConstraints(0,0,4,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(0,5,0,5),0,0);
		functionPanel.add(functionComboBox, cFunctionCombo);

		//input:department name number position 
		depLabel = new JLabel("Dep");
		GridBagConstraints cDepLabel = new GridBagConstraints(0,1,1,1,0,0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,5,10),0,0);
		functionPanel.add(depLabel, cDepLabel);

		positionLabel = new JLabel("Position");
		GridBagConstraints cPositionLabel = new GridBagConstraints(0,2,1,1,0,0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,5,10),0,0);
		functionPanel.add(positionLabel, cPositionLabel);

		nameLabel = new JLabel("Name");
		GridBagConstraints cNameLabel = new GridBagConstraints(2,1,1,1,0,0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,5,10),0,0);
		functionPanel.add(nameLabel, cNameLabel);

		numberLabel = new JLabel("Number");
		GridBagConstraints cNumberLabel = new GridBagConstraints(2,2,1,1,0,0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,5,10),0,0);
		functionPanel.add(numberLabel, cNumberLabel);

		createDepComboBox(depComboBox = new JComboBox());
		GridBagConstraints cDepCombo = new GridBagConstraints(1,1,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(15,10,5,10),0,0);
		functionPanel.add(depComboBox, cDepCombo);

		positionComboBox = new JComboBox();
		createPositionComboBox();
		GridBagConstraints cPositionCombo = new GridBagConstraints(1,2,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(15,10,5,10),0,0);
		functionPanel.add(positionComboBox, cPositionCombo);

		nameTextField = new JTextField();
		GridBagConstraints cNameTxt = new GridBagConstraints(3,1,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(15,10,5,10),0,0);
		functionPanel.add(nameTextField, cNameTxt);

		playerNumberTextField = new JTextField();
		GridBagConstraints cNumTxt = new GridBagConstraints(3,2,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(15,10,5,10),0,0);
		functionPanel.add(playerNumberTextField, cNumTxt);

		functionComboBox.setSelectedIndex(0);
		positionComboBox.setSelectedIndex(0);
		depComboBox.setSelectedIndex(0);
	}

	public void createSubmitPanel() {
		submitPanel = new JPanel(new GridLayout(1,2,20,20));

		textArea = new JTextArea();
		submitPanel.add(textArea);

		submitButton = new JButton("Submit");
		submitPanel.add(submitButton);
	}

	public void createSubmitBtn(PlayerManagement playerManagement)  {
		class ClickListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UserManagement userManagement = NCCUBasketball.m1;
					//get input data
					String functionSelec = (String) functionComboBox.getSelectedItem();
					String positionSelec = (String) positionComboBox.getSelectedItem();
					String departmentSelec = (String) depComboBox.getSelectedItem();
					departmentSelec = SchedulePanel.depNametoLong(departmentSelec);
					String userDepartment = userManagement.getUser().getDepartment();
					String playerName = nameTextField.getText();
					int playerNum = Integer.parseInt(playerNumberTextField.getText());

					//set all input to null
					textArea.setText("");
					nameTextField.setText("");
					playerNumberTextField.setText("");
					functionComboBox.setSelectedIndex(0);
					positionComboBox.setSelectedIndex(0);
					depComboBox.setSelectedIndex(0);

					if(userDepartment.equals(departmentSelec)||userDepartment.equals("NCCU")) {
						switch (functionSelec) {
						case "Add Player":
							if(playerManagement.addPlayer(playerName , playerNum , positionSelec , departmentSelec)) {
								textArea.setText("Add player success");
							}else {
								textArea.setText("Add player failure");
							}
							break;
						case "Delete Player":
							if(playerManagement.deletePlayer(playerName , playerNum , positionSelec , departmentSelec)) {
								textArea.setText("Delete player success");
							}else {
								textArea.setText("Delete player failure");
							}
							break;
						case "Modify Player":
							if(playerManagement.modifyPlayer(playerName , playerNum , positionSelec , departmentSelec)) {
								textArea.setText("Modify player success");
							}else {
								textArea.setText("Modify player failure");
							}
							break;
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"You are not allow to manage this player.");
					}
				}catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(null,"Please enter the relevant information correctly.");
				}catch(SQLIntegrityConstraintViolationException e2) {
					JOptionPane.showMessageDialog(null,"The player has been add to the database.");
				}catch(SQLException e3) {
					e3.printStackTrace();
				}
			}
		}
		ActionListener listener = new ClickListener();
		submitButton.addActionListener(listener);
	}
	public void createFunctionComboBox() {
		String[] function = {"Add Player" , "Delete Player" , "Modify Player"};
		for(int i = 0 ; i < function.length ; i++) {
			functionComboBox.addItem(function[i]);
		}
	}
	public void createPositionComboBox() {
		String[] position = {"PG" , "SG" , "SF" , "PF" , "C"};
		for(int i = 0 ; i < position.length ; i++) {
			positionComboBox.addItem(position[i]);
		}
	}
	public static void createDepComboBox(JComboBox comboBox) {
		String[] departments = {"Chinese" , "Education" , "History" ,"Philosophy","Politics","Diplomacy","Society","Public Finance","Public Administration","Land Economics","Economics","Ethnology","International Business","Money and Banking","Accounting","Statistics","Business Administration","Management Information Systems","Finance","Risk Management and Insurance","Communication","English","Arabric","Slavic","Japanese","Korean","Turkish","European","Southeast Asian","Mathematical Sciences","Psychology","Computer Science"
		};
		
		for(int i = 0 ; i < departments.length ; i++) {
			departments[i] = SchedulePanel.depNametoShort(departments[i]);
			comboBox.addItem(departments[i]);
		}
	}
	public void setGUI() {
		setBackground(Color.WHITE);
		String font = "Avenir Next";
		Font titleFont = new Font(font, Font.BOLD, 20);
		Font font18 = new Font(font, Font.PLAIN, 18);
		Font font16 = new Font(font, Font.PLAIN, 16);

		JPanel[] panels = {instructionPanel, functionPanel , submitPanel};
		for(int i = 0 ; i < panels.length ; i++)
			panels[i].setBackground(null);
		instructionPanel.setBackground(new Color(239,239,239));
		instructionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		functionPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Function",TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, titleFont));
		submitPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel[] labels = {depLabel , nameLabel , positionLabel , numberLabel};
		titleLabel.setFont(titleFont);
		for(int i = 0 ; i < labels.length ; i++)
			labels[i].setFont(font18);

		JComboBox[] comboBox = {positionComboBox , depComboBox};
		functionComboBox.setFont(titleFont);
		for(int i = 0 ; i < comboBox.length ; i++) {
			comboBox[i].setFont(font16);
			comboBox[i].setMaximumRowCount(8);
		}

		JTextField[] txt = {nameTextField , playerNumberTextField};
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		for(int i = 0 ; i < txt.length ; i++) {
			txt[i].setFont(font16);
			txt[i].setColumns(8);
			txt[i].setBorder(border);
		}
		JTextArea[] txtAreas = {instructionTxt, textArea};
		for(int i = 0 ; i < txtAreas.length ; i++) {
			txtAreas[i].setFont(font16);
			txtAreas[i].setBackground(null);
			txtAreas[i].setEditable(false);
			txtAreas[i].setRows(4);	
		}
		instructionTxt.setFont(new Font(font, Font.PLAIN, 14));
		textArea.setPreferredSize(new Dimension(300,25));
		textArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		submitButton.setBackground(new Color(42,75,98));
		submitButton.setForeground(Color.WHITE);
		submitButton.setFont(new Font(font, Font.BOLD, 18));
		submitButton.setBorder(null);
		submitButton.setContentAreaFilled(false);
		submitButton.setOpaque(true);
		submitButton.setPreferredSize(new Dimension(300,25));

	}
}

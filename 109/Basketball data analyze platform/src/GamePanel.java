import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class GamePanel extends JPanel {
	static final GridBagLayout LAYOUT = new GridBagLayout();
	
	private JPanel instructionPanel , functionPanel;
	private JLabel titleLabel , timeLabel , placeLabel , hostLabel , guestLabel;
	private JTextField timeTextField , placeTextField;
	private JComboBox hostComboBox , guestComboBox;
	private JTextArea instructionTxt;
	private JButton submitButton;
	private GameManagement gameManagement;
	
	public static String host;
	
	public GamePanel() {
		setLayout(LAYOUT);
		GridBagConstraints c = new GridBagConstraints(0,0,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(15,10,0,10),0,0);
		createInstructionPanel();
		createFunctionPanel();
		add(instructionPanel,c);
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		add(functionPanel,c);
		setGUI();
	}
	public void createInstructionPanel(){
		String word = "Let's start a new Game!!\n"
				+ "Input the information below and the system will create a new frame for you!\n"
				+ "* The time format should be yyyy-MM-dd";

		instructionPanel = new JPanel();
		instructionPanel.setLayout(new BorderLayout(20,20));
		titleLabel = new JLabel("Record Game");
		instructionTxt = new JTextArea(word);

		instructionPanel.add(titleLabel,BorderLayout.WEST);
		instructionPanel.add(instructionTxt,BorderLayout.CENTER);
	}
	public void createFunctionPanel() {
		functionPanel = new JPanel();
		functionPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,15,5,15);
		c.fill = GridBagConstraints.BOTH;
		
		timeLabel = new JLabel("Time");
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		functionPanel.add(timeLabel,c);
		
		placeLabel = new JLabel("Place");
		c.gridx = 0;
		c.gridy = 2;
		functionPanel.add(placeLabel,c);
		
		hostLabel = new JLabel("Host");
		c.gridx = 0;
		c.gridy = 3;
		functionPanel.add(hostLabel,c);
		
		guestLabel = new JLabel("Guest");
		c.gridx = 0;
		c.gridy = 4;
		functionPanel.add(guestLabel,c);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    Date date = new Date();   
		timeTextField = new JTextField(formatter.format(date));
		timeTextField.setToolTipText("The format should be yyyy-MM-dd");
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.WEST;
		functionPanel.add(timeTextField,c);
		
		placeTextField = new JTextField();
		placeTextField.setToolTipText("Where is this game going??");
		c.gridx = 1;
		c.gridy = 2;
		functionPanel.add(placeTextField,c);
		
		createhostComboBox();
		hostComboBox.setToolTipText("The host can only be which department you belong to.");
		c.gridx = 1;
		c.gridy = 3;
		functionPanel.add(hostComboBox,c);
		
		createguestComboBox();
		guestComboBox.setToolTipText("Which team is your opponent?");
		c.gridx = 1;
		c.gridy = 4;
		functionPanel.add(guestComboBox,c);
		
		createSubmitBtn();
	}
	
	public void createSubmitBtn() {
		submitButton = new JButton("Submit");
		class ClickListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UserManagement userManagement = NCCUBasketball.m1;
					String time = timeTextField.getText();
					String place = placeTextField.getText();
					host = SchedulePanel.depNametoLong((String)hostComboBox.getSelectedItem());
					String guest = (String)guestComboBox.getSelectedItem();
					guest = SchedulePanel.depNametoLong(guest);
					Game game = new Game(time, place ,host ,guest);
					if(host.equals(userManagement.getUser().getDepartment()) || userManagement.getUser().getDepartment().equals("NCCU")){
						gameManagement = new GameManagement();
						
						boolean openFrame = false;
						if(gameManagement.findGame(game)) { //the game is not exist 
							int confirmed = JOptionPane.showConfirmDialog(null, 
							        "The game is exist. Do you still want to record?", "Game Exist Message Boxx",
							        JOptionPane.YES_NO_OPTION);

							    if (confirmed == JOptionPane.YES_OPTION) {
							    	openFrame = true;
							    }
						}
						else {
							gameManagement.addGame(game);	//therefore add a new game
							openFrame = true;
						}
						if(openFrame == true) {
							GameEventFrame frame = new GameEventFrame();	//whether or not the game existed still open the frame to record
							String gameID = String.valueOf(gameManagement.findGameID(time, place, host, guest));
							frame.getGameID().setText(gameID);
							frame.getTime().setText(time);
							frame.getPlace().setText(place);
							frame.getHost().setText(host);
							frame.getGuest().setText(guest);
							frame.setHost(frame.getHost().getText());
						}
					}else {
						JOptionPane.showMessageDialog(null,"You are not allow to record game on the host team.");
					}	
				}catch (SQLException e1) {
					e1.printStackTrace();
				}catch(NumberFormatException e2) {
					JOptionPane.showMessageDialog(null,"Please enter the relevant information correctly");
				}
			}
		}
		ActionListener listener = new ClickListener();
		submitButton.addActionListener(listener);
		
		GridBagConstraints cButton = new GridBagConstraints();
		cButton.gridwidth = 2;
		cButton.gridx = 0;
		cButton.gridy = 5;
		cButton.insets = new Insets(10,50,10,50);
		cButton.fill = GridBagConstraints.BOTH;
		functionPanel.add(submitButton,cButton);
	}
	public void createhostComboBox() {
		PlayerManagementPanel.createDepComboBox(hostComboBox = new JComboBox());
	}
	public void createguestComboBox() {
		PlayerManagementPanel.createDepComboBox(guestComboBox = new JComboBox());
	}
	
	public void setGUI() {
		String font = "Avenir Next";
		Font titleFont = new Font(font, Font.BOLD, 20);
		Font font18 = new Font(font, Font.PLAIN, 18);
		Font font16 = new Font(font, Font.PLAIN, 16);
		
		setBackground(Color.WHITE);
		instructionPanel.setBackground(new Color(239,239,239));
		instructionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		functionPanel.setBackground(null);
		functionPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"New Game",TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, titleFont));
		
		JLabel[] labels = {timeLabel , placeLabel , hostLabel , guestLabel};
		titleLabel.setFont(titleFont);
		for(int i = 0 ; i < labels.length ; i++) {
			labels[i].setFont(font18);
		}
		
		JTextField[] txt = {timeTextField , placeTextField};
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		for(int i = 0 ; i < txt.length ; i++) {
			txt[i].setFont(font16);
			txt[i].setColumns(15);
			txt[i].setBorder(border);
		}
		
		JComboBox[] cb = {hostComboBox , guestComboBox};
		for(int i = 0 ; i < cb.length ; i++) {
			cb[i].setFont(font16);
			cb[i].setMaximumRowCount(8);
		}
		
		instructionTxt.setFont(new Font(font, Font.PLAIN, 14));
		instructionTxt.setBackground(null);
		instructionTxt.setEditable(false);
		instructionTxt.setRows(4);	
		instructionTxt.setColumns(5);
		
		submitButton.setBackground(new Color(42,75,98));
		submitButton.setForeground(Color.WHITE);
		submitButton.setFont(new Font(font, Font.BOLD, 18));
		submitButton.setBorder(null);
		submitButton.setContentAreaFilled(false);
		submitButton.setOpaque(true);
	}

}
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class LoginPanel extends JPanel{
	
	private JLabel imgLabel;
	private JLabel titleLabel;
	private JLabel userIDLabel;
	private JLabel passwordLabel;
	
	private JTextField userIDField;
	private JPasswordField passwordField;
	
	private JButton loginButton;
	private JButton registerButton;
	
	public LoginPanel() {
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		
		createLoginButton();
		createRegisterButton();
		createIdComp();
		createPasswordComp();
		createTitle();
		addRegisterButtonListener();
		setGUI();
	}
	public void createLoginButton() {
		loginButton = new JButton("Log In");
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		c.insets = new Insets(25,0,0,0);
		c.anchor = GridBagConstraints.WEST;
		
		add(loginButton,c);
	}
	public void createRegisterButton() {
		registerButton = new JButton("New Accout");
		registerButton.setToolTipText("Don't have an account? Create It!");
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 4;
		c.gridwidth = 1;
		c.insets = new Insets(25,0,0,0);
		c.anchor = GridBagConstraints.EAST;
		add(registerButton,c);
	}
	public void createPasswordComp() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(25,0,0,0);
		passwordLabel = new JLabel("Password: ");
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.EAST;
		add(passwordLabel,c);
		
		passwordField = new JPasswordField();
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		add(passwordField,c);
	}
	public void createIdComp() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(25,0,0,0);
		userIDLabel = new JLabel("User ID: ");
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		add(userIDLabel,c);
		userIDField = new JTextField();
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		add(userIDField,c);
	}
	public void createTitle() {
		GridBagConstraints c = new GridBagConstraints();
		imgLabel = new JLabel();
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("./src/basketball.png").getImage().getScaledInstance(125,125, Image.SCALE_DEFAULT));
		imgLabel.setIcon(imageIcon);
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(imgLabel,c);
		
		titleLabel = new JLabel("NCCU Basketball");
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		add(titleLabel,c);
	}
	public void setGUI() {
		Color bg = Color.WHITE;
		Font titleFont = new Font("Avenir Next", Font.BOLD, 48);
		Font lblFont = new Font("Avenir Next", Font.PLAIN, 24);
		Font btFont = new Font("Avenir Next", Font.PLAIN, 20);
		JButton[] buttons = {loginButton,registerButton};
		JLabel[] labels = {userIDLabel,passwordLabel};
		JTextField[] fields = {userIDField,passwordField};
		
		setBackground(bg);
		titleLabel.setFont(titleFont);
		for(int i = 0; i < buttons.length ; i++) {
			buttons[i].setBorder(BorderFactory.createEtchedBorder());
			buttons[i].setContentAreaFilled(false);
			buttons[i].setOpaque(true);
			buttons[i].setFont(btFont);
			buttons[i].setPreferredSize(new Dimension((i+1)*85,35));
		}
		for(int i = 0; i < labels.length ; i++) {
			labels[i].setFont(lblFont);
			fields[i].setFont(lblFont);
			fields[i].setColumns(10);
			fields[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		}
		
	}
	public JButton getLoginButton() {
		return loginButton;
	}
	
	public void addRegisterButtonListener() {
		class ClickListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					RegisterFrame frame = new RegisterFrame();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		ActionListener listener = new ClickListener();
		registerButton.addActionListener(listener);
	}
	
	public void addLoginButtonListener(JPanel panel1, UserManagement userManagement, JMenuBar mb) {
		class ClickListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String ID = userIDField.getText();
					String password = String.valueOf(passwordField.getPassword());
					if(userManagement.userExist(ID)) {
						if(userManagement.passwordCorrect(ID, password)) {
							if(userManagement.getUser().getIdentity().equals("Admin")) {
								CardLayout c1 = (CardLayout)panel1.getLayout();
								c1.show(panel1, "manager");
							}
							else if(userManagement.getUser().getIdentity().equals("User")) {
								CardLayout c1 = (CardLayout)panel1.getLayout();
								c1.show(panel1, "general");
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"Your ID or Password may be wrong.");
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"This user ID is not exist.");
					}
				}catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(null,"Please enter the relevant information correctly");
					
				}finally {
					userIDField.setText("");
					passwordField.setText("");
				}
			}
			
		}
		ActionListener listener = new ClickListener();
		loginButton.addActionListener(listener);
	}

}
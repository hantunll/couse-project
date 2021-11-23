import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RegisterFrame extends JFrame{
	private UserManagement userManagement;
	private JPanel panel;
	
	private JLabel titleLabel , userIDLabel , passwordLabel , passwordConfirmLabel , departmentLabel;
	
	private JTextField userIDField;
	private JPasswordField passwordField;
	private JPasswordField passwordConfirmField;
	private JComboBox departmentComboBox;
	private JTextArea textArea;
	
	private JButton submitButton;
	
	public RegisterFrame() throws SQLException {
		userManagement = new UserManagement();
		setTitle("Create New Accout");
		setVisible(true);
		setSize(1000, 500);
		setLocationRelativeTo(null);
		panel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);
		createSubmitButton();
		createIdComp();
		createPasswordComp();
		createConfirmComp();
		createDepartmentComp();
		createTitle();
		setGUI();
		add(panel);
	}
	public void createSubmitButton() {
		submitButton = new JButton("Submit");
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 2;
		c.insets = new Insets(25,0,0,0);
		panel.add(submitButton,c);
		addSubmitButtonListener();
	}
	public void createDepartmentComp() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(25,0,0,0);
		departmentLabel = new JLabel("Department: ");
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.EAST;
		panel.add(departmentLabel,c);
		
		PlayerManagementPanel.createDepComboBox(departmentComboBox = new JComboBox());
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		panel.add(departmentComboBox,c);
	}
	public void createConfirmComp() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(25,0,0,0);
		passwordConfirmLabel = new JLabel("Confirm Password: ");
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.EAST;
		panel.add(passwordConfirmLabel,c);
		
		passwordConfirmField = new JPasswordField();
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		panel.add(passwordConfirmField,c);
	}
	public void createPasswordComp() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(25,0,0,0);
		passwordLabel = new JLabel("Password: ");
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		panel.add(passwordLabel,c);
		
		passwordField = new JPasswordField();
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		panel.add(passwordField,c);
	}
	public void createIdComp() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(25,0,0,0);
		userIDLabel = new JLabel("User ID: ");
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		panel.add(userIDLabel,c);
		
		userIDField = new JTextField();
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		panel.add(userIDField,c);
	}
	public void createTitle() {
		GridBagConstraints c = new GridBagConstraints();
		titleLabel = new JLabel("Create New User");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.anchor = GridBagConstraints.CENTER;
		panel.add(titleLabel,c);
	}
	
	public void setGUI() {
		Color bg = Color.WHITE;
		Font titleFont = new Font("Avenir Next", Font.BOLD, 48);
		Font font = new Font("Avenir Next", Font.PLAIN, 24);
		JLabel[] labels = {userIDLabel , passwordLabel , passwordConfirmLabel , departmentLabel};
		
		panel.setBackground(bg);
		titleLabel.setFont(titleFont);
		
		for(int i = 0 ; i < labels.length ; i++) {
			labels[i].setFont(font);
		}
		userIDField.setFont(font);
		userIDField.setColumns(10);
		passwordField.setFont(font);
		passwordField.setColumns(10);
		passwordConfirmField.setFont(font);
		passwordConfirmField.setColumns(10);
		departmentComboBox.setFont(font);
		submitButton.setBackground(Color.GRAY);
		submitButton.setFont(font);
		textArea.setFont(font);
	}
	
	public void addSubmitButtonListener() {
		class ClickListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(userIDField.getText());
					String password = String.valueOf(passwordField.getPassword());
					String passwordConfirm = String.valueOf(passwordConfirmField.getPassword());
					String department = (String) departmentComboBox.getSelectedItem();
					if(!password.equals("") && password.equals(passwordConfirm)) {
						String ID = String.valueOf(id);
						if(userManagement.addUser(ID, password, "User", department)) {
							textArea.setText("Register Success");
							userIDField.setText("");
							passwordField.setText("");
							passwordConfirmField.setText("");
							departmentComboBox.setSelectedIndex(0);
						}
							else {
								textArea.setText("Register Failure");
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"Password doesn't match.");
						}
					}catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(null,"Please enter the relevant information correctly.");
				}catch(SQLIntegrityConstraintViolationException e2) {
					JOptionPane.showMessageDialog(null,"The ID has existed.Please Change your ID.");
				}catch(SQLException e3) {
					e3.printStackTrace();
				}
			}
		}
		
		GridBagConstraints c = new GridBagConstraints();
		textArea = new JTextArea();
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 2;
		panel.add(textArea,c);
		ActionListener listener = new ClickListener();
		submitButton.addActionListener(listener);
	}
	

}

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;

public class NCCUBasketball extends JFrame {

	private JPanel panel;
	private CardLayout contentLayout;
	private LoginPanel loginPanel;
	private GeneralPanel generalPanel;
	private ManagerPanel managerPanel;
	static UserManagement m1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NCCUBasketball frame = new NCCUBasketball();
					frame.setVisible(true);
					frame.setSize(1000, 500);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	public NCCUBasketball() throws SQLException {
		setBackground(Color.WHITE);
		//Instantiate the UserManagement object and add
		m1 = new UserManagement();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Instantiate the JMenuBar, JMenu, and JMenuItem
		JMenuBar menuBar = new JMenuBar();
		JMenu menu= new JMenu("Menu");
		JMenuItem logOutMenuItem = new JMenuItem("Log out"); 
		logOutMenuItem.setActionCommand("Log out");
		JMenuItem exitMenuItem = new JMenuItem("Exit"); 
		exitMenuItem.setActionCommand("Exit");
		
		// add items to menu
		menu.add(logOutMenuItem);
		menu.add(exitMenuItem);
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		setContentPane(panel);
		contentLayout = new CardLayout(0, 0);
		panel.setLayout(contentLayout);
		
		loginPanel = new LoginPanel();
		loginPanel.setBackground(Color.WHITE);
		panel.add(loginPanel, "login");
		loginPanel.addLoginButtonListener(panel, m1, menuBar);
		
		generalPanel = new GeneralPanel();
		panel.add(generalPanel, "general");
		
		managerPanel = new ManagerPanel();
		panel.add(managerPanel, "manager");
		
		
		// set up logout listener
					class LogOutListener implements ActionListener{

						@Override
						public void actionPerformed(ActionEvent e) {
							contentLayout.show(panel, "login");
							
						}
						
					}
					ActionListener logOutListener = new LogOutListener();
					logOutMenuItem.addActionListener(logOutListener);
					
					
					// set up exit listener
					class ExitListener implements ActionListener{

						@Override
						public void actionPerformed(ActionEvent e) {
							System.exit(0);
						}
						
					}
					ActionListener exitListener = new ExitListener();
					exitMenuItem.addActionListener(exitListener);
	}

}

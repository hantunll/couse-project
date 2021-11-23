import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.CardLayout;
import java.awt.Image;

public class ManagerPanel extends GeneralPanel {
	private JButton recordButton;
	private JButton playerMButton;
	
	private JPanel recordGamePanel;
	private JPanel playerMPanel;

	public ManagerPanel() {
		super();
	}
	
	public void createButtonPanel() {
		super.createButtonPanel();
		createRecordButton();
		createPlayerMButton();
		
	}
	public void createCardPanel() {
		super.createCardPanel();
		
		recordGamePanel = new GamePanel();
		super.cardPanel.add(recordGamePanel, "rec");
		
		try {
			playerMPanel = new PlayerManagementPanel();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.cardPanel.add(playerMPanel, "ply");
	}
	
	public void createScheduleButton() {
		super.createScheduleButton();
		
		class schListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout)cardPanel.getLayout();
				c1.show(cardPanel, "sch");
				
				ManagerPanel.super.scheduleButton.setBackground(LIGHTBLUE);
				ManagerPanel.super.queryButton.setBackground(DARKBLUE);
				recordButton.setBackground(DARKBLUE);
				playerMButton.setBackground(DARKBLUE);
			}
		}
		ActionListener schListener = new schListener();
		scheduleButton.addActionListener(schListener);
	}
	
	public void createQueryButton() {
		super.createQueryButton();
		
		class qListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout)cardPanel.getLayout();
				c1.show(cardPanel, "q");
				
				ManagerPanel.super.scheduleButton.setBackground(DARKBLUE);
				ManagerPanel.super.queryButton.setBackground(LIGHTBLUE);
				recordButton.setBackground(DARKBLUE);
				playerMButton.setBackground(DARKBLUE);
			}
		}
		ActionListener qListener = new qListener();
		queryButton.addActionListener(qListener);
		
	}
	
	public void createRecordButton() {
		recordButton = new JButton("Record Game");
		ImageIcon icon = new ImageIcon(new ImageIcon("./src/recordGame.png").getImage().getScaledInstance(20, 20 , Image.SCALE_DEFAULT));
		recordButton.setIcon(icon);
		recordButton.setIconTextGap(5);
		super.buttonGUI(recordButton);
		super.buttonPanel.add(recordButton);
		
		class recListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout)ManagerPanel.super.cardPanel.getLayout();
				c1.show(ManagerPanel.super.cardPanel, "rec");
				
				ManagerPanel.super.scheduleButton.setBackground(DARKBLUE);
				ManagerPanel.super.queryButton.setBackground(DARKBLUE);
				recordButton.setBackground(LIGHTBLUE);
				playerMButton.setBackground(DARKBLUE);
			}
		}
		ActionListener recListener = new recListener();
		recordButton.addActionListener(recListener);
		
	}
	public void createPlayerMButton() {
		playerMButton = new JButton("Player Management");
		ImageIcon icon = new ImageIcon(new ImageIcon("./src/playerManagement.png").getImage().getScaledInstance(20, 20 , Image.SCALE_DEFAULT));
		playerMButton.setIcon(icon);
		playerMButton.setIconTextGap(5);
		super.buttonGUI(playerMButton);
		super.buttonPanel.add(playerMButton);
		
		class plyListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout)ManagerPanel.super.cardPanel.getLayout();
				c1.show(ManagerPanel.super.cardPanel, "ply");
				
				ManagerPanel.super.scheduleButton.setBackground(DARKBLUE);
				ManagerPanel.super.queryButton.setBackground(DARKBLUE);
				recordButton.setBackground(DARKBLUE);
				playerMButton.setBackground(LIGHTBLUE);
			}
		}
		ActionListener plyListener = new plyListener();
		playerMButton.addActionListener(plyListener);
		
	}

}

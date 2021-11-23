import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Image;

public class GeneralPanel extends JPanel {
	static final int FRAME_WIDTH = 1000;
	static final int FRAME_HEIGHT = 500;
	
	static final Color DARKBLUE = new Color(42,75,98);
	static final Color LIGHTBLUE = new Color(148,165,176);
	static final GridBagLayout LAYOUT = new GridBagLayout();
	
	private JPanel titlePanel;
		private JLabel imgLabel;
		private JLabel titleLabel;
	
	protected JPanel buttonPanel;
		protected JButton scheduleButton;
		protected JButton queryButton;
	
	public JPanel cardPanel;
		private JPanel schdulePanel;
		private JPanel queryPanel;
		
	public GeneralPanel() {
		setBackground(Color.WHITE);
		setLayout(LAYOUT);
		setPreferredSize(new Dimension(FRAME_WIDTH , FRAME_HEIGHT));
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		//Title Panel
		createTitlePanel();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 10;
		c.gridheight = 2;
		c.weightx = 0;
		c.weighty = 0;
		add(titlePanel,c);
		
		//button Panel
		createButtonPanel();
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.gridheight = 7;
		c.weightx = 0;
		c.weighty = 0;
		add(buttonPanel , c);
		
		//card Panel
		createCardPanel();
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 8;
		c.gridheight = 7;
		c.weightx = 1;
		c.weighty = 1;
		add(cardPanel , c);
		
		setGUI();
	}
	
	public void createScheduleButton() {
		ImageIcon icon = new ImageIcon(new ImageIcon("./src/schedule.png").getImage().getScaledInstance(20, 20 , Image.SCALE_DEFAULT));
		scheduleButton = new JButton("Schedule");
		scheduleButton.setIcon(icon);
		scheduleButton.setIconTextGap(5);
		buttonGUI(scheduleButton);
		scheduleButton.setBackground(LIGHTBLUE);
		buttonPanel.add(scheduleButton);
		
		class schListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout)cardPanel.getLayout();
				c1.show(cardPanel, "sch");
				
				scheduleButton.setBackground(LIGHTBLUE);
				queryButton.setBackground(DARKBLUE);
			}
		}
		ActionListener schListener = new schListener();
		scheduleButton.addActionListener(schListener);
	}
	
	public void createQueryButton() {
		ImageIcon icon = new ImageIcon(new ImageIcon("./src/query.png").getImage().getScaledInstance(20, 20 , Image.SCALE_DEFAULT));
		queryButton = new JButton("Query");
		queryButton.setIcon(icon);
		queryButton.setIconTextGap(5);
		buttonGUI(queryButton);
		buttonPanel.add(queryButton);
		
		class qListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout)cardPanel.getLayout();
				c1.show(cardPanel, "q");
				queryButton.setBackground(LIGHTBLUE);
				scheduleButton.setBackground(DARKBLUE);
			}
		}
		ActionListener qListener = new qListener();
		queryButton.addActionListener(qListener);
		
	}
	
	public void createTitlePanel() {
		titlePanel = new JPanel();
		titlePanel.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new FlowLayout());
		
		imgLabel = new JLabel();
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("./src/basketball.png").getImage().getScaledInstance(70, 70 , Image.SCALE_DEFAULT));
		imgLabel.setIcon(imageIcon);
		panel.add(imgLabel);
		
		titleLabel = new JLabel("NCCUBasketball");
		titleLabel.setFont(new Font("Avenir Next", Font.BOLD, 36));
		panel.add(titleLabel);
		titlePanel.add(panel,BorderLayout.SOUTH);
	}
	public void createButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(7,1));
		
		createScheduleButton();
		createQueryButton();
		
	}
	public void createCardPanel() {
		cardPanel = new JPanel();
		cardPanel.setLayout(new CardLayout(0, 0));
		
		schdulePanel = new SchedulePanel();
		cardPanel.add(schdulePanel, "sch");
		
		queryPanel = new QueryPanel();
		cardPanel.add(queryPanel, "q");
		
	}
	
	public void buttonGUI(JButton button) {
		button.setFont(new Font("Avenir Next", Font.PLAIN, 18));
		button.setBackground(DARKBLUE);
		button.setForeground(Color.WHITE);
		button.setBorder(null);
		button.setContentAreaFilled(false);
		button.setOpaque(true);
	}
	public void setGUI() {
		Border hBorder = BorderFactory.createMatteBorder(0,0,3,0, Color.BLACK);
		Border vBorder = BorderFactory.createMatteBorder(0,0,0,3, Color.BLACK);
		
		titlePanel.setPreferredSize(new Dimension(FRAME_WIDTH,(int) (FRAME_HEIGHT*0.2)));
		titlePanel.setBackground(Color.WHITE);
		titlePanel.setBorder(hBorder);
		
		buttonPanel.setPreferredSize(new Dimension((int) (FRAME_WIDTH*0.2),(int) (FRAME_HEIGHT*0.7)));
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setBorder(vBorder);
		cardPanel.setPreferredSize(new Dimension((int) (FRAME_WIDTH*0.8),(int) (FRAME_HEIGHT*0.7)));
		cardPanel.setBackground(Color.WHITE);
		
		
	}
}

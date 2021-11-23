import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class ScorePanel extends JPanel{
	static final FlowLayout LAYOUT = new FlowLayout(FlowLayout.TRAILING , 0,0);
	
	private JButton minusButton;
	private JLabel scoreLabel;
	private JButton plusButton;
	
	public ScorePanel() {
		setBorder(new LineBorder(Color.BLACK, 1));
		setLayout(LAYOUT);
		
		createMinusButton();
		createScoreLabel();
		createPlusButton();
		setGUI();
		
		add(minusButton);
		add(scoreLabel);
		add(plusButton);
		
	}
	// get the score label value of the scorepanel
	public String getScoreValue() {
		return this.scoreLabel.getText();
	}
	
	public void createScoreLabel() {
		scoreLabel = new JLabel("0",SwingConstants.CENTER);
	}
	
	public void createPlusButton() {
		plusButton = new JButton("+");
		class ClickListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				int num = Integer.parseInt(scoreLabel.getText());
				num++;
				scoreLabel.setText(Integer.toString(num));
			}	
		}
		ActionListener listener = new ClickListener();
		plusButton.addActionListener(listener);
	}
	
	public void createMinusButton() {
		minusButton = new JButton("-");
		class ClickListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				int num = Integer.parseInt(scoreLabel.getText());
				if(num > 0) {
					num--;
					scoreLabel.setText(Integer.toString(num));
				}
			}	
		}
		ActionListener listener = new ClickListener();
		minusButton.addActionListener(listener);
	}
	
	public void setGUI() {
		setBackground(Color.WHITE);

		Font font = new Font("Avenir Next", Font.PLAIN, 16);
		Dimension buttonSize = new Dimension(17,30);
		
		minusButton.setPreferredSize(buttonSize);
		minusButton.setBackground(Color.LIGHT_GRAY);
		minusButton.setForeground(Color.BLACK);
		minusButton.setFont(font);
		minusButton.setBorder(null);
		minusButton.setContentAreaFilled(false);
		minusButton.setOpaque(true);
		
		scoreLabel.setPreferredSize(new Dimension(36,30));
		scoreLabel.setFont(font);
		
		plusButton.setPreferredSize(buttonSize);
		plusButton.setBackground(Color.LIGHT_GRAY);
		plusButton.setForeground(Color.BLACK);
		plusButton.setFont(font);
		plusButton.setBorder(null);
		plusButton.setContentAreaFilled(false);
		plusButton.setOpaque(true);
	}
}

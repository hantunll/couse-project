import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

public class SchedulePanel extends JPanel {
	static final GridBagLayout LAYOUT = new GridBagLayout();
	private String[] months = { "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };
	private String[] years = { "2020", "2021", "2022" };
	private JComboBox yearCombo;
	private JComboBox monthCombo;
	private JTable table;
	private JScrollPane scroll;
	private JPanel instructionPanel;
	private JTextArea instructionTxt;
	private JLabel titleLabel;
	private CalendarModel model;
	private GameManagement gameManagement;

	public SchedulePanel(){
		setLayout(LAYOUT);
		createTitleCombo();
		createTable();
		setGUI();
		try {
			setScheduleGame();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createTable() {
		GridBagConstraints c = new GridBagConstraints();
		model = new CalendarModel();
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(500,300));
		scroll = new JScrollPane(table);
		scroll.setColumnHeaderView(table.getTableHeader());
		model.setMonth(yearCombo.getSelectedIndex() + 1998, monthCombo.getSelectedIndex());

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 7;
		c.insets = new Insets(5,10,5,10);
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;

		add(scroll,c);
	}
	public void createTitleCombo() {
		String word = "Welcome to NCCU Basketball. Here is this month's Game Schedule.\nIf you want to know other years  or months scheduled game, choose whatever you want!";

		instructionPanel = new JPanel();
		instructionPanel.setLayout(new BorderLayout(50,50));
		titleLabel = new JLabel("Schedule");
		instructionTxt = new JTextArea(word);

		instructionPanel.add(titleLabel,BorderLayout.WEST);
		instructionPanel.add(instructionTxt,BorderLayout.CENTER);

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 7;
		c.insets = new Insets(10,10,5,10);
		c.fill = GridBagConstraints.BOTH;
		add(instructionPanel,c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5,10,0,10);
		c.fill = GridBagConstraints.HORIZONTAL;
		yearCombo = new JComboBox(years);
		yearCombo.setSelectedIndex(1);
		yearCombo.addItemListener(new ComboHandler());
		add(yearCombo,c);

		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 3;
		monthCombo = new JComboBox(months);
		SimpleDateFormat formatter = new SimpleDateFormat("MM");  
	    Date date = new Date();   
		int month = Integer.parseInt(formatter.format(date))-1;
		monthCombo.setSelectedIndex(month);
		monthCombo.setMaximumRowCount(6);
		monthCombo.addItemListener(new MonthHandler());
		add(monthCombo,c);
	}
	public void setGUI() {
		setBackground(Color.WHITE);
		
		Font font = new Font("Avenir Next", Font.PLAIN, 14);
		Font titleFont = new Font("Avenir Next", Font.BOLD, 24);
		instructionPanel.setBackground(new Color(239,239,239));
		instructionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		titleLabel.setFont(titleFont);
		instructionTxt.setFont(font);
		instructionTxt.setBackground(null);
		instructionTxt.setEditable(false);
		instructionTxt.setRows(2);
		
		yearCombo.setFont(font);
		monthCombo.setFont(font);
		table.setFont(font);
		table.getTableHeader().setFont(table.getFont());

		int height = 20;
		int row = table.getRowCount();
		table.setFillsViewportHeight(true);
		for(int i = 0 ; i < row ; i++) {
			table.setRowHeight(i, height);
		}
		table.setPreferredSize(new Dimension(700,height * row));
	}
	public void setScheduleGame() throws SQLException, ParseException {

		gameManagement = new GameManagement();
		ArrayList<Game> gamesAll = gameManagement.getScheduleGame();
		ArrayList<Game> games = new ArrayList<Game>();
		for(int i = 0 ; i < gamesAll.size() ; i++) {
			String time = gamesAll.get(i).getTime();
			int year = Integer.parseInt(time.substring(0, 4));
			int month = Integer.parseInt(time.substring(5, 7));
			if(year == yearCombo.getSelectedIndex() + 2020 && month == monthCombo.getSelectedIndex() + 1) {
				games.add(gamesAll.get(i));
			}
		}
		for(int i = 1 ; i < 14 ; ++i) {
			for(int j = 0 ; j < 7 ; ++j) {
				String day = (String)model.getValueAt(i, j);
				for(int k = 0 ; k < games.size() ; k++) {
					String time = games.get(k).getTime();
					String gameDay = time.substring(8, 10);
					if(gameDay.substring(0,1).equals("0")) {
						gameDay = gameDay.substring(1, 2);
					}
					if(gameDay.equals(day)) {
						String date = String.format("%s",model.getValueAt(i, j));
						String info = String.format("%s v.s %s", depNametoShort(gamesAll.get(k).getHost()), depNametoShort(gamesAll.get(k).getGuest()));
						model.setValueAt(date, i, j);
						model.setValueAt(info, i+1, j);
//						System.out.println(info);
					}
				}
			}
		}
	}

	class CalendarModel extends AbstractTableModel{
		int row = 14;
		int col = 7;
		String[] columnNames = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

		int[] numDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		String[][] calendar = new String[row][col];

		public CalendarModel() {
			//			for (int i = 0; i < columnNames.length; ++i)
			//				calendar[0][i] = columnNames[i];
			for (int i = 1; i < row; ++i)
				for (int j = 0; j < col; ++j)
					calendar[i][j] = " ";
		}

		public int getRowCount() {
			return row;
		}

		public int getColumnCount() {
			return col;
		}
		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int column) {
			return calendar[row][column];
		}

		public void setValueAt(Object value, int row, int column) {
			calendar[row][column] = (String) value;
		}

		public void setMonth(int year, int month) {
			for (int i = 1; i < row; ++i)
				for (int j = 0; j < col; ++j)
					calendar[i][j] = " ";
			java.util.GregorianCalendar cal = new java.util.GregorianCalendar();
			cal.set(year, month, 1);
			int offset = cal.get(java.util.GregorianCalendar.DAY_OF_WEEK) - 1;
			offset += 7;
			int num = daysInMonth(year, month);
			for (int i = 0; i < num; ++i) {
				calendar[(offset / 7)*2][offset % 7] = Integer.toString(i + 1);
				++offset;
			}
		}

		public boolean isLeapYear(int year) {
			if (year % 4 == 0)
				return true;
			return false;
		}

		public int daysInMonth(int year, int month) {
			int days = numDays[month];
			if (month == 1 && isLeapYear(year))
				++days;
			return days;
		}
	}
	class ComboHandler implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			model.setMonth(yearCombo.getSelectedIndex() + 1998, monthCombo.getSelectedIndex());
			try {
				setScheduleGame();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			table.repaint();
		}
	}
	class MonthHandler implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			model.setMonth(yearCombo.getSelectedIndex() + 1998, monthCombo.getSelectedIndex());
			try {
				setScheduleGame();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			table.repaint();
		}
	}
	public static String depNametoShort(String depname) {
		switch(depname) {
		case"Chinese":
			return "CHIN";
		case"Education":
			return "EDU";
		case"History":
			return "HIS";	
		case"Philosophy":
			return "PHL";
		case"Politics":
			return "PS";	
		case"Diplomacy":
			return "DIP";	
		case"Society":
			return "SOC";	
		case"Public Finance":
			return "PF";	
		case"Public Administration":
			return "PA";	
		case"Land Economics":
			return "LE";	
		case"Economics":
			return "ECO";	
		case"Ethnology":
			return "ETH";	
		case"International Business":
			return "IB";	
		case"Money and Banking":
			return "MAB";
		case"Accounting":
			return "ACCT";
		case"Statistics":
			return "STAT";
		case"Business Administration":
			return "BA";
		case"Management Information Systems":
			return "MIS";
		case"Finance":
			return "FIN";
		case"Risk Management and Insurance":
			return "RMI";			
		case"Communication":
			return "COMM";			
		case"English":
			return "ENG";			
		case"Arabric":
			return "ARB";		
		case"Slavic":
			return "SLV";			
		case"Japanese":
			return "JAP";			
		case"Korean":
			return "KOR";			
		case"Turkish":
			return "TUR";			
		case"European":
			return "EURO";			
		case"Southeast Asian":
			return "SEALC";			
		case"Mathematical Sciences":
			return "MATH";					
		case"Psychology":
			return "PSY";		
		case"Computer Science":
			return "CS";				

		}
		return "";
	}
	public static String depNametoLong(String depname) {
		switch(depname) {
		case"CHIN":
			return "Chinese";
		case"EDU":
			return "Education";
		case"HIS":
			return "History";	
		case"PHL":
			return "Philosophy";
		case"PS":
			return "Politics";	
		case"DIP":
			return "Diplomacy";	
		case"SOC":
			return "Society";	
		case"PF":
			return "Public Finance";	
		case"PA":
			return "Public Administration";	
		case"LE":
			return "Land Economics";	
		case"ECO":
			return "Economics";	
		case"ETH":
			return "Ethnology";	
		case"IB":
			return "International Business";	
		case"MAB":
			return "Money and Banking";
		case"ACCT":
			return "Accounting";
		case"STAT":
			return "Statistics";
		case"BA":
			return "Business Administration";
		case"MIS":
			return "Management Information Systems";
		case"FIN":
			return "Finance";
		case"RMI":
			return "Risk Management and Insurance";			
		case"COMM":
			return "Communication";			
		case"ENG":
			return "English";			
		case"ARB":
			return "Arabric";		
		case"SLV":
			return "Slavic";			
		case"JAP":
			return "Japanese";			
		case"KOR":
			return "Korean";			
		case"TUR":
			return "Turkish";			
		case"EURO":
			return "European";			
		case"SEALC":
			return "Southeast Asian";			
		case"MATH":
			return "Mathematical Sciences";					
		case"PSY":
			return "Psychology";		
		case"CS":
			return "Computer Science";				

		}
		return "";
	}
}

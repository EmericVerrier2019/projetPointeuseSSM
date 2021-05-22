package view;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import projetPointeuseSSM.Department;
import projetPointeuseSSM.Employee;
import projetPointeuseSSM.Planning;
import projetPointeuseSSM.TimeClock;

public class TimeClockFrame extends JFrame{/*
	private ArrayList<Employee> eliste;
	Employee e1; 
	Employee e2;
	
	class tableauEmployees extends AbstractTableModel
	{
		@Override
		public int getRowCount() {
			System.out.println(eliste.size() + " nombre de lignes ");
			return eliste.size();
		}
	
		@Override
		public int getColumnCount() {
			System.out.println(Employee.class.getDeclaredFields().length + "nombre théorique de colonnes");
				return 4;
		}
	
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch(columnIndex)
			{
			case 0:
				return eliste.get(rowIndex).getFirstName();
			case 1:
				return eliste.get(rowIndex).getLastName();
			case 2:
				return eliste.get(rowIndex).getIdDepartment();
			case 3:
				return eliste.get(rowIndex).getIdEmployee();
			default:
				throw new IllegalArgumentException();
			}
					
		}
		
	}*/
	public TimeClockFrame() 
	{
		super();
		/*e1 = new Employee("Jacques","Martin",1,2);
		e2 = new Employee("Philippe","Poutou",2,4);
		eliste = new ArrayList<Employee>();
		eliste.add(e1);
		eliste.add(e2);*/
		build();
	}
	private void build() 
	{
		// define the organization of JFrame : one JTabbedPane which embed two JPanel
		JTabbedPane timeClockPane = new JTabbedPane();
		JPanel timeClockTab = new JPanel();
		JPanel parametersElem = new JPanel();
		
		// define the String which will be rendered for the date
		String dateElem = "<html>" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd EEEE kk:mm:ss")).toString();
		JLabel textDate = new JLabel(dateElem);
		// define the user id zone and the button to send it to the main application
		JTextField userIdField = new JTextField(20);
		JLabel userIdLabel = new JLabel("User Id :");
		JButton userIdButton = new JButton("Check In/Out");
		dateElem = dateElem + ("<br>" + " Okay let's say : " +TimeClock.RoundTime().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)).toString());
		timeClockTab.add(textDate);
		userIdField.setEditable(true);
		userIdLabel.setLabelFor(userIdField);
		userIdLabel.setDisplayedMnemonic('i');
		timeClockTab.add(userIdLabel);
		timeClockTab.add(userIdField);
		timeClockTab.add(userIdButton);
		
		// define the content of the parameter Pane
		JLabel ipLabel = new JLabel("IP Adress : ");
		JLabel portLabel = new JLabel("Port : ");
		JTextField ipField = new JTextField(16);
		JTextField portField = new JTextField(5);
		JButton parametersButton = new JButton("Save Parameters");
		//tableauEmployees te = new tableauEmployees();
		
		//JTable tableauEmp = new JTable(te);
		
		ipLabel.setLabelFor(ipField);
		portLabel.setLabelFor(portField);
		ipLabel.setDisplayedMnemonic('I');
		portLabel.setDisplayedMnemonic('P');
		
		parametersElem.add(ipLabel);
		parametersElem.add(ipField);
		parametersElem.add(portLabel);
		parametersElem.add(portField);
		parametersElem.add(parametersButton);
		//parametersElem.add(tableauEmp);
		
		// define general informations for the jFrame as its title, its dimensions...
		setTitle("TimeClock Java -- SSMV");
		setSize(500,500);
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//adding the two Panes to the JTabbedPane and setting the content pane of the JFrame with the JTabbedPane
		timeClockPane.addTab("timeClock", timeClockTab);
		timeClockPane.addTab("Parameters", parametersElem);
		setContentPane(timeClockPane);
		timeClockPane.setSelectedComponent(timeClockTab);
	}
	

}

package view.timeClockView;
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

public class TimeClockFrame extends JFrame{
	private ArrayList<Employee> eliste;
	Employee e1; 
	Employee e2;
	
	class tableauEmployees extends AbstractTableModel
	{
		@Override
		public int getRowCount() {
			return eliste.size();
		}
	
		@Override
		public int getColumnCount() {
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
		
	}
	public TimeClockFrame() 
	{
		super();
		e1 = new Employee("Jacques","Martin",1,2);
		e2 = new Employee("Philippe","Poutou",2,4);
		eliste = new ArrayList<Employee>();
		eliste.add(e1);
		eliste.add(e2);
		build();
	}
	private void build() 
	{
		// define the organization of JFrame : one JTabbedPane which embed two JPanel
		JTabbedPane timeClockPane = new JTabbedPane();
		JPanelTimeClock timeClockTab = new JPanelTimeClock();
		JPanelParametersView parametersTab = new JPanelParametersView();
		
		
		// define general informations for the jFrame as its title, its dimensions...
		setTitle("TimeClock Java -- SSMV");
		setSize(500,500);
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//adding the two Panes to the JTabbedPane and setting the content pane of the JFrame with the JTabbedPane
		timeClockPane.addTab("timeClock", timeClockTab);
		timeClockPane.addTab("Parameters", parametersTab);
		setContentPane(timeClockPane);
		timeClockPane.setSelectedComponent(timeClockTab);
	}
	

}

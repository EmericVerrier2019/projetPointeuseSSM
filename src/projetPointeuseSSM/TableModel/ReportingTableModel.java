package projetPointeuseSSM.TableModel;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.table.AbstractTableModel;

import projetPointeuseSSM.Company;
import projetPointeuseSSM.Day;
import projetPointeuseSSM.Employee;

public class ReportingTableModel extends AbstractTableModel {
	/**
	 * Numéro de version imposé par AbstractTableModel
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * entreprise rattaché à l'application
	 */
	private Company company;
	/**
	 * entête du tableau
	 */
	private static final String[] header = {"Nom", "Prénom", "Heure d'arrivée", "Heure de départ", "Heures supplémentaire"} ;
	/**
	 * Liste des jours de travails affichés
	 */
	private List<Day> workdays;
	/**
	 * liste des employés associés à chaque jours de la liste workdays
	 */
	private List<Employee> employees;
	/**
	 * Booléen permettant de savoir si on affiche tous les pointages ou non
	 */
	private boolean allWorkdays;
	/**
	 * constructeur
	 * @param company company rattaché à l'application
	 */
	public ReportingTableModel(Company company) {
		setCompany(company);
	}


	
	public void setCompany(Company company) {
		this.company = company;
		updateList();
	}



	public void setAllWorkdays(boolean allWorkdays) {
		this.allWorkdays = allWorkdays;
		updateList();
	}



	public Company getCompany() {
		return company;
	}



	public boolean isAllWorkdays() {
		return allWorkdays;
	}

	private void updateList() {
		if(workdays == null) {
			workdays = new ArrayList<Day>();
		}else {
			workdays.clear();
		}
		if (employees == null) {
			employees = new ArrayList<Employee>();
		}
		else {
			employees.clear();
		}
		
		for (Employee employee : company.getListEmployees()) {
			if(allWorkdays) {
				for (Map.Entry<Day, Duration> map: employee.getReportingOfDayWorked().getWorkedDays().entrySet()) {
					workdays.add(map.getKey());
					employees.add(employee);
				}
			}
			
			Day currentDay = employee.getReportingOfDayWorked().getCurrentDay();
			if(currentDay.getTimeStart() != null) {
				workdays.add(currentDay);
				employees.add(employee);
			}
		}
	}

	@Override
	public int getRowCount() {
		return workdays.size();
	}

	@Override
	public int getColumnCount() {
		return header.length;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return header[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Employee employee = employees.get(rowIndex);
		Day day = workdays.get(rowIndex);
		
		switch(columnIndex) {
		case 0:
			return employee.getLastName();
		case 1: 
			return employee.getFirstName();
		case 2:
			return day.getTimeStart().format(DateTimeFormatter.ofPattern("EEEE dd MMMM YYYY : h'h'mm",Locale.FRANCE));
		case 3:
			return day.getTimeEnd();
		case 4:
			Duration overTimeHour = employee.getReportingOfDayWorked().getWorkedDays().get(day);
			return overTimeHour; 
		default:
			return "?";		
		}
	}

	@Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
	
	@Override
	public void fireTableDataChanged() {
		updateList();
		super.fireTableDataChanged();
		
	}
}



package projetPointeuseSSM.TableModel;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.table.AbstractTableModel;

import projetPointeuseSSM.Company;
import projetPointeuseSSM.Employee;

public class ManagementTableModel extends AbstractTableModel{
	/**
	 * Num�ro de version impos� par AbstractTableModel
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Employee> employees;
	private static final String[] header = {"id","prenom","nom","Pr�sent sur site"} ;
	
	public ManagementTableModel() {
		super();
	}
	
	public ManagementTableModel(Company company) {
		super();
		this.employees = (ArrayList<Employee>) company.getListEmployees();
	}
	
	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}

	public int getRowCount() {
		return employees.size();
	}
	

	public int getColumnCount() {
		return header.length;
	}
	
	public String getColumnName(int columnIndex) {
		return header[columnIndex];
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0:
			return employees.get(rowIndex).getIdEmployee();
		case 1: 
			return employees.get(rowIndex).getLastName();
		case 2:
			return employees.get(rowIndex).getFirstName();
		case 3:
			if (employees.get(rowIndex).getReportingOfDayWorked().getCurrentDay() == null){
				return -1;			
			}else if(employees.get(rowIndex).getReportingOfDayWorked().getCurrentDay().getTimeEnd() == null 
					&& employees.get(rowIndex).getReportingOfDayWorked().getCurrentDay().getTimeStart() == null){
				return -1;
			}else if(employees.get(rowIndex).getReportingOfDayWorked().getCurrentDay().getTimeEnd() == null){
				return 1;
			}else {
				return -1;
			}
		default :
			return null;
		}
	}
	@Override
	public void fireTableDataChanged() {
		super.fireTableDataChanged();
		
	}
	@Override
	public void fireTableCellUpdated(int row, int column) {
		super.fireTableCellUpdated(row, column);
	}
	@Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

}

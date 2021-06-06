package view;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import projetPointeuseSSM.Company;
import projetPointeuseSSM.Employee;

public class ManagementTableModel extends AbstractTableModel{
	/**
	 * Num�ro de version impos� par AbstractTableModel
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Employee> employees;
	private String[] header = {"id","prenom","nom"} ;
	
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

	public String[] getHeader() {
		return header;
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
		default:
			return null;		
		}
	}
	
	public void addEmployee(Employee employee) {
		employees.add(employee);
		fireTableRowsInserted(employees.size() -1 ,employees.size() -1);
	}
	
	public void removeEmployee(int rowIndex) {
		employees.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
		
	}

	@Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

}

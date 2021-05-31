package view.JPanel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import projetPointeuseSSM.Employee;

public class ManagementTableModel extends AbstractTableModel{
	private ArrayList<Employee> employees;
	private String[] header;
	
	public ManagementTableModel() {
		super();
		this.employees = new ArrayList<Employee>();
	}
	
	public ManagementTableModel(ArrayList<Employee> employees, String[] header) {
		super();
		this.employees = employees;
		this.header = header;
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

	public void setHeader(String[] header) {
		this.header = header;
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
	//header : {id , nom, prenom,  suppr}
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

	

}
